package registradores;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class RegistraMaquinas extends Thread
{	
	public void run(ColecaoInstituicoes colinst, ColecaoDispositivos coldis) throws IOException, Exception
	{
		ServerSocket servidor = new ServerSocket(60050);
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
			}
			
			servidor.close();
		}
	}
}
