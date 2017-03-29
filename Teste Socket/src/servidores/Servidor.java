package servidores;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import registradores.ColecaoDispositivos;
import registradores.Maquina;
 
public class Servidor
{	
	private static boolean desligandos[];

	public static void main(String[] args) throws Exception
	{
		ColecaoDispositivos coldis = null;
		Scanner input = new Scanner(System.in);
		Recupera update = null;
		try
		{
			update = new Recupera(coldis);
			update.start();
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
		}
		while(!menu(input,coldis));
	}
	
	private static boolean menu(Scanner input, ColecaoDispositivos coldis)
	{
		ColecaoDispositivos colmaq = coldis.getColMaq();
		System.out.println("LISTA DE DISPOSITIVOS");
		desligandos = null;
		for(int i = 0; i < colmaq.size(); i++)
		{
			System.out.println(colmaq.getDispositivo(i).toString());
			System.out.println("Deseja desligar este dispositivo? ('1' - Sim/'0' - Não)");
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
				dispositivo = new Socket(IP,55555);
				cmdOff = new DataOutputStream(dispositivo.getOutputStream());
				cmdOff.writeBoolean(desligandos[i]);
				cmdOff.close();
			}
			catch (IOException e)
			{
				System.err.println(e.getMessage());
			}
		}
		System.out.println("Deseja encerrar o Gerenciador de Dispositivos? ('1' - Sim/'0' - Não)");
		if(lerOpcao(input,0,1) == 1)
		{
			return true;
		}
		return false;
	}
	
	private static int lerOpcao(Scanner input, int iniciall, int finall)
	{
		int opcao;
		if(!input.hasNextInt())
		{
			System.out.printf("Digite um número válido: \n");
			input.nextLine();
			return lerOpcao(input,iniciall,finall);
		}
		opcao = input.nextInt();
		input.nextLine();
		if(opcao < iniciall || opcao > finall)
		{
			System.out.printf("Digite um número entre '" + iniciall + "' e '" + finall + "' : \n");
			return lerOpcao(input,iniciall,finall);
		}
		return opcao;
	}
}