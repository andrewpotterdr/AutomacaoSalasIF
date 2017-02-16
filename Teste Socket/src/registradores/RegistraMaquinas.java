package registradores;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class RegistraMaquinas extends Thread
{	
	public void run(ColecaoDispositivos coldis) throws IOException, Exception
	{
		boolean status = false;
		ServerSocket servidor = new ServerSocket(60050);
		while(true)
		{
			Socket cliente;
			try
			{
				cliente = servidor.accept();
			}
			catch(IOException e)
			{
				throw new IOException("Conexão Interrompida!");
			}
			DataInputStream entrada = new DataInputStream(cliente.getInputStream());
			String MAC = entrada.readUTF();
			String IP = cliente.getInetAddress().toString().replace("/", "");
			String nome = InetAddress.getByName(IP).getCanonicalHostName().replaceAll("/", "");
			Dispositivo dispositivo = new Maquina(MAC, nome, IP, status);
			if(!coldis.adicionaDispositivo(dispositivo))
			{
				coldis.removeDispositivo(dispositivo);
				coldis.adicionaDispositivo(dispositivo);
			}
			servidor.close();
		}
	}
}
