package registradores;

import java.io.DataInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Pablo Bezerra Guedes Lins de Albuquerque e Michael Almeida da Franca Monteiro
 * @version 1.0
 * Classe que extende de Thread e é usada para atualizar sempre que for requisitado pelo servidor, a coleção atual de dispositivos de um ambiente.
 */

public class Atualiza extends Thread
{	
	
	ColecaoInstituicoes colinst = new ColecaoInstituicoes();

	/**
	 * Método construtor da classe
	 * @param colinst
	 */
	
	public Atualiza(ColecaoInstituicoes colinst)
	{
		this.colinst = colinst;
	}
	
	/**
	 * Método que contém o que será executado na Thread.
	 */
	
	public void run()
	{
		ServerSocket updater = null;
		ObjectOutputStream oout = null;
		ObjectInputStream oin = null;
		DataInputStream signin = null;
		Socket servidor = null;
		try
		{
			updater = new ServerSocket(51122);
			servidor = updater.accept();
			signin = new DataInputStream(servidor.getInputStream());
			oin = new ObjectInputStream(servidor.getInputStream());
			oout = new ObjectOutputStream(servidor.getOutputStream());
		}
		catch(Exception e)
		{
			System.err.println("Porta em uso!");
		}
		try
		{
			while(true)
			{
				if(signin.readBoolean())
				{
					colinst.recuperaArquivo();
					Instituicao inst = colinst.procuraInst((InstituicaoEnsino)(oin.readObject()));
					Bloco bloco = inst.getColBlo().pesquisaPeloNome(signin.readUTF());
					Sala sala = bloco.getColSal().pesquisaPeloNome(signin.readUTF());
					ColecaoDispositivos coldis = sala.getColDis();
					oout.writeObject(coldis);
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("Conexão encerrada com sucesso!");
		}
	}
}
