package registradores;

public class Arcondicionado implements Dispositivo
{
	private String nome;
	private boolean status;
	
	public Arcondicionado(String nome, boolean status) 
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
		return "Arcondicionado\nStatus: " + status;
	}
	
	public boolean equals(Dispositivo arc)
	{
		if(this.nome.equals(arc.getNome()))
		{
			return true;
		}
		return false;
	}
	
	public String toConteudo()
	{
		return "ARCONDICIONADO\r\n" + nome + "\r\n" + status + "\r\n";
	}
}
