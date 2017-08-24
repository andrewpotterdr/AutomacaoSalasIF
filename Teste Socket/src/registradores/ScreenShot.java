package registradores;

import java.io.File;
import java.io.Serializable;

public class ScreenShot implements Serializable
{
	File img;
	public ScreenShot(File img)
	{
		this.img = img;
	}
	
	public File getImg()
	{
		return this.img;
	}
	
	public void setImg(File img)
	{
		this.img = img;
	}
}
