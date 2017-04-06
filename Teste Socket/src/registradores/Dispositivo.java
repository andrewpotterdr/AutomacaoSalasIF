package registradores;

/**
 * @authors Pablo Bezerra Guedes Lins de Albuquerque e Michael Almeida da Franca Monteiro
 * @version 1.0
 * Interface que representa um dispositivo.
 */

public interface Dispositivo 
{	
	public String getNome();
	public void setNome(String nome);
	public boolean getStatus();
	public void setStatus(boolean status);
	public String toString();
	public boolean equals(Dispositivo dispositivo);
}
