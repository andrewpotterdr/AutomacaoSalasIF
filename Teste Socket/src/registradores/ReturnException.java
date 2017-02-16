package registradores;

public class ReturnException extends Exception
{
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