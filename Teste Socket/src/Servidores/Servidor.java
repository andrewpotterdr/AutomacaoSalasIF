package Servidores;

import java.io.DataInputStream;
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
		DataInputStream entrada = null;
		boolean desligar = false;
		ServerSocket servidor = new ServerSocket(12345);
		System.out.println("Porta 12345 aberta!");
		String[] maquinas = null;
		Boolean[] status = null;
		int i = 0;
		while(true)
		{
			Socket cliente = servidor.accept();
			entrada = new DataInputStream(cliente.getInputStream());
			maquinas[i] = entrada.readUTF();
			if(lerOpcao(input,0,1) == 1)
			{
				desligar = true;
			}
			status[i] = desligar;
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