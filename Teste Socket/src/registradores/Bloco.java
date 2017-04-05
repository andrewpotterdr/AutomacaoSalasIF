package registradores;

import java.io.Serializable;

/**
 * @author Pablo e Michael
 * Classe que faz abstração do Bloco.
 *
 */
public class Bloco implements Serializable
{
	private static final long serialVersionUID = 4066449166305726761L;
	private String nome;
	private ColecaoSalas colsal = null;
	
	/**
	 * @param nome
	 * Construtor de Bloco.
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
