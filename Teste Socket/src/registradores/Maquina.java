package registradores;

import java.io.Serializable;

/**
 * @author Pablo Bezerra Guedes Lins de Albuquerque e Michael Almeida da Franca Monteiro
 * @version 1.0
 * Classe que representa uma máquina.
 */

public class Maquina implements Dispositivo, Serializable
{
	
	private static final long serialVersionUID = 7632605201988475337L;
	private String nome;
	private String MAC;
	private String IP;
	private boolean status;
	
	public Maquina(String nome, String MAC, String IP, boolean status) 
	{
		this.nome = nome;
		this.MAC = MAC;
		this.IP = IP;
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
	
	public String getMAC() 
	{
		return this.MAC;
	}
	
	public void setMAC(String MAC) 
	{
		this.MAC = MAC;
	}
	
	public String getIP() 
	{
		return this.IP;
	}
	
	public void setIP(String IP) 
	{
		this.IP = IP;
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
		return "Máquina\nNome: " + this.nome + "\nMAC: " + MAC + "\nIP: " + IP + "\nStatus: " + status + "\n";
	}
	
	public boolean equals(Dispositivo maquina)
	{
		if(this.MAC.equals(((Maquina)maquina).getMAC()))
		{
			return true;
		}
		return false;
	}
}
