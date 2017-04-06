package registradores;

/**
 * @authors Pablo Bezerra Guedes Lins de Albuquerque e Michael Almeida da Franca Monteiro
 * @version 1.0
 * Classe que extende de Exception, utilizada para ter um campo adicional com um número inteiro usado para retorno.
 */

public class ReturnException extends Exception 
{
	
	private static final long serialVersionUID = 6548203327430221985L;
	private String msg;
	private int ret;
	
	/**
	 * Método construtor da classe.
	 * @param msg
	 * @param ret
	 */
	
	public ReturnException(String msg, int ret)
	{
		this.msg = msg;
		this.ret = ret;
	}
	
	public String getMessage()
	{
		return this.msg;
	}
	
	public int getReturn()
	{
		return this.ret;
	}
	
	public void setMessage(String msg)
	{
		this.msg = msg; 
	}
	
	public void setReturn(int ret)
	{
		this.ret = ret;
	}
	
	public String toString()
	{
		return "Return Exception:\nMessagem: " + msg + "\nRetorno: " + ret;
	}
	
	public boolean equals(ReturnException e)
	{
		if(this.msg.equals(e.getMessage()) && this.ret==e.getReturn())
		{
			return true;
		}
		return false;
	}
}