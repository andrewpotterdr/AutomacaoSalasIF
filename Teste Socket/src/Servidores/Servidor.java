package Servidores;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
 
public class Servidor
{
	public static void main(String[] args) throws IOException
	{
		Scanner input = new Scanner(System.in);
		DataOutputStream saida = null;
		boolean desligar = true;
		ServerSocket servidor = new ServerSocket(12500);
		System.out.println("Porta 12345 aberta!");
		int i = 0;
		while(true)
		{
			Socket cliente = servidor.accept();
			String ID = cliente.getInputStream().toString();
			System.out.println(ID);
			String IP = cliente.getInetAddress().toString();
			System.out.println(IP);
			if(lerOpcao(input,0,1) == 0)
			{
				desligar = false;
			}
			boolean status = desligar;
			saida = new DataOutputStream(cliente.getOutputStream());
			saida.writeBoolean(desligar);
			i++;
		}
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