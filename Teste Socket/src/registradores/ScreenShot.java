package registradores;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Base64.Encoder;

import org.apache.commons.io.FileUtils;

public class ScreenShot implements Serializable
{
	private static final long serialVersionUID = -2945122000176677220L;
	
	Encoder base64 = null;
	byte [] img;
	public ScreenShot(File img) throws IOException
	{
		this.img = base64.encode(FileUtils.readFileToByteArray(img));
	}
	
	public byte[] getImg()
	{
		return this.img;
	}
	
	public void setImg(File img) throws IOException
	{
		this.img = base64.encode(FileUtils.readFileToByteArray(img));
	}
}
