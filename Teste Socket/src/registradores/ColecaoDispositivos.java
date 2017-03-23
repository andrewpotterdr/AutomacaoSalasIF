package registradores;

import java.util.Vector;

public class ColecaoDispositivos 
{
	private Vector<Dispositivo> dispositivos;
	
	public ColecaoDispositivos()
	{
		dispositivos = new Vector<Dispositivo> ();
	}
	
	public boolean adicionaDispositivo(Dispositivo dispositivo)
	{
		for(int i = 0; i < dispositivos.size(); i++)
		{
			if(dispositivos.get(i).equals(dispositivo))
			{
				dispositivos.remove(i);
				break;
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
			if(dispositivos.get(i).equals(dispositivo))
			{
				dispositivos.remove(dispositivo);
				return true;
			}
		}
		return false;
	}
	
	public Maquina pesquisaMaquina(String MAC)
	{
		for(int i = 0; i < dispositivos.size(); i++)
		{
			if(dispositivos.get(i) instanceof Maquina)
			{
				Maquina dispTemp = (Maquina)dispositivos.get(i);
				if(dispTemp.getMAC().equals(MAC))
				{
					return dispTemp;
				}
			}
		}
		return null;
	}
	
	public int size()
	{
		return dispositivos.size();
	}
	
	public boolean isMaquina(int i)
	{
		if(dispositivos.get(i) instanceof Maquina)
		{
			return true;
		}
		return false;
	}
	
	public int sizeMaquina()
	{
		int i;
		for(i = 0; i < dispositivos.size(); i++)
		{
			if(dispositivos.get(i) instanceof Maquina)
			{
				i++;
			}
		}
		return i;
	}
}
