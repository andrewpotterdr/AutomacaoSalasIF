package registradores;

public abstract class Dispositivo
{
	private String nome;
	private String MAC;
	private String IP;
	private boolean status;
	
	public Dispositivo(String nome, String MAC, String IP, boolean status)
	{
		this.MAC = MAC;
		this.nome = nome;
		this.IP = IP;
		this.status = status;
	}
	
	public Dispositivo(String MAC, String IP, boolean status)
	{
		this.MAC = MAC;
		this.IP = IP;
		this.status = status;
	}
	
	public String getNome()
	{
		return this.nome;
	}
	
	public String getMAC()
	{
		return this.MAC;
	}
	
	public String getIP()
	{
		return this.IP;
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
		return "Dispositivo:\nIP: " + IP;
	}
	
	public boolean equals(Dispositivo dispositivo)
	{
		if(this.IP.equals(dispositivo.getIP()) && this.nome.equals(dispositivo.getNome()) && this.MAC.equals(dispositivo.getMAC()))
		{
			return true;
		}
		return false;
	}
}
