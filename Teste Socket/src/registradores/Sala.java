package registradores;

import java.io.Serializable;

/**
 * @authors Pablo Bezerra Guedes Lins de Albuquerque e Michael Almeida da Franca Monteiro
 * @version 1.0
 * Classe que representa uma sala.
 */

public class Sala implements Serializable
{
	
	private static final long serialVersionUID = 7370526043455390603L;
	private String nome;
	private ColecaoDispositivos coldis = null;
	
	/**
	 * Método construtor da classe.
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
	
	/**
	 * Método que retorna a coleção de dispositivos contida na sala corrente.
	 * @return ColecaoDispositivos
	 */
	
	public ColecaoDispositivos getColDis()
	{
		return this.coldis;
	}
	
	/**
	 * Método que retorna a quantidade de dispositivos existentes na sala.
	 * @return int
	 */
	
	public int qtdDispoitivos()
	{
		return coldis.size();
	}
	
	public String toString()
	{
		return "SALA\nNome: " + nome;
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
