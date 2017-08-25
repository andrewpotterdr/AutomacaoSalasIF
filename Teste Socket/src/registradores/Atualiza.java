package registradores;

import java.io.DataInputStream;
import java.io.DataOutputStream;
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
		DataInputStream signin = null;
		DataOutputStream signout = null;
		Socket servidor = null;
		int porta = 51166;
		try
		{
			while(true)
			{
				updater = new ServerSocket(porta); //porta
				servidor = updater.accept();
				oin = new ObjectInputStream(servidor.getInputStream());
				oout = new ObjectOutputStream(servidor.getOutputStream());
				colinst.recuperaArquivo();
				Instituicao inst = colinst.procuraInst((InstituicaoEnsino)(oin.readObject()));
				System.out.println(inst);
				/*oin.close();
				signout.writeUTF("Não é que eu não consiga enviar alguma coisa, só não consigo enviar objetos");
				//Instituicao inst = colinst.procuraInst(new InstituicaoEnsino(signin.readUTF()));
				Bloco bloco = inst.getColBlo().pesquisaPeloNome(signin.readUTF());
				System.out.println(bloco);
				Sala sala = bloco.getColSal().pesquisaPeloNome(signin.readUTF());
				System.out.println(sala);
				ColecaoDispositivos coldis = sala.getColDis();
				oout.writeObject(coldis);
				//oout.close();*/
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
