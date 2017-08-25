package registradores;

import java.io.Serializable;

/**
 * @author Pablo Bezerra Guedes Lins de Albuquerque e Michael Almeida da Franca Monteiro.
 * Classe que faz a abstração das salas.
 *
 */
public class Sala implements Serializable
{
	
	private static final long serialVersionUID = 7370526043455390603L;
	private String nome;
	private ColecaoDispositivos coldis = null;
	
	/**
	 * Construtor de Sala.
	 * @param nome
	 */
	public Sala(String nome)
	{
		
		this.nome = nome;
		coldis = new ColecaoDispositivos();
	}
	
	public String getNome()
	{
		return this.nome;
	}
	
	public ColecaoDispositivos getColDis()
	{
		return this.coldis;
	}
	
	/**
	 * Retorna a quantidade de Dispositivos.
	 * @return int
	 */
	public int qtdDispoitivos()
	{
		
		return coldis.size();
	}
	
	public String toString()
	{
		return "Sala: " + nome;
	}
	
	public boolean equals(Sala sala)
	{
		if(this.nome.equals(sala.getNome()))
		{
			return true;
		}
		return false;
	}
}
