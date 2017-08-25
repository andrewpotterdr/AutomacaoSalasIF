package registradores;

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
	ColecaoInstituicoes colinst = new ColecaoInstituicoes();
	public Atualiza() throws Exception
	{
		try
		{
			colinst.recuperaArquivo();
		}
		catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
	}
	
	public void run()
	{
		ServerSocket updater = null;
		ObjectOutputStream oout = null;
		ObjectInputStream oin = null;
		Socket servidor = null;
		int porta = 51198;
		try
		{
			while(true)
			{
				updater = new ServerSocket(porta); //porta
				servidor = updater.accept();
				oin = new ObjectInputStream(servidor.getInputStream());
				oout = new ObjectOutputStream(servidor.getOutputStream());
				if(((Stringo)oin.readObject()).getStringo().equals("true"))
				{
					colinst.recuperaArquivo();
					Instituicao inst = colinst.procuraInst((InstituicaoEnsino)(oin.readObject()));
					Bloco bloco = inst.getColBlo().pesquisaPeloNome(((Bloco)oin.readObject()).getNome());
					Sala sala = bloco.getColSal().pesquisaPeloNome(((Sala)oin.readObject()).getNome());
					ColecaoDispositivos coldis = sala.getColDis();
					oout.writeObject(coldis);
				}
				servidor.close();
				porta++;
			}
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
		}
	}
}
