package clientes;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.*;
 
public class Cliente
{
	private static String OS = System.getProperty("os.name").toLowerCase();
	public static void main(String[] args) throws UnknownHostException, IOException
	{
		DataInputStream entrada = null;
		DataOutputStream saida = null;
		Socket cliente = null;
		String MAC = GetNetworkAddress.GetAddress("mac");
		String IP = GetNetworkAddress.GetAddress("ip");
		String nome = InetAddress.getByName(IP).getCanonicalHostName();
		boolean desligar = true;
		//Path path = Paths.get("D:/Pen-Card Amway/IFPB/Projeto Automação das Salas/AutomacaoSalasIF/Exemplo Dados Salvos em Texto/conteudoMaquina.txt");
		Path path = Paths.get("D:/conteudo.txt");
		String conteudo = "NAME: " + nome + "\nMAC: " + MAC + "\nIP: " + IP + "\nDesligar: " + desligar;
		try
		{
			cliente = new Socket("10.0.4.191", 60050);
			saida = new DataOutputStream(cliente.getOutputStream());
		}
		catch(IOException e)
		{
			System.err.println("Falha na comunicação com o registrador!");
		}
		byte textoSaida[] = conteudo.getBytes();
		try
		{
			InputStream in = Files.newInputStream(path);
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String conteudoInterno = null;
			String linha = null;
			while((linha = reader.readLine()) != null)
			{
				conteudoInterno += linha;
			}
			try
			{
				if(!conteudoInterno.equals(conteudo))
				{
					Files.delete(path);
					OutputStream out = new BufferedOutputStream(Files.newOutputStream(path,CREATE,APPEND)); 
					out.write(textoSaida, 0, textoSaida.length);
					saida.writeBoolean(true);
				}
				else
				{
					saida.writeBoolean(false);
				}
			}
			catch(IOException e)
			{
				System.err.println("Falha na comunicação com o registrador!");
			}
		}
		catch(IOException e)
		{
			System.err.println("Erro na escrita/leitura de arquivo!");
		}
		saida.close();
		cliente.close();
		try
		{
			cliente = new Socket("10.0.4.191", 60100);
			saida = new DataOutputStream(cliente.getOutputStream());
		}
		catch(IOException e)
		{
			System.err.println("Falha na comunicação com o servidor!");
		}
		saida.writeUTF(conteudo);
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