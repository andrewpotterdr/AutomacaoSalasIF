package registradores;

import java.io.Serializable;

public class InstituicaoEnsino extends Instituicao implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 49349387951519319L;
	private String campus;
	
	public InstituicaoEnsino(String nome, String cidade, String campus)
	{
		super(nome, cidade);
		this.campus = campus;
	}
	
	public String getCampus()
	{
		return this.campus;
	}
	
	public String toString()
	{
		return super.toString() + "\nCampus: " + campus;
	}
	
	public boolean equals(InstituicaoEnsino instituicaoEnsino)
	{
		if(super.equals((Instituicao)instituicaoEnsino) && this.campus.equals(instituicaoEnsino.getCampus()))
		{
			return true;
		}
		return false;
	}
}
