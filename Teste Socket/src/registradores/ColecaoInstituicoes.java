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
	
	@SuppressWarnings({ "unchecked" })
	public void recuperaArquivo() throws Exception
	{
		File file;
		FileInputStream fin;
		ObjectInputStream oin;
		try
		{
			file = new File("D:/Pen-Card Amway/IFPB/Projeto Automação das Salas/AutomacaoSalasIF/Exemplo Dados Salvos em Texto/conteudo.dat");
			if(file.exists())
			{
				fin = new FileInputStream(file);
				oin = new ObjectInputStream(fin);
				if(oin.readObject() != null)
				{
					instituicoes = (Vector<Instituicao>) oin.readObject();
				}
				else
				{
					instituicoes = new Vector<Instituicao>();
				}
				oin.close();
				fin.close();
			}
		}
		catch(Exception e)
		{
			throw new Exception(e.getMessage());
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
