package registradores;

public class Sala
{
	private String nome;
	private ColecaoDispositivos coldis = null;
	
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
	
	public String toConteudo()
	{
		String conteudo = "SALA " + this.nome + "\r\n<\r\n";
		for(int i = 0; i < coldis.size(); i++)
		{
			conteudo += coldis.getDispositivo(i).toConteudo();
		}
		conteudo += ">\r\n";
		return conteudo;
	}
}
