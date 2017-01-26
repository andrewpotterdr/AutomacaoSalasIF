package Clientes;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
 
public class Cliente
{
	private static String OS = System.getProperty("os.name").toLowerCase();
	public static void main(String[] args) throws UnknownHostException, IOException
	{
		Socket cliente = new Socket("127.0.0.1", 12345);
		DataInputStream entrada = new DataInputStream(cliente.getInputStream());
		boolean stat = entrada.readBoolean();
		if(stat)
		{
			if (isWindows())
			{
				String[] commandWin = new String[3];
				commandWin[0] = "cmd";
				commandWin[1] = "/c";
				commandWin[2] = "shutdown -s";
				Runtime.getRuntime().exec (commandWin);
	    	}
			else if (isMac())
	    	{
				String commandMac = "shutdown";
				Runtime.getRuntime().exec (commandMac);
	    	}
			else if (isUnix())
	    	{
				String commandLin = "poweroff";
				Runtime.getRuntime().exec (commandLin);
	    	}
		}
		entrada.close();
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