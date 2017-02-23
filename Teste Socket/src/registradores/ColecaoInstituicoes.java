package registradores;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Vector;

public class ColecaoInstituicoes
{
	private Vector<Instituicao> instituicoes;
	
	public ColecaoInstituicoes()
	{
		instituicoes = new Vector<Instituicao>();
	}
	
	public boolean adicionaInstituicao(Instituicao inst)
	{
		for(int i = 0; i < instituicoes.size(); i++)
		{
			if(instituicoes.get(i).equals(inst))
			{
				return false;
			}
		}
		instituicoes.add(inst);
		return true;
	}
	
	public Instituicao getInst(int i)
	{
		return instituicoes.get(i);
	}
	
	public void limparColecao()
	{
		instituicoes = null;
	}
	
	public int listageminstituicoes()
	{
		int i;
		System.out.println("INSTITUIÇÕES");
		for(i = 0; i < instituicoes.size(); i++)
		{
			System.out.println(instituicoes.get(i).toString());
		}
		return i;
	}
	
	public boolean removeInstituicao(Instituicao inst)
	{
		for(int i = 0; i < instituicoes.size(); i++)
		{
			if(instituicoes.get(i).equals(inst))
			{
				instituicoes.remove(inst);
				return true;
			}
		}
		return false;
	}
	
	public int size()
	{
		return instituicoes.size();
	}
	
	public Instituicao procuraInst(Instituicao inst)
	{
		for(int i = 0; i < instituicoes.size(); i++)
		{
			if(instituicoes.get(i).equals(inst))
			{
				return instituicoes.get(i);
			}
		}
		return null;
	}
	
	public String toConteudo()
	{
		String conteudo = "|<\r\n";
		for(int i = 0; i < instituicoes.size(); i++)
		{
			conteudo += instituicoes.get(i).toConteudo();
		}
		conteudo += ">|\r\n";
		return conteudo;
	}
	
	public boolean gravaArquivo() throws Exception
	{
		
		String conteudo = toConteudo();
		String conteudoInterno = "";
		File file = new File("D:/Pen-Card 1Amway/IFPB/Projeto Automação das Salas/AutomacaoSalasIF/Exemplo Dados Salvos em Texto/conteudo.txt");
		FileReader reader = null;
		FileWriter writer = null;
		try
		{
			file.createNewFile();
		}
		catch(Exception e)
		{
			throw new Exception("Erro na criação de arquivo!");
		}
		try
		{
			reader = new FileReader(file);
			while(reader.ready())
			{
				conteudoInterno += (char)reader.read();
			}
			reader.close();
		}
		catch(Exception e)
		{
			throw new Exception("Erro na leitura de arquivo!");
		}
		try
		{
			if(!conteudoInterno.equals(conteudo))
			{
				writer = new FileWriter(file);
				writer.write(conteudo);
				writer.close();
			}
		}
		catch(Exception e)
		{
			throw new Exception("Erro na escrita de arquivo!");
		}
		return true;
	}
}
