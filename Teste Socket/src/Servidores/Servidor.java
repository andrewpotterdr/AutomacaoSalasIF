package servidores;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import registradores.ColecaoDispositivos;
import registradores.Dispositivo;
 
public class Servidor
{
	public static void main(String[] args) throws IOException
	{
		ColecaoDispositivos coldis = new ColecaoDispositivos();
		Scanner input = new Scanner(System.in);
		DataOutputStream saida = null;
		boolean desligar = true;
		ServerSocket servidor = new ServerSocket(60050);
		System.out.println("Porta 60050 aberta!");
		while(true)
		{
			Socket cliente = servidor.accept();
			DataInputStream entrada = new DataInputStream(cliente.getInputStream());
			String MAC = entrada.readUTF();
			String IP = cliente.getInetAddress().toString().replace("/", "");
			System.out.println(IP);
			String nome = InetAddress.getByName(IP).getCanonicalHostName().replaceAll("/", "");
			System.out.println(nome);
			if(lerOpcao(input,0,1) == 0)
			{
				desligar = false;
			}
			boolean status = desligar;
			saida = new DataOutputStream(cliente.getOutputStream());
			saida.writeBoolean(desligar);
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