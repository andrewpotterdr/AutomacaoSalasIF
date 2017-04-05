package registradores;

import java.io.Serializable;

/**
 * @author Michael Almeida da Frana Monteiro e Pablo Bezerra Guedes Lins de Albuquerque.
 * Classe que faz  a abstração do datashow.
 */
public class Datashow implements Dispositivo, Serializable
{
	private static final long serialVersionUID = 3878983661396577350L;
	private String nome;
	private boolean status;
	
	/**
	 * Construtor que atribui os valores passados por parâmetros aos atributos locais.
	 * @param nome
	 * @param status
	 * 
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
