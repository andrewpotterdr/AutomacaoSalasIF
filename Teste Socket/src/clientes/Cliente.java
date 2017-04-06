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
 * @authors Pablo Bezerra Guedes Lins de Albuquerque e Michael Almeida da Franca Monteiro
 * @version 1.0
 * Aplicação para operação de funções de uma máquina cliente.
 */

public class Cliente
{
	/**
	 * Variável que armazena o nome do sistema operacional onde a aplicação será executada.
	 */
	private static String OS = System.getProperty("os.name").toLowerCase();
	
	/**
	 * Método principal de execução da aplicação, onde o cliente faz uma comunicação com o registrador via socket e após, abre um ServerSocket que fica à espera da conexão do socket que será rodado na aplicação do servidor.
	 * @param args
	 * @throws UnknownHostException
	 * @throws IOException
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
	 * Método usado para desligar o dispositivo onde esta aplicação for rodada.
	 * @throws IOException
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
	 * Método para verificar se o sistema operacional onde esta aplicação estiver sendo rodada é Windows OS. Retorna true se for validada ou false caso contrário.
	 * @return boolean
	 */
	
	public static boolean isWindows()
	{
		return (OS.indexOf("win") >= 0);
	}
	
	/**
	 * Método para verificar se o sistema operacional onde esta aplicação estiver sendo rodada é Mac OS.  Retorna true se for validada ou false caso contrário.
	 * @return boolean
	 */
	
	public static boolean isMac()
	{
		return (OS.indexOf("mac") >= 0);
	}
	
	/**
	 * Método para verificar se o sistema operacional onde esta aplicação estiver sendo rodada é Unix OS ou derivados.  Retorna true se for validada ou false caso contrário.
	 * @return boolean
	 */
	
	public static boolean isUnix()
	{
    	return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0 );
	}
}