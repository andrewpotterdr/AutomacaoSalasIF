package registradores;

import java.io.Serializable;
import java.util.Vector;

/**
 * @author Pablo Bezerra Guedes Lins de Albuquerque e Michael Almeida da Franca Monteiro
 * @version 1.0
 * Classe que representa uma coleção de salas.
 */

public class ColecaoSalas implements Serializable
{
	
	private static final long serialVersionUID = -1454612525743607061L;
	private Vector<Sala> salas;
	
	/**
	 * Método construtor da classe.
	 */
	
	public ColecaoSalas()
	{
		salas = new Vector<Sala>();
	}
	
	/**
	 * Método para adicionar uma sala à coleção de salas, retorna true se a operação for executada com sucesso, ou false caso contrário.
	 * @param sala
	 * @return boolean
	 */
	
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
	
	/**
	 * Método que retorna uma sala pelo seu índice na coleção.
	 * @param i
	 * @return Sala
	 */
	
	public Sala getSala(int i)
	{
		return salas.get(i);
	}
	
	/**
	 * Método que atribui uma coleção vazia à coleção atual.
	 */
	
	public void limparColecao()
	{
		salas = new Vector<Sala>();
	}
	
	/**
	 * Método que lista as salas contidas na coleção. Retorna o número de instituições contidas na coleção.
	 * @return int
	 */
	
	public int listagemSalas()
	{
		int i;
		System.out.println("SALAS");
		for(i = 0; i < salas.size(); i++)
		{
			System.out.println(salas.get(i).toString());
		}
		return i;
	}
	
	/**
	 * Método para remover uma sala da coleção de salas, retorna true se a operação for executada com sucesso, ou false caso contrário.
	 * @param sala
	 * @return boolean
	 */
	
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

	/**
	 * Método que retorna o tamanho da coleção, ou seja, o número de dispositivos dentro da mesma.
	 * @return int
	 */
	
	public int size()
	{
		return salas.size();
	}
	
	/**
	 * Método que retorna uma sala após pesquisa pelo nome passado por parâmetro.
	 * @param nome
	 * @return Sala
	 */
	
	public Sala pesquisaPeloNome(String nome)
	{
		int flag = pesquisaIndicePeloNome(nome);
		if(flag >= 0)
		{
			return getSala(flag);
		}
		return null;
	}
	
	/**
	 * Método que retorna o índice de uma sala na coleção, após pesquisa pelo nome passado por parâmetro.
	 * @param nome
	 * @return int
	 */
	
	public int pesquisaIndicePeloNome(String nome)
	{
		int i;
		for(i = 0; i < salas.size(); i++)
		{
			if(salas.get(i).getNome().equals(nome))
			{
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Método que retorna porta relacionada ao índice da sala na coleção, usada para padronização das portas usadas para servidores socket.
	 * @param sala
	 * @return int
	 */
	
	public int atribuirPorta(Sala sala)
	{
		int i = pesquisaIndicePeloNome(sala.getNome());
		return 60051 + i;
	}
}
