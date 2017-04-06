package registradores;

import java.io.Serializable;

/**
 * @author Pablo Bezerra Guedes Lins de Albuquerque e Michael Almeida da Franca Monteiro
 * @version 1.0
 * Classe que representa um data show.
 */

public class Datashow implements Dispositivo, Serializable
{

	private static final long serialVersionUID = 3878983661396577350L;
	private String nome;
	private boolean status;
	
	/**
	 * Método construtor da classe.
	 * @param nome
	 * @param status
	 */
	
	public Datashow(String nome, boolean status) 
	{
		this.nome = nome;
		this.status = status;
	}

	public String getNome()
	{
		return this.nome;
	}
	
	public void setNome(String nome)
	{
		this.nome = nome;
	}
	
	public boolean getStatus()
	{
		return this.status;
	}

	public void setStatus(boolean status) 
	{
		this.status = status;
	}

	public String toString() 
	{
		return "Datashow\nStatus: " + status;
	}
	
	public boolean equals(Dispositivo datashow)
	{
		if(this.nome.equals(datashow.getNome()))
		{
			return true;
		}
		return false;
	}
}
