package registradores;

import java.io.Serializable;

public class Stringo implements Serializable
{
	private static final long serialVersionUID = 8398298832055274405L;
	
	String s = null;
	public Stringo(String s)
	{
		this.s = s;
	}
	
	public String getStringo()
	{
		return this.s;
	}
}
