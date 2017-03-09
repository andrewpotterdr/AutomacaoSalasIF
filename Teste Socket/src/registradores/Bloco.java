package registradores;
import java.io.Serializable;

public class Bloco implements Serializable
{
	private String nome;
	private ColecaoSalas colsal = null;
	
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
	
	public String toConteudo()
	{
		String conteudo = "BLOCO " + this.nome + "\r\n<\r\n";
		for(int i = 0; i < colsal.size(); i++)
		{
			conteudo += colsal.getSala(i).toConteudo();
		}
		conteudo += ">\r\n";
		return conteudo;
	}
}
