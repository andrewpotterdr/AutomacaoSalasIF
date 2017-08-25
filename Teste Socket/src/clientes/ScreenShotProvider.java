package clientes;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.File;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.imageio.ImageIO;

import registradores.ScreenShot;

public class ScreenShotProvider extends Thread
{
	private static String OS = System.getProperty("os.name").toLowerCase();
	String nomeMaquina;
	public ScreenShotProvider(String nomeMaquina)
	{
		this.nomeMaquina = nomeMaquina;
	}
	
	public void run()
	{
		ServerSocket inServidor;
		Socket inCliente;
		DataInputStream entradaSinal;
		ObjectOutputStream oout;
		ScreenShot shotScreen;
		try
		{
			inServidor = new ServerSocket(48700);
			while(true)
			{
				inCliente = inServidor.accept();
				shotScreen = new ScreenShot(printScreen()); 
				entradaSinal = new DataInputStream(inCliente.getInputStream());
				oout = new ObjectOutputStream(inCliente.getOutputStream());
				if(entradaSinal.readBoolean())
				{
					oout.writeObject(shotScreen);
				}
				entradaSinal.close();
				oout.close();
				inCliente.close();
			}
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
		}
	}
	
	public File printScreen() throws Exception
	{
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		String caminho = new String("./ScreenShot" + nomeMaquina + ".png");
		if (isWindows())
		{
			caminho = "D:\\ScreenShot" + nomeMaquina + ".png";
		}
		BufferedImage tela = (new Robot()).createScreenCapture(new Rectangle(0, 0, d.width, d.height));
		File arquivo = new File (caminho);
		ImageIO.write(tela, "png", arquivo);
		return arquivo;
	}
	
	/**
	 * Verifica se o SO é Windows.
	 * @return boolean
	 */
	public static boolean isWindows()
	{
		return (OS.indexOf("win") >= 0);
	}
}
