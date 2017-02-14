package registradores;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Registrador
{
	public static void main(String[] args) throws IOException
	{
		Scanner input = new Scanner(System.in);
		Instituicao inst;
		while(menu(input,inst) != 1);
	}
	
	public static int menu(Scanner input, Instituicao inst)
	{
		int res;
		System.out.println("Escolha uma das opções abaixo: \n"
						 + "1 - Menu Instituições\n"
						 + "2 - Menu Blocos\n"
						 + "3 - Menu Salas\n"
						 + "4 - Menu Dispositivos\n"
						 + "5 - Encerrar Aplicação");
		switch(lerOpcao(input,1,5))
		{
			case 1:
				res = menuInstituicoes(input,inst);
				while(res != 2)
				{
					res = menuInstituicoes(input,inst);
				}
			return 0;
			case 2:
				res = menuBlocos(input,inst);
				while(res != 2)
				{
					res = menuBlocos(input,inst);
				}
			return 0;
			case 3:
				res = menuSalas(input,inst);
				while(res != 2)
				{
					res = menuSalas(input,inst);
				}
			return 0;
			case 4:
				res = menuDispositivos(input,inst);
				while(res != 2)
				{
					res = menuDispositivos(input,inst);
				}
			return 0;
		}
		return 1;
	}
	
	public static int menuInstituicoes(Scanner input, Instituicao inst)
	{
		String nome;
		String cidade;
		inst = null;
		System.out.println("Escolha uma das opções abaixo\n"
						 + "1 - Cadastrar Instituicao de Ensino\n"
						 + "2 - Cadastrar Empresa\n"
						 + "3 - Alterar Nome da Instituição\n"
						 + "4 - Desvincular instituição\n"
						 + "5 - Retornar ao Menu Anterior");
		switch(lerOpcao(input,1,5))
		{
			case 1:
				if(inst != null)
				{	
					System.out.println("A instituição já foi cadastrada!\nSe deseja cadastrar outra institução, você precisa desvincular a atualmente cadastrada.");
					return 0;
				}
				System.out.println("Digite o nome da instituição: ");
				nome = input.nextLine();
				System.out.println("Digite o nome da cidade: ");
				cidade = input.nextLine();
				System.out.println("Digite a qual Campus pertence a Instituição: ");
				String campus = input.nextLine();
				inst = new InstituicaoEnsino(nome, cidade, campus);
				System.out.println("Instituição de Ensino cadastrada com sucesso!");
			return 1;
			case 2:
				if(inst != null)
				{
					System.out.println("A instituição já foi cadastrada!\nSe deseja cadastrar outra institução, você precisa desvincular a atualmente cadastrada.");
					return 0;
				}
				System.out.println("Digite o nome da instituição: ");
				nome = input.nextLine();
				System.out.println("Digite o nome da cidade: ");
				cidade = input.nextLine();
				System.out.println("Digito o CNPJ da empresa: ");
				String CNPJ = input.nextLine();
				inst = new Empresa(nome, cidade, CNPJ);
				System.out.println("Empresa cadastrada com sucesso!");
			return 1;
			case 3:
				if(inst == null)
				{
					System.out.println("A instituição ainda não foi cadastrada!");
					return 0;
				}
				System.out.println("Digite o nome para qual vai ser modificado a instituição: ");
				nome = input.nextLine();
				inst.setNome(nome);
				System.out.println("Nome alterado com sucesso!");
			return 1;
			case 4:
				if(inst != null)
				{
					System.out.println("Tem certeza que deseja desvincular a instituicao " + inst.getNome() + "? ('1' - Sim/'0' - Não)");
					if(lerOpcao(input,0,1) == 1)
					{
						inst = null;
						System.out.println("Instituição desvinculada com sucesso!");
					}
				}
				else
				{
					System.out.println("Ainda não há uma instituição cadastrada!");
					return 0;
				}
			return 1;
		}
		return 2;
	}
	
	public static int menuBlocos(Scanner input, Instituicao inst)
	{
		if(inst == null)
		{
			System.out.println("Ainda não há uma instituição cadastrada!");
			return 2;
		}
		String nome;
		Bloco bloco = null;
		System.out.println("Escolha uma das opções abaixo\n"
				 + "1 - Adicionar Bloco\n"
				 + "2 - Listar Blocos\n"
				 + "3 - Remover Bloco\n"
				 + "4 - Mostrar Quantidade de Blocos\n"
				 + "5 - Retornar ao Menu Anterior");
		switch(lerOpcao(input,1,5))
		{
			case 1:
				System.out.println("Digite o nome do bloco a ser adicionado: ");
				nome = input.nextLine();
				bloco = new Bloco(nome);
				if(!inst.getColBlo().adicionabloco(bloco))
				{
					System.out.println("Este bloco já foi adicionado!");
					return 0;
				}
				System.out.println("Bloco adicionado com sucesso!");
			return 1;
			case 2:
				if(inst.getColBlo().size() == 0)
				{
					System.out.println("Ainda não há nenhum bloco cadastrado na instituição!");
					return 0;
				}
				else
				{
					inst.getColBlo().listagemblocos();
				}
			return 1;
			case 3:
				System.out.println("Digite o nome do bloco a ser desvinculado: ");
				nome = input.nextLine();
				bloco = inst.getColBlo().pesquisaPeloNome(nome);
				if(bloco == null)
				{
					System.out.println("Não há nenhum bloco cadastrado com esse nome!");
					return 0;
				}
				else
				{
					if(inst.getColBlo().removebloco(bloco))
					{
						System.out.println("Bloco desvinculado com sucesso!");
					}
				}
			return 1;
			case 4:
				System.out.println("Quantidade de blocos cadastrados: " + inst.getColBlo().size());
			return 1;
		}
		return 2;
	}
	
	public static int menuSalas(Scanner input, Instituicao inst)
	{
		if(inst == null)
		{
			System.out.println("Ainda não há uma instituição cadastrada!");
			return 2;
		}
		else
		{
			if(inst.getColBlo().size() == 0)
			{
				System.out.println("Ainda não há nenhum bloco cadastrado!");
				return 2;
			}
		}
		String nome;
		Sala sala = null;
		Bloco bloco = null;
		System.out.println("Escolha uma das opções abaixo\n"
				 		 + "1 - Adicionar Sala\n"
				 		 + "2 - Listar Salas\n"
				 		 + "3 - Remover Sala\n"
				 		 + "4 - Mostrar Quantidade de Salas\n"
				 		 + "5 - Retornar ao Menu Anterior");
		switch(lerOpcao(input,1,5))
		{
			case 1:
				System.out.println("Digite o nome do bloco da sala: ");
				nome = input.nextLine();
				bloco = inst.getColBlo().pesquisaPeloNome(nome);
				if(bloco == null)
				{
					System.out.println("Não há bloco cadastrado com esse nome!");
					return 0;
				}
				else
				{
					System.out.println("Digite o nome da sala a ser cadastrada: ");
					nome = input.nextLine();
					sala = new Sala(nome);
					if(!bloco.getColSal().adicionaSala(sala))
					{
						System.out.println("Esta sala já foi cadastrada neste bloco: ");
						return 0;
					}
				}
				System.out.println("Sala cadastrada com sucesso!");
			return 1;
			case 2:
				System.out.println("Digite o nome do bloco a ser consultado: ");
				nome = input.nextLine();
				bloco = inst.getColBlo().pesquisaPeloNome(nome);
				if(bloco == null)
				{
					System.out.println("Não há bloco cadastrado com esse nome!");
					return 0;
				}
				else
				{
					if(bloco.getColSal().size() == 0)
					{
						System.out.println("Ainda não há salas cadastradas nesse bloco!");
						return 0;
					}
					else
					{
						bloco.getColSal().listagemSalas();
					}
				}
			return 1;
			case 3:
				System.out.println("Digite o nome do bloco a ser consultado: ");
				nome = input.nextLine();
				bloco = inst.getColBlo().pesquisaPeloNome(nome);
				if(bloco == null)
				{
					System.out.println("Não há bloco cadastrado com esse nome!");
					return 0;
				}
				else
				{
					System.out.println("Digite o nome da sala a ser desvinculada: ");
					nome = input.nextLine();
					sala = bloco.getColSal().pesquisaPeloNome(nome);
					if(bloco.getColSal().removeSala(sala))
					{
						System.out.println("Ainda não há uma sala cadastrada com esse nome!");
						return 0;
					}
				}
				System.out.println("Sala desvinculada com sucesso!");
			return 1;
			case 4:
				System.out.println("Digite o nome do bloco a ser consultado: ");
				nome = input.nextLine();
				bloco = inst.getColBlo().pesquisaPeloNome(nome);
				if(bloco == null)
				{
					System.out.println("Não há bloco cadastrado com esse nome!");
					return 0;
				}
				else
				{
					System.out.println("Número de salas cadastradas nesse bloco: " + bloco.getColSal().size());
				}
			return 1;
		}
		return 2;
	}
	
	public static int menuDispositivos(Scanner input, Instituicao inst) throws IOException
	{
		if(inst == null)
		{
			System.out.println("Ainda não há uma instituição cadastrada!");
			return 2;
		}
		else
		{
			if(inst.getColBlo().size() == 0)
			{
				System.out.println("Ainda não há nenhum bloco cadastrado!");
				return 2;
			}
		}
		System.out.println("Escolha uma das opções abaixo\n"
		 		 		 + "1 - Registrar Dispositivos de uma Sala\n"
		 		 		 + "2 - Registrar Dispositivos de Todas as Salas\n"
		 		 		 + "3 - Atualizar Dispositivos de uma Sala\n"
		 		 		 + "4 - Atualizar Dispositivos de Todas as Salas\n"
		 		 		 + "5 - Retornar ao Menu Anterior");
		/*ColecaoDispositivos coldis = new ColecaoDispositivos();
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
			String nome = InetAddress.getByName(IP).getCanonicalHostName().replaceAll("/", "");
			if(nome.equals(IP))
			{
				Dispositivo dispositivo = new Dispositivo(MAC, IP, desligar);
				if(coldis.adicionaDispositivo(dispositivo))
				{
					System.out.println("Dispositivo já foi registrado");
				}
			}
			else
			{
				Dispositivo dispositivo = new Dispositivo(MAC, nome, IP, desligar);
				if(coldis.adicionaDispositivo(dispositivo))
				{
					System.out.println("Dispositivo já foi registrado");
				}
			}
			saida = new DataOutputStream(cliente.getOutputStream());
			saida.writeBoolean(!desligar);
		}*/
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

/*ColecaoDispositivos coldis = new ColecaoDispositivos();
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
	String nome = InetAddress.getByName(IP).getCanonicalHostName().replaceAll("/", "");
	if(nome.equals(IP))
	{
		Dispositivo dispositivo = new Dispositivo(MAC, IP, desligar);
		if(coldis.adicionaDispositivo(dispositivo))
		{
			System.out.println("Dispositivo já foi registrado");
		}
	}
	else
	{
		Dispositivo dispositivo = new Dispositivo(MAC, nome, IP, desligar);
		if(coldis.adicionaDispositivo(dispositivo))
		{
			System.out.println("Dispositivo já foi registrado");
		}
	}
	saida = new DataOutputStream(cliente.getOutputStream());
	saida.writeBoolean(!desligar);
}*/