package servidores;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;
import registradores.ColecaoDispositivos;
import registradores.InstituicaoEnsino;
import registradores.Maquina;
 
/**
 * @author Pablo Bezerra Guedes Lins de Albuquerque e Michael Almeida da Franca Monteiro
 * @version 1.0
 * Aplicação para operação de funções de uma máquina servidor.
 */

public class Servidor
{	

	/**
	 * Método principal de execução da aplicação, onde o servidor abre uma conexão socket com o registrador e adquire a coleção de dispositivos atualmente registrada na sala corrente, logo após lista os dispositivos e dá ao usuário a opção de escoher se um dispositivo deve ser desligado ou não, desligando o dispositivo por meio de envio de uma flag via conexão socket ao cliente, se essa foi a opção escolhida.
	 * @param args
	 * @throws Exception
	 */
	
	public static void main(String[] args) throws Exception
	{
		String IP = "10.0.4.148";
		ColecaoDispositivos coldis = null;
		Scanner input = new Scanner(System.in);
		Socket atualiza = null;
		DataOutputStream saidaSinal = null;
		ObjectOutputStream saidaObj = null;
		ObjectInputStream entradaCol = null;
		try
		{
			atualiza = new Socket(IP,51064);
			saidaSinal = new DataOutputStream(atualiza.getOutputStream());
			saidaObj = new ObjectOutputStream(atualiza.getOutputStream());
			entradaCol = new ObjectInputStream(atualiza.getInputStream());
			saidaSinal.writeBoolean(true);
			saidaObj.writeObject(new InstituicaoEnsino("ifpb","jp","jp"));
			saidaSinal.writeUTF("b");
			saidaSinal.writeUTF("1");
			coldis = (ColecaoDispositivos) entradaCol.readObject();
			atualiza.close();
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
		}
		coldis.listagemDispositivos();
		while(!menu(input,coldis));
	}
	
	/**
	 * Método menu que dá a opção de desligamento ou não dos dispositivos que serão listados neste método. Retorna true se o usuário decidir encerrar a aplicação, ou falso caso contrário.
	 * @param input
	 * @param coldis
	 * @return boolean
	 */
	
	private static boolean menu(Scanner input, ColecaoDispositivos coldis)
	{
		ColecaoDispositivos colmaq = coldis.getColMaq();
		System.out.println("LISTA DE DISPOSITIVOS");
		boolean[] desligandos = new boolean[colmaq.size()];
		Arrays.fill(desligandos, Boolean.FALSE);
		for(int i = 0; i < colmaq.size(); i++)
		{
			System.out.println(colmaq.getDispositivo(i).toString());
			System.out.println("Deseja desligar este dispositivo? ('1' - Sim/'0' - NÃ£o)");
			if(lerOpcao(input,0,1) == 1)
			{
				desligandos[i] = true;
			}
			else
			{
				desligandos[i] = false;
			}
		}
		Socket dispositivo = null;
		DataOutputStream cmdOff = null;
		for(int i = 0; i < colmaq.size(); i++)
		{
			String IP = ((Maquina)colmaq.getDispositivo(i)).getIP();
			try
			{
				dispositivo = new Socket(IP,55563);
				cmdOff = new DataOutputStream(dispositivo.getOutputStream());
				cmdOff.writeBoolean(desligandos[i]);
				cmdOff.close();
			}
			catch (IOException e)
			{
				System.err.println(e.getMessage());
			}
		}
		System.out.println("Deseja encerrar o Gerenciador de Dispositivos? ('1' - Sim/'0' - NÃ£o)");
		if(lerOpcao(input,0,1) == 1)
		{
			return true;
		}
		return false;
	}
	
	/**
	 * Método que garante validação da opção lida dentro do limite de números inteiros estabelecidos por parâmetro. Retorna a opção lida.
	 * @param input
	 * @param iniciall
	 * @param finall
	 * @return int
	 */
	
	private static int lerOpcao(Scanner input, int iniciall, int finall)
	{
		
		int opcao;
		if(!input.hasNextInt())
		{
			System.out.printf("Digite um nÃºmero vÃ¡lido: \n");
			input.nextLine();
			return lerOpcao(input,iniciall,finall);
		}
		opcao = input.nextInt();
		input.nextLine();
		if(opcao < iniciall || opcao > finall)
		{
			System.out.printf("Digite um nÃºmero entre '" + iniciall + "' e '" + finall + "' : \n");
			return lerOpcao(input,iniciall,finall);
		}
		return opcao;
	}
}