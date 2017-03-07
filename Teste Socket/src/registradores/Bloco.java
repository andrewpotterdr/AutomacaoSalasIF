package registradores;

public class Bloco
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
		String conteudo = "\tBLOCO " + this.nome + "\r\n\t\t<\r\n\t";
		for(int i = 0; i < colsal.size(); i++)
		{
			conteudo += colsal.getSala(i).toConteudo();
		}
		conteudo += "\t>\r\n";
		return conteudo;
	}
}
