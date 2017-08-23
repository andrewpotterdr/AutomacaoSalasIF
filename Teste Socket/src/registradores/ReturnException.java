package registradores;

/**
 * @author Pablo Bezerra Guedes Lins de Albuquerque e Michael Almeida da Franca Monteiro.
 * Classe que especifica uma exceção com atributo número de retorno.
 *
 */
public class ReturnException extends Exception 
{
	
	private static final long serialVersionUID = 6548203327430221985L;
	private String msg;
	private int ret;
	
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