package servidores;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;
import registradores.Bloco;
import registradores.ColecaoDispositivos;
import registradores.InstituicaoEnsino;
import registradores.Maquina;
import registradores.Sala;
import registradores.Stringo;
 
/**
 * @author Pablo Bezerra Guedes Lins de Albuquerque e Michael Almeida da Franca Monteiro. 
 * Classe servidor, recebe o arquivo com a coleÃƒÂ§ÃƒÂ£o referente aos dispositivos e chama o mÃƒÂ©todo menu. 
 */
public class Servidor
{	

	public static void main(String[] args) throws Exception
	{
		String IP = "10.0.2.158";
		ColecaoDispositivos coldis = null;
		Scanner input = new Scanner(System.in);
		Socket atualiza = null;
		ObjectOutputStream saidaObj = null;
		ObjectInputStream entradaCol = null;
		try
		{
			atualiza = new Socket(IP,51148);
			saidaObj = new ObjectOutputStream(atualiza.getOutputStream());
			entradaCol = new ObjectInputStream(atualiza.getInputStream());
			saidaObj.writeObject(new Stringo("true"));
			saidaObj.writeObject(new InstituicaoEnsino("ifpb","jp","jp"));
			saidaObj.writeObject(new Bloco("b"));
			saidaObj.writeObject(new Sala("1"));
			coldis = (ColecaoDispositivos) entradaCol.readObject();
			coldis.listagemDispositivos();
			atualiza.close();
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
		}
		while(!menu(input,coldis));
	}
	
	/**
	 * Lista os dispositivos e pede entrada para marcar os dispositivos a serem desligados ou nÃƒÂ£o, e entÃƒÂ£o o servidor 
	 * abre uma conexÃƒÂ£o socket com cada cliente enviando um sinal de desligamento ou nÃƒÂ£o, caso queira encerrar o menu retorna true
	 * caso nÃƒÂ£o, false.
	 * @param input
	 * @param coldis
	 * @return boolean
	 * @throws Exception 
	 */
	private static boolean menu(Scanner input, ColecaoDispositivos coldis) throws Exception
	{
		ColecaoDispositivos colmaq = coldis.getColMaq();
		boolean[] desligandos = new boolean[colmaq.size()];
		Arrays.fill(desligandos, Boolean.FALSE);
		for(int i = 0; i < colmaq.size(); i++)
		{
			while(!menudisp(input, colmaq, desligandos));
		}
		Socket dispositivo = null;
		DataOutputStream cmdOff = null;
		for(int i = 0; i < colmaq.size(); i++)
		{
			String IP = ((Maquina)colmaq.getDispositivo(i)).getIP();
			try
			{
				dispositivo = new Socket(IP,55590);
				cmdOff = new DataOutputStream(dispositivo.getOutputStream());
				cmdOff.writeBoolean(desligandos[i]);
				cmdOff.close();
			}
			catch (IOException e)
			{
				System.err.println(e.getMessage());
			}
		}
		System.out.println("Deseja encerrar o Gerenciador de Dispositivos? ('1' - Sim/'0' - NÃƒÆ’Ã‚Â£o)");
		if(lerOpcao(input,0,1) == 1)
		{
			return true;
		}
		return false;
	}
	
	private static boolean menudisp(Scanner input, ColecaoDispositivos colmaq, boolean [] desligandos) throws Exception
	{
		Socket screenGetShot = null;
		String IP = null;
		DataOutputStream saidaSinal = null;
		ObjectInputStream oin = null;
		Stringo shotScreen;
		System.out.println("Digite uma das opÃ§Ãµes abaixo:"
						 + "0 - Encerrar Etapa\n"
						 + "1 - Listar MÃ¡quinas\n"
						 + "2 - Executar SequÃªncia\n");
		switch(lerOpcao(input,0,2))
		{
			case 0:
			return true;
			case 1:
				for(int i = 0; i < colmaq.size(); i++)
				{
					System.out.println(colmaq.getDispositivo(i));
					IP = ((Maquina)colmaq.getDispositivo(i)).getIP();
					screenGetShot = new Socket(IP,48726);
					saidaSinal = new DataOutputStream(screenGetShot.getOutputStream());
					oin = new ObjectInputStream(screenGetShot.getInputStream());
					saidaSinal.writeBoolean(true);
					shotScreen = (Stringo) oin.readObject();
					oin.close();
					saidaSinal.close();
					screenGetShot.close();
					System.out.println(shotScreen.getStringo());
				}
			return false;
			case 2:
				for(int i = 0; i < desligandos.length; i++)
				{
					desligandos[i] = lerOpcao(input, 0, 1) == 1? true: false;
				}
			return false;
		}
		return false;
	}
	
	/**
	 * MÃƒÂ©todo que realiza o tratamento de entradas.
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
			System.out.printf("Digite um nÃƒÆ’Ã‚Âºmero vÃƒÆ’Ã‚Â¡lido: \n");
			input.nextLine();
			return lerOpcao(input,iniciall,finall);
		}
		opcao = input.nextInt();
		input.nextLine();
		if(opcao < iniciall || opcao > finall)
		{
			System.out.printf("Digite um nÃƒÆ’Ã‚Âºmero entre '" + iniciall + "' e '" + finall + "' : \n");
			return lerOpcao(input,iniciall,finall);
		}
		return opcao;
	}
}