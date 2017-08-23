package registradores;

import java.io.Serializable;

/**
 * @author Michael Almeida da Franca Monteiro e Pablo Bezerra Guedes Lins de Albuquerque.
 * Classe
 */
public class Empresa extends Instituicao implements Serializable
{
	
	private static final long serialVersionUID = 7215584323856933459L;
	private String CNPJ;
	
	/**
	 * Construtor de Empresa.
	 * @param nome
	 * @param cidade
	 * @param CNPJ
	 */
	public Empresa(String nome, String cidade, String CNPJ)
	{
		
		super(nome, cidade);
		this.CNPJ = CNPJ;
	}
	
	public String getCNPJ()
	{
		return this.CNPJ;
	}
	
	public String toString()
	{
		return super.toString() + "\nCNPJ: " + CNPJ;
	}
	
	public boolean equals(Empresa empresa)
	{
		if(super.equals((Instituicao)empresa) && this.CNPJ.equals(empresa.getCNPJ()))
		{
			return true;
		}
		return false;
	}
}
