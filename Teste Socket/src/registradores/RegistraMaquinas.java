package registradores;
import java.io.Serializable;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class RegistraMaquinas extends Thread implements Serializable
{	
	
	ColecaoInstituicoes colinst;
	ColecaoDispositivos coldis;
	
	public RegistraMaquinas(ColecaoInstituicoes colinst, ColecaoDispositivos coldis)
	{
		this.colinst = colinst;
		this.coldis = coldis;
	}
	
	public void run()
	{
		ServerSocket servidor = null;
		try
		{
			servidor = new ServerSocket(60048);
		}
		catch (IOException e)
		{
		}
		Socket cliente = null;
		while(true)
		{
			try
			{
				cliente = servidor.accept();
			}
			catch(IOException e)
			{
			}
			DataInputStream entrada = null;
			try
			{
				entrada = new DataInputStream(cliente.getInputStream());
			}
			catch (IOException e)
			{
			}
			try
			{
				if(entrada.readBoolean())
				{
					String conteudoMaquina = entrada.readUTF();
					String partes[] = conteudoMaquina.split("\r\n");
					String nome = partes[0];
					String MAC = partes[1];
					String IP = partes[2];
					Dispositivo dispositivo = new Maquina(nome, MAC, IP, true);
					if(!coldis.adicionaDispositivo(dispositivo))
					{
						coldis.removeDispositivo(dispositivo);
						coldis.adicionaDispositivo(dispositivo);
					}
					try
					{
						colinst.gravaArquivo();
					}
					catch(Exception e)
					{
						throw new Exception(e.getMessage());
					}
				}
			}
			catch(Exception e)
			{
			}
			//servidor.close();
		}
	}
}
