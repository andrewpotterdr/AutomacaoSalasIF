package clientes;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import registradores.Maquina;

public class Cliente
{
	private static String OS = System.getProperty("os.name").toLowerCase();
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
			cliente = new Socket("10.0.13.108", 60067);
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
	}
	
	public static void desligar(Boolean stat) throws IOException
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
	
	public static boolean isWindows()
	{
		return (OS.indexOf("win") >= 0);
	}
	
	public static boolean isMac()
	{
		return (OS.indexOf("mac") >= 0);
	}
	
	public static boolean isUnix()
	{
    	return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0 );
	}
}