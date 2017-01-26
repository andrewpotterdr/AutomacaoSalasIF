package Servidores;

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
}
