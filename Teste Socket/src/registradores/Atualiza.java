package registradores;

import java.io.DataInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Atualiza extends Thread
{
	ColecaoInstituicoes colinst = new ColecaoInstituicoes();
	
	public Atualiza(ColecaoInstituicoes colinst)
	{
		this.colinst = colinst;
	}
	
	public void run()
	{
		ServerSocket updater = null;
		ObjectOutputStream oout = null;
		ObjectInputStream oin = null;
		DataInputStream signin = null;
		Socket servidor = null;
		try
		{
			updater = new ServerSocket(51064);
			servidor = updater.accept();
			signin = new DataInputStream(servidor.getInputStream());
			oin = new ObjectInputStream(servidor.getInputStream());
			oout = new ObjectOutputStream(servidor.getOutputStream());
		}
		catch(Exception e)
		{
			System.err.println("dsfd");
		}
		try
		{
			while(true)
			{
				if(signin.readBoolean())
				{
					System.err.println("sfds0");
					//signin.close();
					colinst.recuperaArquivo();
					System.err.println("sfds1");
					//ColecaoDispositivos coldis = colinst.procuraInst((Instituicao)(oin.readObject())).getColBlo().pesquisaPeloNome(signin.readUTF()).getColSal().pesquisaPeloNome(signin.readUTF()).getColDis();
					Instituicao inst = colinst.procuraInst((InstituicaoEnsino)(oin.readObject()));
					System.err.println("sfds2");
					//oin.close();
					System.out.println(inst);
					Bloco bloco = inst.getColBlo().pesquisaPeloNome(signin.readUTF());
					System.err.println("sfds3");
					Sala sala = bloco.getColSal().pesquisaPeloNome(signin.readUTF());
					System.err.println("sfds4");
					//signin.close();
					ColecaoDispositivos coldis = sala.getColDis();
					System.err.println("sfds5");
					oout.writeObject(coldis);
					System.err.println("sfds6");
					//oout.close();
				}
			}
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
		}
	}
}
