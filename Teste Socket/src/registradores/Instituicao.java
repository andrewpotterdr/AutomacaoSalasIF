package registradores;

import java.io.Serializable;

/**
 * @authors Pablo Bezerra Guedes Lins de Albuquerque e Michael Almeida da Franca Monteiro
 * @version 1.0
 * Classe abstrata que representa uma instituição.
 */

public abstract class Instituicao implements Serializable
{
	
	private static final long serialVersionUID = -793901709382791416L;
	private String nome;
	private String cidade;
	private ColecaoBlocos colblo = null;
	
	/**
	 * Método construtor da classe.
	 * @param nome
	 * @param cidade
	 */
	
	public Instituicao(String nome, String cidade)
	{
		this.nome = nome;
		this.cidade = cidade;
		this.colblo = new ColecaoBlocos();
	}
	
	public String getNome()
	{
		return this.nome;
	}
	
	public void setNome(String nome)
	{
		this.nome = nome;
	}
	
	public String getCidade()
	{
		return this.cidade;
	}
	
	/**
	 * Método que retorna a coleção de blocos de salas contida na instituição corrente.
	 * @return ColecaoBlocos
	 */
	
	public ColecaoBlocos getColBlo()
	{
		return this.colblo;
	}
	
	/**
	 * Método que retorna a quantidade de blocos de salas contidos na instituição corrente.
	 * @return int
	 */
	
	public int qtdBlocos()
	{
		return colblo.size();
	}
	
	public String toString()
	{
		return "INSTITUIÇÃO\nNome: " + nome + "\nCidade: " + cidade;
	}
	
	public boolean equals(Instituicao instituicao)
	{
		if(this.nome.equals(instituicao.getNome()) && this.cidade.equals(instituicao.getCidade()))
		{
			return true;
		}
		return false;
	}
}
