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
import java.util.Base64.Encoder;
import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;
import registradores.Base64;
import registradores.Stringo;

public class ScreenShotProvider extends Thread
{
	private static String OS = System.getProperty("os.name").toLowerCase();
	Encoder base64;
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
		Stringo shotScreen;
		File img;
		String img64;
		try
		{
			inServidor = new ServerSocket(48000);
			while(true)
			{
				inCliente = inServidor.accept();
				img = printScreen();
				img64 = Base64.encode((FileUtils.readFileToByteArray(img)).toString());
				System.out.println(img64);
				shotScreen = new Stringo(img64.toString());
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
		String caminho = new String("ScreenShot" + nomeMaquina + ".png");
		/*if (isWindows())
		{
			System.out.println("BolstidiScreen4");
			caminho = "D:\\ScreenShot" + nomeMaquina + ".png";
		}*/
		BufferedImage tela = (new Robot()).createScreenCapture(new Rectangle(0, 0, d.width, d.height));
		File arquivo = new File (caminho);
		ImageIO.write(tela, "png", arquivo);
		return arquivo;
	}
	
	/**
	 * Verifica se o SO Ã© Windows.
	 * @return boolean
	 */
	public static boolean isWindows()
	{
		return (OS.indexOf("win") >= 0);
	}
}
