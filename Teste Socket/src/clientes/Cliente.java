package clientes;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente
{
	private static String OS = System.getProperty("os.name").toLowerCase();
	public static void main(String[] args) throws UnknownHostException, IOException
	{
		String MAC = GetNetworkAddress.GetAddress("mac");
		String IP = GetNetworkAddress.GetAddress("ip");
		String nome = "";
		try
		{
			nome = InetAddress.getByName(IP).getCanonicalHostName();
		}
		catch(Exception e)
		{
			System.err.println("Erro ao tentar encontrar o nome referente ao IP fornecido!");
		}
		boolean desligar = true;
		String conteudo = nome + "\r\n" + MAC +"\r\n" + IP + "\r\n" + desligar;
		String conteudoInterno = "";
		Socket cliente = null;
		DataOutputStream saida = null;
		File file = new File("D:/Pen-Card Amway/IFPB/Projeto Automação das Salas/AutomacaoSalasIF/Exemplo Dados Salvos em Texto/conteudo.txt");
		FileReader reader = null;
		FileWriter writer = null;
		try
		{
			cliente = new Socket("10.0.43.102", 60050);
		}
		catch(Exception e)
		{
			System.err.println("Erro ao tentar conectar ao registrador!");
		}
		saida = new DataOutputStream(cliente.getOutputStream());
		try
		{
			file.createNewFile();
		}
		catch(Exception e)
		{
			System.err.println("Erro na criação de arquivo!");
		}
		try
		{
			reader = new FileReader(file);
			while(reader.ready())
			{
				conteudoInterno += (char)reader.read();
			}
			reader.close();
		}
		catch(Exception e)
		{
			System.err.println("Erro na leitura de arquivo!");
		}
		try
		{
			if(!conteudoInterno.equals(conteudo))
			{
				writer = new FileWriter(file);
				writer.write(conteudo);
				writer.close();
				saida.writeBoolean(true);
				saida.writeUTF(conteudo);
			}
			else
			{
				saida.writeBoolean(false);
			}
		}
		catch(Exception e)
		{
			System.err.println("Erro na escrita de arquivo!");
		}
		cliente.close();
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