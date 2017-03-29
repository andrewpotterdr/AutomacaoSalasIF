package servidores;

import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import registradores.ColecaoDispositivos;
import registradores.InstituicaoEnsino;

public class Recupera extends Thread
{	
	ColecaoDispositivos coldis = null;
	String IP = null;
	
	public Recupera(ColecaoDispositivos coldis) throws Exception
	{
		this.coldis = coldis;
	}
	
	public void run()
	{
		Socket atualiza = null;
		DataOutputStream saidaSinal = null;
		ObjectOutputStream saidaObj = null;
		ObjectInputStream entradaCol = null;
		try
		{
			atualiza = new Socket(IP,51007);
			saidaSinal = new DataOutputStream(atualiza.getOutputStream());
			saidaObj = new ObjectOutputStream(atualiza.getOutputStream());
			entradaCol = new ObjectInputStream(atualiza.getInputStream());
			while(true)
			{
				saidaSinal.writeBoolean(true);
				saidaSinal.close();
				saidaObj.writeObject(new InstituicaoEnsino("ifpb","jp","jp"));
				saidaObj.close();
				saidaSinal.writeUTF("b");
				saidaSinal.writeUTF("1");
				saidaSinal.close();
				coldis = (ColecaoDispositivos) entradaCol.readObject();
				entradaCol.close();
			}
		}
		catch (Exception e)
		{
			System.err.println(e.getMessage());
		}
	}
}
