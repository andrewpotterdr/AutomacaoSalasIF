package registradores;

public interface Dispositivo 
{	
	public String getNome();
	public void setNome(String nome);
	public boolean getStatus();
	public void setStatus(boolean status);
	public String toString();
	public boolean equals(Dispositivo dispositivo);
}
