package registradores;

import java.io.Serializable;
import java.util.Vector;

public class ColecaoBlocos implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5589748364330376619L;
	private Vector<Bloco> blocos;
	
	public ColecaoBlocos()
	{
		blocos = new Vector<Bloco>();
	}
	
	public boolean adicionabloco(Bloco bloco)
	{
		for(int i = 0; i < blocos.size(); i++)
		{
			if(blocos.get(i).equals(bloco))
			{
				return false;
			}
		}
		blocos.add(bloco);
		return true;
	}
	
	public Bloco getBloco(int i)
	{
		return blocos.get(i);
	}
	
	public void limparColecao()
	{
		blocos = null;
	}
	
	public int listagemblocos()
	{
		int i;
		System.out.println("BLOCOS");
		for(i = 0; i < blocos.size(); i++)
		{
			System.out.println(blocos.get(i).toString());
		}
		return i;
	}
	
	public boolean removebloco(Bloco bloco)
	{
		for(int i = 0; i < blocos.size(); i++)
		{
			if(blocos.get(i).equals(bloco))
			{
				blocos.remove(bloco);
				return true;
			}
		}
		return false;
	}

	public int size()
	{
		return blocos.size();
	}
	
	public Bloco pesquisaPeloNome(String nome)
	{
		int i, flag = -1;
		for(i = 0; i < blocos.size(); i++)
		{
			if(blocos.get(i).getNome().equals(nome))
			{
				flag = i;
				break;
			}
		}
		if(flag != -1)
		{
			return blocos.get(flag);
		}
		else
		{
			return null;
		}
	}
}
