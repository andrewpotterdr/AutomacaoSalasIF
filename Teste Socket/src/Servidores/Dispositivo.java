package Servidores;

public class Dispositivo
{
	private String IP;
	private boolean status;
	
	public Dispositivo(String IP, boolean status)
	{
		this.IP = IP;
		this.status = status;
	}
	
	public String getIP()
	{
		return this.IP;
	}
	
	public boolean getStatus()
	{
		return this.status;
	}
	
	public void setIP(String IP)
	{
		this.IP = IP;
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
		if(this.IP.equals(dispositivo.getIP()))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
