package registradores;

import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Atualiza extends Thread
{
	ColecaoInstituicoes colinst;
	
	public Atualiza(ColecaoInstituicoes colinst)
	{
		this.colinst = colinst;
	}
	
	public void run()
	{
		ServerSocket updater = null;
		ObjectOutputStream oout = null;
		Socket servidor = null;
		try
		{
			updater = new ServerSocket(51998);
			servidor = updater.accept();
			oout = new ObjectOutputStream(servidor.getOutputStream());
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
		}
		try
		{
			while(true)
			{
				colinst.recuperaArquivo();
				oout.writeObject(colinst);
				wait(60);
			}
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
		}
	}
}
