package registradores;

import java.io.DataInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Pablo e Michael
 * Classe que cria uma thread para atualizar as coleções a partir dos objetos salvos no arquivo.
 *
 */
public class Atualiza extends Thread
{
	ColecaoInstituicoes colinst = null;
	public Atualiza() throws Exception
	{
		
		colinst.recuperaArquivo();
	}
	
	public void run()
	{
		ServerSocket updater = null;
		ObjectOutputStream oout = null;
		ObjectInputStream oin = null;
		DataInputStream signin = null;
		Socket servidor = null;
		try
		{
			updater = new ServerSocket(51100);
			servidor = updater.accept();
			signin = new DataInputStream(servidor.getInputStream());
			oout = new ObjectOutputStream(servidor.getOutputStream());
			oin = new ObjectInputStream(servidor.getInputStream());
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
		}
		try
		{
			while(true)
			{
				if(signin.readBoolean())
				{
					signin.close();
					colinst.recuperaArquivo();
					//ColecaoDispositivos coldis = colinst.procuraInst((Instituicao)(oin.readObject())).getColBlo().pesquisaPeloNome(signin.readUTF()).getColSal().pesquisaPeloNome(signin.readUTF()).getColDis();
					Instituicao inst = colinst.procuraInst((Instituicao)(oin.readObject()));
					oin.close();
					Bloco bloco = inst.getColBlo().pesquisaPeloNome(signin.readUTF());
					Sala sala = bloco.getColSal().pesquisaPeloNome(signin.readUTF());
					signin.close();
					ColecaoDispositivos coldis = sala.getColDis();
					oout.writeObject(coldis);
					oout.close();
				}
			}
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
		}
	}
}
