package clientes;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import registradores.Maquina;

/**
 * @author Pablo e Michael
 * Classe que representa o cliente, é aqui onde as informações das máquinas serão adiquiridas e passadas para o registrador
 * essas informações são passadas via socket, também é na classe Cliente que o método referente ao desligamento é implementado.
 */
public class Cliente
{
	private static String OS = System.getProperty("os.name").toLowerCase();
	/**
	 * @param args
	 * @throws UnknownHostException
	 * @throws IOException
	 * O método main é encarregado de pegar os dados como MAC , IP e nome, uma variável boolean desligar é setada como true para ser passada no construtor do tipo Maquina,
	 * dois arquivos com os Objects são criados, um que será gravado na máquina e um temporário, o qual caso haja alguma alteração em relação ao armazenado substituirá o antigo,
	 * o main também manda o file para o registrador.
	 */
	public static void main(String[] args) throws UnknownHostException, IOException
	{
		
		String MAC = GetNetworkAddress.GetAddress("mac");
		String IP = GetNetworkAddress.GetAddress("ip");
		String nome = null;
		Maquina maquina = null;
		try
		{
			nome = InetAddress.getByName(IP).getCanonicalHostName();
		}
		catch(Exception e)
		{
			System.err.println("Erro ao tentar encontrar o nome referente ao IP fornecido!");
		}
		boolean desligar = true;
		maquina = new Maquina(nome, MAC, IP, desligar);
		Socket cliente = null;
		DataOutputStream saidaBool = null;
		File file = null, tempFile = null;
		FileInputStream fin = null;
		ObjectInputStream oin = null;
		FileOutputStream fout = null, tempFout = null;
		ObjectOutputStream saidaObj = null, oout = null, tempOout = null;
		try
		{
			file = new File("D:/conteudo.dat");
			fout = new FileOutputStream(file);
			oout = new ObjectOutputStream(fout);
			fin = new FileInputStream(file);
			oin = new ObjectInputStream(fin);
			tempFile = new File("D:/conteudoTemp.dat");
			tempFout = new FileOutputStream(tempFile);
			tempOout = new ObjectOutputStream(tempFout);
			tempOout.writeObject(maquina);
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
		}
		try
		{
			cliente = new Socket("10.0.4.84", 60067);
		}
		catch(Exception e)
		{
			System.err.println("Erro ao tentar conectar ao registrador!");
		}
		try
		{
			saidaBool = new DataOutputStream(cliente.getOutputStream());
			file.createNewFile();
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
		}
		try
		{
			if(file.equals(tempFile))
			{
				saidaBool.writeBoolean(false);
			}
			else
			{
				saidaBool.writeBoolean(true);
				saidaObj = new ObjectOutputStream(cliente.getOutputStream());
				saidaObj.writeObject(maquina);
				oout.writeObject(maquina);
			}
			fin.close();
			oin.close();
			fout.close();
			oout.close();
			tempFout.close();
			tempOout.close();
			tempFile.delete();
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
		}
		try
		{
			cliente.close();
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
		}
		
		ServerSocket inServidor;
		Socket inCliente;
		DataInputStream entradaSinal;
		try
		{
			inServidor = new ServerSocket(55555);
			while(true)
			{
				inCliente = inServidor.accept();
				entradaSinal = new DataInputStream(inCliente.getInputStream());
				if(entradaSinal.readBoolean())
				{
					desligar();
				}
				entradaSinal.close();
			}
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
		}
	}
	
	/**
	 * @throws IOException
	 * Método com finalidade de mandar os comandos de desligamento referente ao SO utilizado no cliente.
	 */
	public static void desligar() throws IOException
	{
		if (isWindows())
		{
			String[] commandWin = new String[3];
			commandWin[0] = "cmd";
			commandWin[1] = "/c";
			commandWin[2] = "shutdown -s";
			Runtime.getRuntime().exec(commandWin);
	    }
		else if (isMac())
	    {
			String commandMac = "shutdown";
			Runtime.getRuntime().exec(commandMac);
	    }
		else if (isUnix())
	    {
			String commandLin = "poweroff";
			Runtime.getRuntime().exec(commandLin);
	    }
	}
	
	/**
	 * Verifica se o SO é Windows.
	 * @return boolean
	 */
	public static boolean isWindows()
	{
		
		return (OS.indexOf("win") >= 0);
	}
	
	/**
	 * Verifica se o SO é Mac.
	 * @return boolean
	 */
	public static boolean isMac()
	{
		return (OS.indexOf("mac") >= 0);
	}
	
	/**
	 * Verifica se o SO é Unix.
	 * @return boolean
	 */
	public static boolean isUnix()
	{
    	return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0 );
	}
}