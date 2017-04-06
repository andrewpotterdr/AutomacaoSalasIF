package registradores;

import java.io.Serializable;

/**
 * @author Pablo Bezerra Guedes Lins de Albuquerque e Michael Almeida da Franca Monteiro
 * @version 1.0
 * Classe que representa um bloco de salas.
 */

public class Bloco implements Serializable
{
	
	private static final long serialVersionUID = 4066449166305726761L;
	private String nome;
	private ColecaoSalas colsal = null;
	
	/**
	 * Método construtor da classe.
	 * @param nome
	 */
	
	public Bloco(String nome)
	{
		this.nome = nome;
		colsal = new ColecaoSalas();
	}
	
	public String getNome()
	{
		return this.nome;
	}
	
	public ColecaoSalas getColSal()
	{
		return this.colsal;
	}
	
	/**
	 * Método que retorna a quantidade de salas existentes no bloco.
	 * @return int
	 */
	
	public int qtdSalas()
	{
		return colsal.size();
	}
	
	public String toString()
	{
		return "Bloco de " + nome;
	}
	
	public boolean equals(Bloco bloco)
	{
		if(this.nome.equals(bloco.getNome()))
		{
			return true;
		}
		return false;
	}
}
