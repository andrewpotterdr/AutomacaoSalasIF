package Servidores;

public class Dispositivo
{
	private String ID;
	private String IP;
	private boolean status;
	
	public Dispositivo(String ID, String IP, boolean status)
	{
		this.ID = ID;
		this.IP = IP;
		this.status = status;
	}
	
	public Dispositivo(String IP, boolean status)
	{
		this.IP = IP;
		this.status = status;
	}
	
	public Dispositivo(String IP)
	{
		this.IP = IP;
	}
	
	public String getID()
	{
		return this.ID;
	}
	
	public String getIP()
	{
		return this.IP;
	}
	
	public boolean getStatus()
	{
		return this.status;
	}
	
	public void setID(String ID)
	{
		this.ID = ID;
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
		return "Dispositivo:\nID: " + ID + "\nIP: " + IP;
	}
	
	public boolean equals(Dispositivo dispositivo)
	{
		if(this.ID.equals(dispositivo.getID()) || this.IP.equals(dispositivo.getIP()))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
