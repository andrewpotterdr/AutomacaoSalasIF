package registradores;

import java.io.IOException;
import java.util.Scanner;

/**
 * @author Pablo Bezerra Guedes Lins de Albuquerque e Michael Almeida da Franca Monteiro.
 * Classe referente ao registrador, onde recupera o arquivo salvo e chama o método menu.
 *
 */
public class Registrador
{
	public static void main(String[] args) throws IOException
	{
		Scanner input = new Scanner(System.in);
		ColecaoInstituicoes colinst;
		Atualiza update = null;
		colinst = new ColecaoInstituicoes();
		try
		{
			colinst.recuperaArquivo();
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
		}
		try
		{
			update = new Atualiza();
			update.start();
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
		}
		try
		{
			while(menu(input,colinst) != 1);
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
		}
	}
	
	/**
	 * Menu principal do registrador.
	 * @param input
	 * @param colinst
	 * @return int
	 * @throws Exception
	 */
	public static int menu(Scanner input, ColecaoInstituicoes colinst) throws Exception
	{
		
		int res;
		System.out.println("Escolha uma das opÃ§Ãµes abaixo: \n"
						 + "1 - Menu InstituiÃ§Ãµes\n"
						 + "2 - Menu Blocos\n"
						 + "3 - Menu Salas\n"
						 + "4 - Menu Dispositivos\n"
						 + "5 - Encerrar AplicaÃ§Ã£o");
		switch(lerOpcao(input,1,5))
		{
			case 1:
				try
				{
					res = menuInstituicoes(input,colinst);
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
						res = menuInstituicoes(input,colinst);
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
					res = menuBlocos(input,colinst);
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
						res = menuBlocos(input,colinst);
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
					res = menuSalas(input,colinst);
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
						res = menuSalas(input,colinst);
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
					res = menuDispositivos(input,colinst);
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
						res = menuDispositivos(input,colinst);
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
	
	/**
	 * Menu referente as opções de manipular as instituições.
	 * @param input
	 * @param colinst
	 * @return int
	 * @throws Exception
	 */
	public static int menuInstituicoes(Scanner input, ColecaoInstituicoes colinst) throws Exception
	{
		
		int opcao;
		Instituicao inst;
		String nome;
		String cidade;
		System.out.println("Escolha uma das opÃ§Ãµes abaixo\n"
						 + "1 - Cadastrar Instituicao de Ensino\n"
						 + "2 - Cadastrar Empresa\n"
						 + "3 - Alterar Nome da InstituiÃ§Ã£o\n"
						 + "4 - Desvincular instituiÃ§Ã£o\n"
						 + "5 - Retornar ao Menu Anterior");
		switch(lerOpcao(input,1,5))
		{
			case 1:
				System.out.println("Digite o nome da instituiÃ§Ã£o: ");
				nome = input.nextLine();
				System.out.println("Digite o nome da cidade: ");
				cidade = input.nextLine();
				System.out.println("Digite a qual Campus pertence a InstituiÃ§Ã£o: ");
				String campus = input.nextLine();
				inst = new InstituicaoEnsino(nome, cidade, campus);
				if(!colinst.adicionaInstituicao(inst))
				{
					throw new Exception("A insituiÃ§Ã£o jÃ¡ havia sido cadastrada!");
				}
				System.out.println("InstituiÃ§Ã£o de Ensino cadastrada com sucesso!");
			return 1;
			case 2:
				System.out.println("Digite o nome da instituiÃ§Ã£o: ");
				nome = input.nextLine();
				System.out.println("Digite o nome da cidade: ");
				cidade = input.nextLine();
				System.out.println("Digito o CNPJ da empresa: ");
				String CNPJ = input.nextLine();
				inst = new Empresa(nome, cidade, CNPJ);
				if(!colinst.adicionaInstituicao(inst))
				{
					throw new Exception("A instituiÃ§Ã£o jÃ¡ havia sido cadastrada!");
				}
				System.out.println("Empresa cadastrada com sucesso!");
			return 1;
			case 3:
				if(colinst.size() == 0)
				{
					throw new Exception("Ainda nÃ£o hÃ¡ uma instituiÃ§Ã£o cadastrada!");
				}
				System.out.println("Digite o nome da instituiÃ§Ã£o: ");
				nome = input.nextLine();
				System.out.println("Digite o nome da cidade: ");
				cidade = input.nextLine();
				System.out.println("A instituiÃ§Ã£o Ã© uma InstituiÃ§Ã£o de Ensino (0) ou uma Empresa (1)? (0 - InsituiÃ§Ã£o de Ensino | 1 - Empresa)");
				opcao = lerOpcao(input,0,1);
				if(opcao == 0)
				{
					System.out.println("Digite o campus da instituiÃ§Ã£o: ");
					campus = input.nextLine();
					inst = colinst.procuraInst(new InstituicaoEnsino(nome,cidade,campus));
				}
				else
				{
					System.out.println("Digite o CNPJ da empresa: ");
					CNPJ = input.nextLine();
					inst = colinst.procuraInst(new Empresa(nome,cidade,CNPJ));
				}
				if(inst == null)
				{
					throw new Exception("InstituiÃ§Ã£o nÃ£o cadastrada!");
				}
				System.out.println("Digite o nome para qual vai ser modificado a instituiÃ§Ã£o: ");
				nome = input.nextLine();
				inst.setNome(nome);
				System.out.println("Nome alterado com sucesso!");
			return 1;
			case 4:
				if(colinst.size() == 0)
				{
					throw new Exception("Ainda nÃ£o hÃ¡ uma instituiÃ§ao cadastrada!");
				}
				System.out.println("Digite o nome da instituiÃ§Ã£o: ");
				nome = input.nextLine();
				System.out.println("Digite o nome da cidade: ");
				cidade = input.nextLine();
				System.out.println("A instituiÃ§Ã£o Ã© uma InstituiÃ§Ã£o de Ensino (0) ou uma Empresa (1)? (0 - InsituiÃ§Ã£o de Ensino | 1 - Empresa)");
				opcao = lerOpcao(input,0,1);
				if(opcao == 0)
				{
					System.out.println("Digite o campus da instituiÃ§Ã£o: ");
					campus = input.nextLine();
					inst = colinst.procuraInst(new InstituicaoEnsino(nome,cidade,campus));
				}
				else
				{
					System.out.println("Digite o CNPJ da empresa: ");
					CNPJ = input.nextLine();
					inst = colinst.procuraInst(new Empresa(nome,cidade,CNPJ));
				}
				if(inst != null)
				{
					System.out.println("Tem certeza que deseja desvincular a instituicao " + inst.getNome() + "? ('1' - Sim/'0' - NÃ£o)");
					if(lerOpcao(input,0,1) == 1)
					{
						colinst.removeInstituicao(inst);
						System.out.println("InstituiÃ§Ã£o desvinculada com sucesso!");
					}
				}
				else
				{
					throw new Exception("Ainda nÃ£o hÃ¡ uma instituiÃ§Ã£o cadastrada!");
				}
			return 1;
		}
		return 2;
	}
	
	/**
	 * Menu referente as opções de manipulação do bloco.
	 * @param input
	 * @param colinst
	 * @return int
	 * @throws Exception
	 */
	public static int menuBlocos(Scanner input, ColecaoInstituicoes colinst) throws Exception
	{
		
		if(colinst.size() == 0)
		{
			throw new ReturnException("Ainda nÃ£o hÃ¡ uma instituiÃ§Ã£o cadastrada!",2);
		}
		String nome;
		String cidade;
		String campus;
		String CNPJ;
		int opcao;
		Instituicao inst = null;
		Bloco bloco = null;
		System.out.println("Escolha uma das opÃ§Ãµes abaixo\n"
				 + "1 - Adicionar Bloco\n"
				 + "2 - Listar Blocos\n"
				 + "3 - Remover Bloco\n"
				 + "4 - Mostrar Quantidade de Blocos\n"
				 + "5 - Retornar ao Menu Anterior");
		switch(lerOpcao(input,1,5))
		{
			case 1:
				System.out.println("Digite o nome da InstituiÃ§Ã£o a ser cadastrado o bloco: ");
				nome = input.nextLine();
				System.out.println("Digite a cidade onde a InstituiÃ§Ã£o se encontra: ");
				cidade = input.nextLine();
				System.out.println("A instituiÃ§Ã£o Ã© uma InstituiÃ§Ã£o de Ensino (0) ou uma Empresa (1)? (0 - InsituiÃ§Ã£o de Ensino | 1 - Empresa)");
				opcao = lerOpcao(input,0,1);
				if(opcao == 0)
				{
					System.out.println("Digite o campus da instituiÃ§Ã£o: ");
					campus = input.nextLine();
					inst = colinst.procuraInst(new InstituicaoEnsino(nome,cidade,campus));
				}
				else
				{
					System.out.println("Digite o CNPJ da empresa: ");
					CNPJ = input.nextLine();
					inst = colinst.procuraInst(new Empresa(nome,cidade,CNPJ));
				}
				if(inst == null)
				{
					throw new Exception("Esta instituiÃ§Ã£o nÃ£o foi cadastrada!");
				}
				System.out.println("Digite o nome do bloco a ser adicionado: ");
				nome = input.nextLine();
				bloco = new Bloco(nome);
				if(!inst.getColBlo().adicionabloco(bloco))
				{
					throw new Exception("Este bloco jÃ¡ foi adicionado!");
				}
				System.out.println("Bloco adicionado com sucesso!");
			return 1;
			case 2:
				System.out.println("Digite o nome da InstituiÃ§Ã£o a ser listado os blocos: ");
				nome = input.nextLine();
				System.out.println("Digite a cidade onde a InstituiÃ§Ã£o se encontra: ");
				cidade = input.nextLine();
				System.out.println("A instituiÃ§Ã£o Ã© uma InstituiÃ§Ã£o de Ensino (0) ou uma Empresa (1)? (0 - InsituiÃ§Ã£o de Ensino | 1 - Empresa)");
				opcao = lerOpcao(input,0,1);
				if(opcao == 0)
				{
					System.out.println("Digite o campus da instituiÃ§Ã£o: ");
					campus = input.nextLine();
					inst = colinst.procuraInst(new InstituicaoEnsino(nome,cidade,campus));
				}
				else
				{
					System.out.println("Digite o CNPJ da empresa: ");
					CNPJ = input.nextLine();
					inst = colinst.procuraInst(new Empresa(nome,cidade,CNPJ));
				}
				if(inst == null)
				{
					throw new Exception("Esta instituiÃ§Ã£o nÃ£o foi cadastrada!");
				}
				if(inst.getColBlo().size() == 0)
				{
					throw new Exception("Ainda nÃ£o hÃ¡ nenhum bloco cadastrado na instituiÃ§Ã£o!");
				}
				else
				{
					inst.getColBlo().listagemblocos();
				}
			return 1;
			case 3:
				System.out.println("Digite o nome da InstituiÃ§Ã£o a ser desvinculado o bloco: ");
				nome = input.nextLine();
				System.out.println("Digite a cidade onde a InstituiÃ§Ã£o se encontra: ");
				cidade = input.nextLine();
				System.out.println("A instituiÃ§Ã£o Ã© uma InstituiÃ§Ã£o de Ensino (0) ou uma Empresa (1)? (0 - InsituiÃ§Ã£o de Ensino | 1 - Empresa)");
				opcao = lerOpcao(input,0,1);
				if(opcao == 0)
				{
					System.out.println("Digite o campus da instituiÃ§Ã£o: ");
					campus = input.nextLine();
					inst = colinst.procuraInst(new InstituicaoEnsino(nome,cidade,campus));
				}
				else
				{
					System.out.println("Digite o CNPJ da empresa: ");
					CNPJ = input.nextLine();
					inst = colinst.procuraInst(new Empresa(nome,cidade,CNPJ));
				}
				if(inst == null)
				{
					throw new Exception("Esta instituiÃ§Ã£o nÃ£o foi cadastrada!");
				}
				System.out.println("Digite o nome do bloco a ser desvinculado: ");
				nome = input.nextLine();
				bloco = inst.getColBlo().pesquisaPeloNome(nome);
				if(bloco == null)
				{
					throw new Exception("NÃ£o hÃ¡ nenhum bloco cadastrado com esse nome!");
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
				System.out.println("Digite o nome da InstituiÃ§Ã£o a ser cadastrado o bloco: ");
				nome = input.nextLine();
				System.out.println("Digite a cidade onde a InstituiÃ§Ã£o se encontra: ");
				cidade = input.nextLine();
				System.out.println("A instituiÃ§Ã£o Ã© uma InstituiÃ§Ã£o de Ensino (0) ou uma Empresa (1)? (0 - InsituiÃ§Ã£o de Ensino | 1 - Empresa)");
				opcao = lerOpcao(input,0,1);
				if(opcao == 0)
				{
					System.out.println("Digite o campus da instituiÃ§Ã£o: ");
					campus = input.nextLine();
					inst = colinst.procuraInst(new InstituicaoEnsino(nome,cidade,campus));
				}
				else
				{
					System.out.println("Digite o CNPJ da empresa: ");
					CNPJ = input.nextLine();
					inst = colinst.procuraInst(new Empresa(nome,cidade,CNPJ));
				}
				if(inst == null)
				{
					throw new Exception("Esta instituiÃ§Ã£o nÃ£o foi cadastrada!");
				}
				System.out.println("Quantidade de blocos cadastrados: " + inst.getColBlo().size());
			return 1;
		}
		return 2;
	}
	
	/**
	 * Menu referente as opções de manipulações da sala.
	 * @param input
	 * @param colinst
	 * @return int
	 * @throws Exception
	 */
	public static int menuSalas(Scanner input, ColecaoInstituicoes colinst) throws Exception
	{
		
		if(colinst.size() == 0)
		{
			throw new ReturnException("Ainda nÃ£o hÃ¡ uma instituiÃ§Ã£o cadastrada!",2);
		}
		String nome;
		String cidade;
		String campus;
		String CNPJ;
		int opcao;
		Instituicao inst;
		Sala sala = null;
		Bloco bloco = null;
		System.out.println("Escolha uma das opÃ§Ãµes abaixo\n"
				 		 + "1 - Adicionar Sala\n"
				 		 + "2 - Listar Salas\n"
				 		 + "3 - Remover Sala\n"
				 		 + "4 - Mostrar Quantidade de Salas\n"
				 		 + "5 - Retornar ao Menu Anterior");
		switch(lerOpcao(input,1,5))
		{
			case 1:
				System.out.println("Digite o nome da InstituiÃ§Ã£o a ser cadastrado o bloco: ");
				nome = input.nextLine();
				System.out.println("Digite a cidade onde a InstituiÃ§Ã£o se encontra: ");
				cidade = input.nextLine();
				System.out.println("A instituiÃ§Ã£o Ã© uma InstituiÃ§Ã£o de Ensino (0) ou uma Empresa (1)? (0 - InsituiÃ§Ã£o de Ensino | 1 - Empresa)");
				opcao = lerOpcao(input,0,1);
				if(opcao == 0)
				{
					System.out.println("Digite o campus da instituiÃ§Ã£o: ");
					campus = input.nextLine();
					inst = colinst.procuraInst(new InstituicaoEnsino(nome,cidade,campus));
				}
				else
				{
					System.out.println("Digite o CNPJ da empresa: ");
					CNPJ = input.nextLine();
					inst = colinst.procuraInst(new Empresa(nome,cidade,CNPJ));
				}
				if(inst == null)
				{
					throw new Exception("Esta instituiÃ§Ã£o nÃ£o foi cadastrada!");
				}
				System.out.println("Digite o nome do bloco da sala: ");
				nome = input.nextLine();
				bloco = inst.getColBlo().pesquisaPeloNome(nome);
				if(bloco == null)
				{
					throw new Exception("NÃ£o hÃ¡ bloco cadastrado com esse nome!");
				}
				else
				{
					System.out.println("Digite o nome da sala a ser cadastrada: ");
					nome = input.nextLine();
					sala = new Sala(nome);
					if(!bloco.getColSal().adicionaSala(sala))
					{
						throw new Exception("Esta sala jÃ¡ foi cadastrada neste bloco: ");
					}
				}
				System.out.println("Sala cadastrada com sucesso!");
			return 1;
			case 2:
				System.out.println("Digite o nome da InstituiÃ§Ã£o a ser cadastrado o bloco: ");
				nome = input.nextLine();
				System.out.println("Digite a cidade onde a InstituiÃ§Ã£o se encontra: ");
				cidade = input.nextLine();
				System.out.println("A instituiÃ§Ã£o Ã© uma InstituiÃ§Ã£o de Ensino (0) ou uma Empresa (1)? (0 - InsituiÃ§Ã£o de Ensino | 1 - Empresa)");
				opcao = lerOpcao(input,0,1);
				if(opcao == 0)
				{
					System.out.println("Digite o campus da instituiÃ§Ã£o: ");
					campus = input.nextLine();
					inst = colinst.procuraInst(new InstituicaoEnsino(nome,cidade,campus));
				}
				else
				{
					System.out.println("Digite o CNPJ da empresa: ");
					CNPJ = input.nextLine();
					inst = colinst.procuraInst(new Empresa(nome,cidade,CNPJ));
				}
				if(inst == null)
				{
					throw new Exception("Esta instituiÃ§Ã£o nÃ£o foi cadastrada!");
				}
				System.out.println("Digite o nome do bloco a ser consultado: ");
				nome = input.nextLine();
				bloco = inst.getColBlo().pesquisaPeloNome(nome);
				if(bloco == null)
				{
					throw new Exception("NÃ£o hÃ¡ bloco cadastrado com esse nome!");
				}
				else
				{
					if(bloco.getColSal().size() == 0)
					{
						throw new Exception("Ainda nÃ£o hÃ¡ salas cadastradas nesse bloco!");
					}
					else
					{
						bloco.getColSal().listagemSalas();
					}
				}
			return 1;
			case 3:
				System.out.println("Digite o nome da InstituiÃ§Ã£o a ser cadastrado o bloco: ");
				nome = input.nextLine();
				System.out.println("Digite a cidade onde a InstituiÃ§Ã£o se encontra: ");
				cidade = input.nextLine();
				System.out.println("A instituiÃ§Ã£o Ã© uma InstituiÃ§Ã£o de Ensino (0) ou uma Empresa (1)? (0 - InsituiÃ§Ã£o de Ensino | 1 - Empresa)");
				opcao = lerOpcao(input,0,1);
				if(opcao == 0)
				{
					System.out.println("Digite o campus da instituiÃ§Ã£o: ");
					campus = input.nextLine();
					inst = colinst.procuraInst(new InstituicaoEnsino(nome,cidade,campus));
				}
				else
				{
					System.out.println("Digite o CNPJ da empresa: ");
					CNPJ = input.nextLine();
					inst = colinst.procuraInst(new Empresa(nome,cidade,CNPJ));
				}
				if(inst == null)
				{
					throw new Exception("Esta instituiÃ§Ã£o nÃ£o foi cadastrada!");
				}
				System.out.println("Digite o nome do bloco a ser consultado: ");
				nome = input.nextLine();
				bloco = inst.getColBlo().pesquisaPeloNome(nome);
				if(bloco == null)
				{
					throw new Exception("NÃ£o hÃ¡ bloco cadastrado com esse nome!");
				}
				else
				{
					System.out.println("Digite o nome da sala a ser desvinculada: ");
					nome = input.nextLine();
					sala = bloco.getColSal().pesquisaPeloNome(nome);
					if(bloco.getColSal().removeSala(sala))
					{
						throw new Exception("Ainda nÃ£o hÃ¡ uma sala cadastrada com esse nome!");
					}
				}
				System.out.println("Sala desvinculada com sucesso!");
			return 1;
			case 4:
				System.out.println("Digite o nome da InstituiÃ§Ã£o a ser cadastrado o bloco: ");
				nome = input.nextLine();
				System.out.println("Digite a cidade onde a InstituiÃ§Ã£o se encontra: ");
				cidade = input.nextLine();
				System.out.println("A instituiÃ§Ã£o Ã© uma InstituiÃ§Ã£o de Ensino (0) ou uma Empresa (1)? (0 - InsituiÃ§Ã£o de Ensino | 1 - Empresa)");
				opcao = lerOpcao(input,0,1);
				if(opcao == 0)
				{
					System.out.println("Digite o campus da instituiÃ§Ã£o: ");
					campus = input.nextLine();
					inst = colinst.procuraInst(new InstituicaoEnsino(nome,cidade,campus));
				}
				else
				{
					System.out.println("Digite o CNPJ da empresa: ");
					CNPJ = input.nextLine();
					inst = colinst.procuraInst(new Empresa(nome,cidade,CNPJ));
				}
				if(inst == null)
				{
					throw new Exception("Esta instituiÃ§Ã£o nÃ£o foi cadastrada!");
				}
				System.out.println("Digite o nome do bloco a ser consultado: ");
				nome = input.nextLine();
				bloco = inst.getColBlo().pesquisaPeloNome(nome);
				if(bloco == null)
				{
					throw new Exception("NÃ£o hÃ¡ bloco cadastrado com esse nome!");
				}
				else
				{
					System.out.println("NÃºmero de salas cadastradas nesse bloco: " + bloco.getColSal().size());
				}
			return 1;
		}
		return 2;
	}
	
	/**
	 * Menu referente as opções de manipulações das salas.
	 * @param input
	 * @param colinst
	 * @return int
	 * @throws Exception
	 */
	public static int menuDispositivos(Scanner input, ColecaoInstituicoes colinst) throws Exception
	{
		
		Sala sala = null;
		Bloco bloco = null;
		String nome;
		String cidade;
		String campus;
		String CNPJ;
		int opcao;
		Instituicao inst = null;
		if(colinst.size() == 0)
		{
			throw new ReturnException("Ainda nÃ£o hÃ¡ uma instituiÃ§Ã£o cadastrada!",2);
		}
		int qtdarc;
		int qtdpro;
		System.out.println("Escolha uma das opÃ§Ãµes abaixo\n"
		 		 		 + "1 - Registrar Dispositivos de uma Sala\n"
		 		 		 + "2 - Remover todas as MÃ¡quinas de uma Sala\n"
		 		 		 + "3 - Remover todos os Arcondicionados de uma Sala\n"
		 		 		 + "4 - Remover todos os Projetores de uma Sala\n"
		 		 		 + "5 - Retornar ao Menu Anterior");
		switch(lerOpcao(input,1,5))
		{
			case 1:
				System.out.println("Digite o nome da InstituiÃ§Ã£o a ser cadastrado o bloco: ");
				nome = input.nextLine();
				System.out.println("Digite a cidade onde a InstituiÃ§Ã£o se encontra: ");
				cidade = input.nextLine();
				System.out.println("A instituiÃ§Ã£o Ã© uma InstituiÃ§Ã£o de Ensino (0) ou uma Empresa (1)? (0 - InsituiÃ§Ã£o de Ensino | 1 - Empresa)");
				opcao = lerOpcao(input,0,1);
				if(opcao == 0)
				{
					System.out.println("Digite o campus da instituiÃ§Ã£o: ");
					campus = input.nextLine();
					inst = colinst.procuraInst(new InstituicaoEnsino(nome,cidade,campus));
				}
				else
				{
					System.out.println("Digite o CNPJ da empresa: ");
					CNPJ = input.nextLine();
					inst = colinst.procuraInst(new Empresa(nome,cidade,CNPJ));
				}
				if(inst == null)
				{
					throw new Exception("Esta instituiÃ§Ã£o nÃ£o foi cadastrada!");
				}
				System.out.println("Digite o nome do bloco da sala: ");
				nome = input.nextLine();
				bloco = inst.getColBlo().pesquisaPeloNome(nome);
				if(bloco == null)
				{
					throw new Exception("NÃ£o hÃ¡ bloco cadastrado com esse nome!");
				}
				System.out.println("Digite o nome da sala onde se encontram os dispositivos a serem registrados: ");
				nome = input.nextLine();
				sala = bloco.getColSal().pesquisaPeloNome(nome);
				if(sala == null)
				{
					throw new Exception("Sala nÃ£o cadastrada!");
				}
				//int porta = bloco.getColSal().atribuirPorta(sala);
				System.out.println("Registrar MÃ¡quinas.");
				System.out.println("Iniciado processo de registro de mÃ¡quinas: ");
				try
				{
					RegistraMaquinas regmaq = new RegistraMaquinas(colinst,sala.getColDis(), 60062); //porta
					regmaq.start();
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
					sala.getColDis().adicionaDispositivo(pro);
				}
				System.out.println("Dispositivos em Registro.");
			return 1;
			case 2:
				System.out.println("Digite o nome da InstituiÃ§Ã£o a ser cadastrado o bloco: ");
				nome = input.nextLine();
				System.out.println("Digite a cidade onde a InstituiÃ§Ã£o se encontra: ");
				cidade = input.nextLine();
				System.out.println("A instituiÃ§Ã£o Ã© uma InstituiÃ§Ã£o de Ensino (0) ou uma Empresa (1)? (0 - InsituiÃ§Ã£o de Ensino | 1 - Empresa)");
				opcao = lerOpcao(input,0,1);
				if(opcao == 0)
				{
					System.out.println("Digite o campus da instituiÃ§Ã£o: ");
					campus = input.nextLine();
					inst = colinst.procuraInst(new InstituicaoEnsino(nome,cidade,campus));
				}
				else
				{
					System.out.println("Digite o CNPJ da empresa: ");
					CNPJ = input.nextLine();
					inst = colinst.procuraInst(new Empresa(nome,cidade,CNPJ));
				}
				if(inst == null)
				{
					throw new Exception("Esta instituiÃ§Ã£o nÃ£o foi cadastrada!");
				}
				System.out.println("Digite o nome do bloco da sala: ");
				nome = input.nextLine();
				bloco = inst.getColBlo().pesquisaPeloNome(nome);
				if(bloco == null)
				{
					throw new Exception("NÃ£o hÃ¡ bloco cadastrado com esse nome!");
				}
				System.out.println("Digite o nome da sala onde se encontram as mÃ¡quinas a serem removidas do sistema: ");
				nome = input.nextLine();
				sala = bloco.getColSal().pesquisaPeloNome(nome);
				if(sala == null)
				{
					throw new Exception("Sala nÃ£o cadastrada!");
				}
				System.out.println("Apagando os registros de mÃ¡quinas da sala " + sala.getNome() + "...");
				sala.getColDis().excluirMaquinas();
				System.out.println("Registros apagados com sucesso!");
			return 1;
			case 3:
				System.out.println("Digite o nome da InstituiÃ§Ã£o a ser cadastrado o bloco: ");
				nome = input.nextLine();
				System.out.println("Digite a cidade onde a InstituiÃ§Ã£o se encontra: ");
				cidade = input.nextLine();
				System.out.println("A instituiÃ§Ã£o Ã© uma InstituiÃ§Ã£o de Ensino (0) ou uma Empresa (1)? (0 - InsituiÃ§Ã£o de Ensino | 1 - Empresa)");
				opcao = lerOpcao(input,0,1);
				if(opcao == 0)
				{
					System.out.println("Digite o campus da instituiÃ§Ã£o: ");
					campus = input.nextLine();
					inst = colinst.procuraInst(new InstituicaoEnsino(nome,cidade,campus));
				}
				else
				{
					System.out.println("Digite o CNPJ da empresa: ");
					CNPJ = input.nextLine();
					inst = colinst.procuraInst(new Empresa(nome,cidade,CNPJ));
				}
				if(inst == null)
				{
					throw new Exception("Esta instituiÃ§Ã£o nÃ£o foi cadastrada!");
				}
				System.out.println("Digite o nome do bloco da sala: ");
				nome = input.nextLine();
				bloco = inst.getColBlo().pesquisaPeloNome(nome);
				if(bloco == null)
				{
					throw new Exception("NÃ£o hÃ¡ bloco cadastrado com esse nome!");
				}
				System.out.println("Digite o nome da sala onde se encontram os arcondicionados a serem removidos do sistema: ");
				nome = input.nextLine();
				sala = bloco.getColSal().pesquisaPeloNome(nome);
				if(sala == null)
				{
					throw new Exception("Sala nÃ£o cadastrada!");
				}
				System.out.println("Apagando os registros de arcondicionados da sala " + sala.getNome() + "...");
				sala.getColDis().excluirArcondicionados();
				System.out.println("Registros apagados com sucesso!");
			return 1;
			case 4:
				System.out.println("Digite o nome da InstituiÃ§Ã£o a ser cadastrado o bloco: ");
				nome = input.nextLine();
				System.out.println("Digite a cidade onde a InstituiÃ§Ã£o se encontra: ");
				cidade = input.nextLine();
				System.out.println("A instituiÃ§Ã£o Ã© uma InstituiÃ§Ã£o de Ensino (0) ou uma Empresa (1)? (0 - InsituiÃ§Ã£o de Ensino | 1 - Empresa)");
				opcao = lerOpcao(input,0,1);
				if(opcao == 0)
				{
					System.out.println("Digite o campus da instituiÃ§Ã£o: ");
					campus = input.nextLine();
					inst = colinst.procuraInst(new InstituicaoEnsino(nome,cidade,campus));
				}
				else
				{
					System.out.println("Digite o CNPJ da empresa: ");
					CNPJ = input.nextLine();
					inst = colinst.procuraInst(new Empresa(nome,cidade,CNPJ));
				}
				if(inst == null)
				{
					throw new Exception("Esta instituiÃ§Ã£o nÃ£o foi cadastrada!");
				}
				System.out.println("Digite o nome do bloco da sala: ");
				nome = input.nextLine();
				bloco = inst.getColBlo().pesquisaPeloNome(nome);
				if(bloco == null)
				{
					throw new Exception("NÃ£o hÃ¡ bloco cadastrado com esse nome!");
				}
				System.out.println("Digite o nome da sala onde se encontram os projetores a serem removidos do sistema: ");
				nome = input.nextLine();
				sala = bloco.getColSal().pesquisaPeloNome(nome);
				if(sala == null)
				{
					throw new Exception("Sala nÃ£o cadastrada!");
				}
				sala.getColDis().excluirDatashows();
				System.out.println("Registros apagados com sucesso!");
			return 1;
		}
		return 2;
	}
	
	/**
	 * Método que faz um tratamento de entrada.
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