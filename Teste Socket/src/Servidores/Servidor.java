package Servidores;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
 
public class Servidor
{
	public static void main(String[] args) throws IOException
	{
		Scanner input = new Scanner(System.in);
		DataOutputStream saida;
		boolean desligar = false;
		ServerSocket servidor = new ServerSocket(12345);
		System.out.println("Porta 12345 aberta!");
		Socket cliente = servidor.accept();
		if(lerOpcao(input,0,1) == 1)
		{
			desligar = true;
		}
		saida = new DataOutputStream(cliente.getOutputStream());
		if(desligar)
		{
			saida.writeInt(1);
		}
		else
		{
			saida.writeInt(0);
		}
		saida.close();
		input.close();
		servidor.close();
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