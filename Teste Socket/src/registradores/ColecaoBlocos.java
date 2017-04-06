package registradores;

import java.io.Serializable;
import java.util.Vector;

/**
 * @authors Pablo Bezerra Guedes Lins de Albuquerque e Michael Almeida da Franca Monteiro
 * @version 1.0
 * Classe que representa uma coleção de blocos de salas.
 */

public class ColecaoBlocos implements Serializable
{
	
	private static final long serialVersionUID = -5589748364330376619L;
	private Vector<Bloco> blocos;
	
	/**
	 * Método construtor da classe.
	 */
	
	public ColecaoBlocos()
	{
		blocos = new Vector<Bloco>();
	}
	
	/**
	 * Método para adicionar um bloco à coleção de blocos, retorna true se a operação for executada com sucesso, ou false caso contrário.
	 * @param bloco
	 * @return boolean
	 */
	
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
	
	/**
	 * Método que retorna um bloco pelo seu índice na coleção.
	 * @param i
	 * @return Bloco
	 */
	
	public Bloco getBloco(int i)
	{
		return blocos.get(i);
	}
	
	/**
	 * Método que atribui uma coleção vazia à coleção atual.
	 */
	
	public void limparColecao()
	{
		blocos = new Vector<Bloco>();
	}
	
	/**
	 * Método que lista os blocos contidos na coleção. Retorna o número de blocos contidos na coleção.
	 * @return int
	 */
	
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
	
	/**
	 * Método para remover um bloco da coleção de blocos, retorna true se a operação for executada com sucesso, ou false caso contrário.
	 * @param bloco
	 * @return boolean
	 */
	
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
	
	/**
	 * Método que retorna o tamanho da coleção, ou seja, o número de blocos dentro da mesma.
	 * @return int
	 */

	public int size()
	{
		return blocos.size();
	}
	
	/**
	 * Método que retorna um bloco baseado na pesquisa pelo nome do mesmo.
	 * @param nome
	 * @return Bloco
	 */
	
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
