package servidores;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import registradores.*;
 
public class Servidor
{
	public static void main(String[] args) throws IOException
	{
		ColecaoDispositivos coldis = new ColecaoDispositivos();
		Scanner input = new Scanner(System.in);
		DataOutputStream saida = null;
		boolean desligar = true;
		ServerSocket servidor = new ServerSocket(60100);
		System.out.println("Porta 60100 aberta!");
		while(true)
		{
			Socket cliente = servidor.accept();
			DataInputStream entrada = new DataInputStream(cliente.getInputStream());
			String conteudoMaquina = entrada.readUTF();
			String partes[] = conteudoMaquina.split("\n");
			String MAC = partes[1];
			Maquina maquina = coldis.pesquisaMaquina(MAC);
			if(lerOpcao(input,0,1) == 0)
			{
				desligar = false;
			}
			maquina.setStatus(false);
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