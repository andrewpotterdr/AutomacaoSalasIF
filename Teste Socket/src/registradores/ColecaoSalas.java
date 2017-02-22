package registradores;

import java.util.Vector;

public class ColecaoSalas
{
	private Vector<Sala> salas;
	
	public ColecaoSalas()
	{
		salas = new Vector<Sala>();
	}
	
	public boolean adicionaSala(Sala sala)
	{
		for(int i = 0; i < salas.size(); i++)
		{
			if(salas.get(i).equals(sala))
			{
				return false;
			}
		}
		salas.add(sala);
		return true;
	}
	
	public Sala getSala(int i)
	{
		return salas.get(i);
	}
	
	public void limparColecao()
	{
		salas = null;
	}
	
	public int listagemSalas()
	{
		int i;
		for(i = 0; i < salas.size(); i++)
		{
			System.out.println(salas.get(i).toString());
		}
		return i;
	}
	
	public boolean removeSala(Sala sala)
	{
		for(int i = 0; i < salas.size(); i++)
		{
			if(!salas.get(i).equals(sala))
			{
				salas.remove(sala);
				return true;
			}
		}
		return false;
	}

	public int size()
	{
		return salas.size();
	}
	
	public Sala pesquisaPeloNome(String nome)
	{
		int i, flag = -1;
		for(i = 0; i < salas.size(); i++)
		{
			if(salas.get(i).getNome().equals(nome))
			{
				flag = i;
				break;
			}
		}
		if(flag != -1)
		{
			return salas.get(flag);
		}
		else
		{
			return null;
		}
	}
}
