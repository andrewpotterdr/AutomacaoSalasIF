package registradores;

public class Datashow implements Dispositivo
{
	private String nome;
	private boolean status;
	
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
	
	public String toConteudo()
	{
		return "DATASHOW\r\n" + nome + "\r\n" + status + "\r\n";
	}
}