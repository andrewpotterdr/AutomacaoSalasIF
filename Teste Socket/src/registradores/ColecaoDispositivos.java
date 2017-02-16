package registradores;

import java.util.ArrayList;

public class ColecaoDispositivos
{
	private ArrayList<Dispositivo> dispositivos;
	
	public ColecaoDispositivos()
	{
		dispositivos = new ArrayList<Dispositivo> ();
	}
	
	public boolean adicionaDispositivo(Dispositivo dispositivo)
	{
		for(int i = 0; i < dispositivos.size(); i++)
		{
			if(!dispositivos.get(i).equals(dispositivo))
			{
				return false;
			}
		}
		dispositivos.add(dispositivo);
		return true;
	}
	
	public Dispositivo getDispositivo(int i)
	{
		return dispositivos.get(i);
	}
	
	public void limparColecao()
	{
		dispositivos = null;
	}
	
	public void excluirMaquinas()
	{
		for(int i = dispositivos.size() - 1; i >= 0; i--)
		{
			if(dispositivos.get(i) instanceof Maquina)
			{
				removeDispositivo(dispositivos.get(i));
			}
		}
	}
	
	public void excluirArcondicionados()
	{
		for(int i = dispositivos.size() - 1; i >= 0; i--)
		{
			if(dispositivos.get(i) instanceof Arcondicionado)
			{
				removeDispositivo(dispositivos.get(i));
			}
		}
	}
	
	public void excluirDatashows()
	{
		for(int i = dispositivos.size() - 1; i >= 0; i--)
		{
			if(dispositivos.get(i) instanceof Datashow)
			{
				removeDispositivo(dispositivos.get(i));
			}
		}
	}
	
	public int listagemDispositivos()
	{
		int i;
		for(i = 0; i < dispositivos.size(); i++)
		{
			System.out.println(dispositivos.get(i).toString());
		}
		return i;
	}
	
	public boolean removeDispositivo(Dispositivo dispositivo)
	{
		for(int i = 0; i < dispositivos.size(); i++)
		{
			if(!dispositivos.get(i).equals(dispositivo))
			{
				return false;
			}
		}
		dispositivos.remove(dispositivo);
		return true;
	}
	
	public int size()
	{
		return dispositivos.size();
	}
}
