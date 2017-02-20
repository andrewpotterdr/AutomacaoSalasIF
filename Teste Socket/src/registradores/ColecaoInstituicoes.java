package registradores;

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
}
