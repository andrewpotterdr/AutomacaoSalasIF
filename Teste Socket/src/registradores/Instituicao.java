package registradores;

import java.io.Serializable;

public abstract class Instituicao implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -793901709382791416L;
	private String nome;
	private String cidade;
	private ColecaoBlocos colblo = null;
	
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
	
	public ColecaoBlocos getColBlo()
	{
		return this.colblo;
	}
	
	public int qtdBlocos()
	{
		return colblo.size();
	}
	
	public String toString()
	{
		return "INSTITUIÇÃO\nNome: " + nome + "\nCidade" + cidade;
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
