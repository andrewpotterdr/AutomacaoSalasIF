package servidores;

import java.io.File;
import java.io.FileInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import registradores.ColecaoDispositivos;

public class ScreenGetShot extends Thread
{
	ColecaoDispositivos coldis = null;
	public ScreenGetShot(ColecaoDispositivos coldis) throws Exception
	{
		this.coldis = coldis;
	}
	
	public void run()
	{
		ServerSocket screenGetShot = null;
		FileInputStream filein = null;
		File file = null;
		Socket cliente = null;
		try
		{
			screenGetShot = new ServerSocket(47000);
			cliente = screenGetShot.accept();
		}
		catch(Exception e)
		{
			
		}
	}
}
