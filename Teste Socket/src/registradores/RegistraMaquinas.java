package registradores;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class RegistraMaquinas extends Thread
{	
	public void run(ColecaoInstituicoes colinst, ColecaoDispositivos coldis) throws IOException, Exception
	{
		ServerSocket servidor = new ServerSocket(60051);
		Socket cliente;
		while(true)
		{
			try
			{
				cliente = servidor.accept();
			}
			catch(IOException e)
			{
				throw new IOException("Conexão Interrompida!");
			}
			DataInputStream entrada = new DataInputStream(cliente.getInputStream());
			try
			{
				if(entrada.readBoolean())
				{
					System.out.println("AQUI");
					String conteudoMaquina = entrada.readUTF();
					System.out.println("AQUI1");
					String partes[] = conteudoMaquina.split("\r\n");
					String nome = partes[0];
					String MAC = partes[1];
					String IP = partes[2];
					Dispositivo dispositivo = new Maquina(nome, MAC, IP, true);
					System.out.println("AQUI2");
					if(!coldis.adicionaDispositivo(dispositivo))
					{
						coldis.removeDispositivo(dispositivo);
						coldis.adicionaDispositivo(dispositivo);
					}
					System.out.println("AQUI3");
					try
					{
						colinst.gravaArquivo();
					}
					catch(Exception e)
					{
						throw new Exception(e.getMessage());
					}
					System.out.println("AQUI4");
					System.out.println(coldis.getDispositivo(0).toString());
				}
			}
			catch(Exception e)
			{
				throw new Exception(e.getMessage());
			}
			servidor.close();
		}
	}
}
