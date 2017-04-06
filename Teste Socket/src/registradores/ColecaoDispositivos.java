package registradores;

import java.io.Serializable;
import java.util.Vector;

/**
 * @authors Pablo Bezerra Guedes Lins de Albuquerque e Michael Almeida da Franca Monteiro
 * @version 1.0
 * Classe que representa uma coleção de dispositivos de uma sala.
 */

public class ColecaoDispositivos implements Serializable 
{
	
	private static final long serialVersionUID = -2213523052812618945L;
	private Vector<Dispositivo> dispositivos;
	
	/**
	 * Método construtor da classe.
	 */
	
	public ColecaoDispositivos()
	{
		dispositivos = new Vector<Dispositivo> ();
	}
	
	/**
	 * Método para adicionar um dispositivo à coleção de dispositivos, retorna true se a operação for executada com sucesso, ou false caso contrário.
	 * @param dispositivo
	 * @return boolean
	 */
	
	public boolean adicionaDispositivo(Dispositivo dispositivo)
	{
		for(int i = 0; i < dispositivos.size(); i++)
		{
			if(!isMaquina(i))
			{
				if(dispositivos.get(i).equals(dispositivo))
				{
					dispositivos.remove(i);
					break;
				}
			}
		}
		dispositivos.add(dispositivo);
		return true;
	}
	
	/**
	 * Método que retorna um dispositivo pelo seu índice na coleção.
	 * @param i
	 * @return Dispositivo
	 */
	
	public Dispositivo getDispositivo(int i)
	{
		return dispositivos.get(i);
	}
	
	/**
	 * Método que atribui uma coleção vazia à coleção atual.
	 */
	
	public void limparColecao()
	{
		dispositivos = new Vector<Dispositivo> ();
	}
	
	/**
	 * Método que remove todas as máquinas da coleção de dispositivos.
	 */
	
	public void excluirMaquinas()
	{
		for(int i = dispositivos.size() - 1; i >= 0; i--)
		{
			if(isMaquina(i))
			{
				removeDispositivo(dispositivos.get(i));
			}
		}
	}
	
	/**
	 * Método que remove todos os ares-condicionados da coleção de dispositivos.
	 */
	
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
	
	/**
	 * Método que remove todos os Data Shows da coleção de dispositivos.
	 */
	
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
	
	/**
	 * Método que lista os dispositivos contidos na coleção. Retorna o número de dispositivos contidos na coleção.
	 * @return int
	 */
	
	public int listagemDispositivos()
	{
		int i;
		System.out.println("DISPOSITIVOS");
		for(i = 0; i < dispositivos.size(); i++)
		{
			System.out.println(dispositivos.get(i).toString());
		}
		return i;
	}
	
	/**
	 * Método para remover um dispositivo da coleção de dispositivos, retorna true se a operação for executada com sucesso, ou false caso contrário.
	 * @param dispositivo
	 * @return boolean
	 */
	
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
	
	/**
	 * Método que retorna uma máquina após feita pesquisa pelo endereço MAC passado por parâmetro.
	 * @param MAC
	 * @return Maquina
	 */
	
	public Maquina pesquisaMaquina(String MAC)
	{
		for(int i = 0; i < dispositivos.size(); i++)
		{
			if(isMaquina(i))
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
	
	/**
	 * Método que retorna o tamanho da coleção, ou seja, o número de dispositivos dentro da mesma.
	 * @return int
	 */
	
	public int size()
	{
		return dispositivos.size();
	}
	
	/**
	 * Método que retorna true se o índice passado por parâmetro for uma máquina, ou false caso contrário.
	 * @param i
	 * @return boolean
	 */
	
	public boolean isMaquina(int i)
	{
		if(dispositivos.get(i) instanceof Maquina)
		{
			return true;
		}
		return false;
	}
	
	/**
	 * Método que retorna quantas máquinas há na coleção de dispositivos.
	 * @return int
	 */
	
	public int sizeMaquina()
	{
		int j = 0;
		for(int i = 0; i < dispositivos.size(); i++)
		{
			if(isMaquina(i))
			{
				j++;
			}
		}
		return j;
	}
	
	/**
	 * Método que retorna uma coleção de dispositivos com apenas as máquinas da coleção corrente.
	 * @return ColecaoDispositivos
	 */
	
	public ColecaoDispositivos getColMaq()
	{
		ColecaoDispositivos colmaq = new ColecaoDispositivos();
		for(int i = 0; i < dispositivos.size(); i++)
		{
			if(isMaquina(i))
			{
				colmaq.adicionaDispositivo(dispositivos.get(i));
			}
		}
		return colmaq;
	}
	
	/**
	 * Método que retorna um dispositivo após pesquisa pelo nome passado por parâmetro.
	 * @param nome
	 * @return Dispositivo
	 */
	
	public Dispositivo pesquisaPeloNome(String nome)
	{
		for(int i = 0; i < dispositivos.size(); i++)
		{
			if(dispositivos.get(i).getNome().equals(nome))
			{
				return dispositivos.get(i);
			}
		}
		return null;
	}
}
