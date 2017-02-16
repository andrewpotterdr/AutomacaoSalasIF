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
		Instituicao inst = null;
		try
		{
			while(menu(input,inst) != 1);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	public static int menu(Scanner input, Instituicao inst) throws Exception
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
				try
				{
					res = menuInstituicoes(input,inst);
				}
				catch(Exception e)
				{
					if(e instanceof ReturnException)
					{
						res = ((ReturnException) e).getReturn();
					}
					throw new Exception(e.getMessage());
				}
				while(res != 2)
				{
					try
					{
						res = menuInstituicoes(input,inst);
					}
					catch(Exception e)
					{
						if(e instanceof ReturnException)
						{
							res = ((ReturnException) e).getReturn();
						}
						throw new Exception(e.getMessage());
					}
				}
			return 0;
			case 2:
				try
				{
					res = menuBlocos(input,inst);
				}
				catch(Exception e)
				{
					if(e instanceof ReturnException)
					{
						res = ((ReturnException) e).getReturn();
					}
					throw new Exception(e.getMessage());
				}
				while(res != 2)
				{
					try
					{
						res = menuBlocos(input,inst);
					}
					catch(Exception e)
					{
						if(e instanceof ReturnException)
						{
							res = ((ReturnException) e).getReturn();
						}
						throw new Exception(e.getMessage());
					}
				}
			return 0;
			case 3:
				try
				{
					res = menuSalas(input,inst);
				}
				catch(Exception e)
				{
					if(e instanceof ReturnException)
					{
						res = ((ReturnException) e).getReturn();
					}
					throw new Exception(e.getMessage());
				}
				while(res != 2)
				{
					try
					{
						res = menuSalas(input,inst);
					}
					catch(Exception e)
					{
						if(e instanceof ReturnException)
						{
							res = ((ReturnException) e).getReturn();
						}
						throw new Exception(e.getMessage());
					}
				}
			return 0;
			case 4:
				try
				{
					res = menuDispositivos(input,inst);
				}
				catch(Exception e)
				{
					if(e instanceof ReturnException)
					{
						res = ((ReturnException) e).getReturn();
					}
					throw new Exception(e.getMessage());
				}
				while(res != 2)
				{
					try
					{
						res = menuDispositivos(input,inst);
					}
					catch(Exception e)
					{
						if(e instanceof ReturnException)
						{
							res = ((ReturnException) e).getReturn();
						}
						throw new Exception(e.getMessage());
					}
				}
			return 0;
		}
		return 1;
	}
	
	public static int menuInstituicoes(Scanner input, Instituicao inst) throws Exception
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
					throw new Exception("A instituição já foi cadastrada!\nSe deseja cadastrar outra institução, você precisa desvincular a atualmente cadastrada.");
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
					throw new Exception("A instituição já foi cadastrada!\nSe deseja cadastrar outra institução, você precisa desvincular a atualmente cadastrada.");
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
					throw new Exception("A instituição ainda não foi cadastrada!");
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
					throw new Exception("Ainda não há uma instituição cadastrada!");
				}
			return 1;
		}
		return 2;
	}
	
	public static int menuBlocos(Scanner input, Instituicao inst) throws Exception
	{
		if(inst == null)
		{
			throw new ReturnException("Ainda não há uma instituição cadastrada!",2);
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
					throw new Exception("Este bloco já foi adicionado!");
				}
				System.out.println("Bloco adicionado com sucesso!");
			return 1;
			case 2:
				if(inst.getColBlo().size() == 0)
				{
					throw new Exception("Ainda não há nenhum bloco cadastrado na instituição!");
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
					throw new Exception("Não há nenhum bloco cadastrado com esse nome!");
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
	
	public static int menuSalas(Scanner input, Instituicao inst) throws Exception
	{
		if(inst == null)
		{
			throw new ReturnException("Ainda não há uma instituição cadastrada!",2);
		}
		else
		{
			if(inst.getColBlo().size() == 0)
			{
				throw new ReturnException("Ainda não há nenhum bloco cadastrado!",2);
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
					throw new Exception("Não há bloco cadastrado com esse nome!");
				}
				else
				{
					System.out.println("Digite o nome da sala a ser cadastrada: ");
					nome = input.nextLine();
					sala = new Sala(nome);
					if(!bloco.getColSal().adicionaSala(sala))
					{
						throw new Exception("Esta sala já foi cadastrada neste bloco: ");
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
					throw new Exception("Não há bloco cadastrado com esse nome!");
				}
				else
				{
					if(bloco.getColSal().size() == 0)
					{
						throw new Exception("Ainda não há salas cadastradas nesse bloco!");
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
					throw new Exception("Não há bloco cadastrado com esse nome!");
				}
				else
				{
					System.out.println("Digite o nome da sala a ser desvinculada: ");
					nome = input.nextLine();
					sala = bloco.getColSal().pesquisaPeloNome(nome);
					if(bloco.getColSal().removeSala(sala))
					{
						throw new Exception("Ainda não há uma sala cadastrada com esse nome!");
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
					throw new Exception("Não há bloco cadastrado com esse nome!");
				}
				else
				{
					System.out.println("Número de salas cadastradas nesse bloco: " + bloco.getColSal().size());
				}
			return 1;
		}
		return 2;
	}
	
	public static int menuDispositivos(Scanner input, Instituicao inst) throws Exception
	{
		Sala sala = null, salaAux = null;
		Bloco bloco = null;
		String nome;
		if(inst == null)
		{
			throw new ReturnException("Ainda não há uma instituição cadastrada!",2);
		}
		else
		{
			if(inst.getColBlo().size() == 0)
			{
				throw new ReturnException("Ainda não há nenhum bloco cadastrado!",2);
			}
			else
			{
				System.out.println("Digite o nome do bloco da sala: ");
				nome = input.nextLine();
				bloco = inst.getColBlo().pesquisaPeloNome(nome);
				if(bloco == null)
				{
					throw new Exception("Não há bloco cadastrado com esse nome!");
				}
			}
		}
		int qtdarc;
		int qtdpro;
		System.out.println("Escolha uma das opções abaixo\n"
		 		 		 + "1 - Registrar Dispositivos de uma Sala\n"
		 		 		 + "2 - Registrar Máquinas de Todas as Salas\n"
		 		 		 + "3 - Remover todas as Máquinas de uma Sala\n"
		 		 		 + "4 - Remover todos os Arcondicionados de uma Sala\n"
		 		 		 + "5 - Remover todos os Projetores de uma Sala\n"
		 		 		 + "6 - Retornar ao Menu Anterior");
		switch(lerOpcao(input,1,5))
		{
			case 1:
				System.out.println("Digite o nome da sala onde se encontram os dispositivos a serem registrados: ");
				nome = input.nextLine();
				sala = bloco.getColSal().pesquisaPeloNome(nome);
				if(sala == null)
				{
					throw new Exception("Sala não cadastrada!");
				}
				System.out.println("Registrar Máquinas.");
				System.out.println("Iniciado processo de registro de máquinas: ");
				try
				{
					RegistraMaquinas regmaq = new RegistraMaquinas();
					regmaq.run(sala.getColDis());
					(regmaq).start();
				}
				catch(Exception e)
				{
					throw new Exception(e.getMessage());
				}
				System.out.println("Registrar Arcondicionados.");
				System.out.println("Digite a quantidade de arcondicionados da sala: ");
				qtdarc = lerOpcao(input,0,1000);
				for(int i = 0; i < qtdarc; i++)
				{
					nome = "ARC" + sala.getNome() + ":" + i;
					boolean status = false;
					Dispositivo arc = new Arcondicionado(nome,status);
					sala.getColDis().adicionaDispositivo(arc);
				}
				System.out.println("Registrar Projetores.");
				System.out.println("Digite a quantidade de projetores da sala: ");
				qtdpro = lerOpcao(input,0,1000);
				for(int i = 0; i < qtdpro; i++)
				{
					nome = "PRO" + sala.getNome() + ":" + i;
					boolean status = false;
					Dispositivo pro = new Datashow(nome,status);
				}
				System.out.println("Dispositivos em Registro.");
			return 1;
			case 2:
				System.out.println("Registrar todas as Máquinas.");
				System.out.println("Iniciado processo de registro de todas as máquinas: ");
				try
				{
					int tamcolsal = bloco.getColSal().size();
					for(int i = 0; i < tamcolsal; i++)
					{
						salaAux = bloco.getColSal().getSala(i);
						RegistraMaquinas regmaq = new RegistraMaquinas();
						regmaq.run(salaAux.getColDis());
						regmaq.start();
					}
				}
				catch(Exception e)
				{
					throw new Exception(e.getMessage());
				}
			return 1;
			case 3:
				System.out.println("Digite o nome da sala onde se encontram as máquinas a serem removidas do sistema: ");
				nome = input.nextLine();
				sala = bloco.getColSal().pesquisaPeloNome(nome);
				if(sala == null)
				{
					throw new Exception("Sala não cadastrada!");
				}
				System.out.println("Apagando os registros de máquinas da sala " + sala.getNome() + "...");
				sala.getColDis().excluirMaquinas();
				System.out.println("Registros apagados com sucesso!");
			return 1;
			case 4:
				System.out.println("Digite o nome da sala onde se encontram os arcondicionados a serem removidos do sistema: ");
				nome = input.nextLine();
				sala = bloco.getColSal().pesquisaPeloNome(nome);
				if(sala == null)
				{
					throw new Exception("Sala não cadastrada!");
				}
				System.out.println("Apagando os registros de arcondicionados da sala " + sala.getNome() + "...");
				sala.getColDis().excluirArcondicionados();
				System.out.println("Registros apagados com sucesso!");
			return 1;
			case 5:
				System.out.println("Digite o nome da sala onde se encontram os projetores a serem removidos do sistema: ");
				nome = input.nextLine();
				sala = bloco.getColSal().pesquisaPeloNome(nome);
				if(sala == null)
				{
					throw new Exception("Sala não cadastrada!");
				}
				sala.getColDis().excluirDatashows();
				System.out.println("Registros apagados com sucesso!");
			return 1;
			case 6:
				return 2;
		}
		return 1;
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