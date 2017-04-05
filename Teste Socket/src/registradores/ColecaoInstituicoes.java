package registradores;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Vector;

public class ColecaoInstituicoes implements Serializable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7663710807871190013L;
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
	
	@SuppressWarnings({ "unchecked" })
	public void recuperaArquivo() throws Exception
	{
		File file;
		FileInputStream fin;
		ObjectInputStream oin;
		try
		{
			file = new File("D:/Pen-Card Amway/IFPB/Projeto Automação das Salas/AutomacaoSalasIF/Exemplo Dados Salvos em Texto/conteudo.dat");
			System.out.println("1");
			if(file.exists())
			{
				System.out.println("2");
				fin = new FileInputStream(file);
				System.out.println("3");
				oin = new ObjectInputStream(fin);
				System.out.println("4");
				Vector<Instituicao> vector = (Vector<Instituicao>)oin.readObject();
				System.out.println("5");
				if(vector != null)
				{
					System.out.println("6");
					instituicoes = vector;
					System.out.println("7");
				}
				else
				{
					System.out.println("8");
					instituicoes = new Vector<Instituicao>();
					System.out.println("9");
				}
				System.out.println("10");
				oin.close();
				fin.close();
			}
		}
		catch(Exception e)
		{
			throw new Exception("Deu merda");
		}
	}
	
	public void gravaArquivo() throws Exception
	{
		File file;
		FileOutputStream fout;
		ObjectOutputStream oout;
		try
		{
			file = new File("D:/Pen-Card Amway/IFPB/Projeto Automação das Salas/AutomacaoSalasIF/Exemplo Dados Salvos em Texto/conteudo.dat");
			fout = new FileOutputStream(file);
			oout = new ObjectOutputStream(fout);
			file.createNewFile();
			oout.writeObject(instituicoes);
			oout.close();
			fout.close();
		}
		catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
	}
}
