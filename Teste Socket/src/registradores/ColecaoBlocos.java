package registradores;

import java.util.ArrayList;

public class ColecaoBlocos
{
	private ArrayList<Bloco> blocos;
	
	public ColecaoBlocos()
	{
		blocos = new ArrayList<Bloco>();
	}
	
	public boolean adicionabloco(Bloco bloco)
	{
		for(int i = 0; i < blocos.size(); i++)
		{
			if(!blocos.get(i).equals(bloco))
			{
				return false;
			}
		}
		blocos.add(bloco);
		return true;
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
			if(!blocos.get(i).equals(bloco))
			{
				return false;
			}
		}
		blocos.remove(bloco);
		return true;
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
