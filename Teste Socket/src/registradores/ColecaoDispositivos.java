package registradores;

import java.io.Serializable;
import java.util.Vector;

/**
 * @author Pablo Bezerra Guedes Lins de Albuquerque e Michael Almeida da Franca Monteiro.
 * Classe referente à coleção de objetos do tipo Dispositivos que é uma interface.
 */
public class ColecaoDispositivos implements Serializable 
{
	private static final long serialVersionUID = -2213523052812618945L;
	private Vector<Dispositivo> dispositivos;
	
	/**
	 * Construtor de ColecaoDispositivos
	 */
	public ColecaoDispositivos()
	{
		
		dispositivos = new Vector<Dispositivo> ();
	}
	
	/**
	 * adiciona um objeto do tipo Dispositivo, retorna true se a operação der certo e false se houver falha.
	 * @param dispositivo
	 * @return boolean.
	 */
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
	
	/**
	 * Seta a coleção como null limpando-a assim.
	 */
	public void limparColecao()
	{
		
		dispositivos = null;
	}
	
	/**
	 * Remove um Dispositivo do tipo Maquina.
	 */
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
	
	/** 
	 * Remove um Dispositivo do tipo Arcondicionado 
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
	 * Remove um Dispositivo do Tipo Datashow.
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
	 * Lista todos os dispositivos, retorna a quantidade de Dispositivos.
	 * @return int.
	 */
	public int listagemDispositivos()
	{
		int i;
		for(i = 0; i < dispositivos.size(); i++)
		{
			System.out.println(dispositivos.get(i).toString());
		}
		return i;
	}
	
	/**
	 * remove o Dispositivo especificado retorna true se a operação der certo e false se houver falha.
	 * @param dispositivo
	 * @return boolean.
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
	 * pesquisa Maquina pelo MAC, retorna Maquina caso exista ou null caso não exista.
	 * @param MAC
	 * @return Maquina.
	 */
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
	
	/**
	 * retorna o tamanho da coleção.
	 * @return int.
	 */
	public int size()
	{
		
		return dispositivos.size();
	}
	
	/**
	 * Verifica se o Dispositivo é Maquina.
	 * @param i
	 * @return Boolean
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
	 * @return int.
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
	 * @return ColecaoDispositivos.
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
		if(colmaq.size() == 0)
		{
			return null;
		}
		return colmaq;
	}
	
	/**
	 * Pesquisa o Dispositivo pelo nome, retorna um Dispositivo caso exista, caso não, retorna null.
	 * @param nome
	 * @return Dispositivo.
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
