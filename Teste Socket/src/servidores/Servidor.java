package servidores;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Scanner;
import registradores.*;
 
public class Servidor
{
	public static void main(String[] args) throws IOException
	{
		ColecaoInstituicoes colinst = null;
		Instituicao instituicaoLocal = new InstituicaoEnsino("ifpb","jp","jp"), instituicaoReg = null;
		Bloco blocoLocal = new Bloco("info"), blocoReg = null;
		Sala salaLocal = new Sala("info01"), salaReg = null;
		Scanner input = new Scanner(System.in);
		Socket servidor;
		ObjectInputStream oin = null;
		try
		{
			servidor = new Socket("192.0.23.234", 51998);
			oin = new ObjectInputStream(servidor.getInputStream());
			while(true)
			{
				colinst = (ColecaoInstituicoes) oin.readObject();
				instituicaoReg = colinst.procuraInst(instituicaoLocal);
				blocoReg = instituicaoReg.getColBlo().pesquisaPeloNome(blocoLocal.getNome());
				salaReg = blocoReg.getColSal().pesquisaPeloNome(salaLocal.getNome());
				menuDisp(salaReg.getColDis(), input);
			}
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
		}
	}
	
	private static void menuDisp(ColecaoDispositivos coldis, Scanner input)
	{
		Maquina maq = null;
		ColecaoDispositivos maquinas = new ColecaoDispositivos();
		for(int i = 0; i < coldis.sizeMaquina(); i++)
		{
			if(coldis.isMaquina(i))
			{
				maquinas.adicionaDispositivo((Maquina) coldis.getDispositivo(i));
			}	
		}
	}
	
	private static int lerOpcao(Scanner input, int iniciall, int finall)
	{
		int opcao;
		if(!input.hasNextInt())
		{
			System.out.printf("Digite um número válido: \n");
			input.nextLine();
			return lerOpcao(input,iniciall,finall);
		}
		opcao = input.nextInt();
		input.nextLine();
		if(opcao < iniciall || opcao > finall)
		{
			System.out.printf("Digite um número entre '" + iniciall + "' e '" + finall + "' : \n");
			return lerOpcao(input,iniciall,finall);
		}
		return opcao;
	}

}