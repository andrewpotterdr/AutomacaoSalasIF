package registradores;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Pablo Bezerra Guedes Lins de Albuquerque e Michael Almeida da Franca Monteiro
 * @version 1.0
 * Classe que extende de Thread e é usada para registrar as máquinas que fazem conexão via socket com o servidor socket contido nesta classe.
 */

public class RegistraMaquinas extends Thread
{	
	
	ColecaoInstituicoes colinst;
	ColecaoDispositivos coldis;
	int porta;
	
	/**
	 * Método construtor da classe.
	 * @param colinst
	 * @param coldis
	 * @param porta
	 */
	
	public RegistraMaquinas(ColecaoInstituicoes colinst, ColecaoDispositivos coldis, int porta)
	{
		this.colinst = colinst;
		this.coldis = coldis;
		this.porta = porta;
	}
	
	/**
	 * Método que contém o que será executado na Thread.
	 */
	
	@SuppressWarnings("resource")
	public void run()
	{
		Maquina maquina = null;
		ServerSocket servidor = null;
		try
		{
			servidor = new ServerSocket(porta);
		}
		catch (IOException e)
		{
			System.err.println(e.getMessage());
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
				System.err.println(e.getMessage());
			}
			DataInputStream entradaBool = null;
			ObjectInputStream entradaObj = null;
			try
			{
				entradaBool = new DataInputStream(cliente.getInputStream());
			}
			catch (IOException e)
			{
				System.err.println(e.getMessage());
			}
			try
			{
				if(entradaBool.readBoolean())
				{
					entradaObj = new ObjectInputStream(cliente.getInputStream());
					maquina = (Maquina) entradaObj.readObject();
					coldis.adicionaDispositivo(maquina);
					colinst.gravaArquivo();
				}
				entradaBool.close();
			}
			catch(Exception e)
			{
				System.err.println(e.getMessage());
			}
		}
	}
}
