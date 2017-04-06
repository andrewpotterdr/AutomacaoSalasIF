package registradores;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Vector;

/**
 * @author Pablo Bezerra Guedes Lins de Albuquerque e Michael Almeida da Franca Monteiro
 * @version 1.0
 * Classe que representa uma coleção de instituições.
 */

public class ColecaoInstituicoes implements Serializable 
{
	
	private static final long serialVersionUID = 7663710807871190013L;
	private Vector<Instituicao> instituicoes;
	
	/**
	 * Método construtor da classe.
	 */
	
	public ColecaoInstituicoes()
	{
		instituicoes = new Vector<Instituicao>();
	}
	
	/**
	 * Método para adicionar uma instituição à coleção de instituições, retorna true se a operação for executada com sucesso, ou false caso contrário.
	 * @param inst
	 * @return boolean
	 */
	
	public boolean adicionaInstituicao(Instituicao inst)
	{
		for(int i = 0; i < instituicoes.size(); i++)
		{
			if(instituicoes.get(i).equals(inst))
			{
				return false;
			}
		}
		instituicoes.add(inst);
		return true;
	}
	
	/**
	 * Método que retorna uma instituição pelo seu índice na coleção.
	 * @param i
	 * @return Instituicao
	 */
	
	public Instituicao getInst(int i)
	{
		return instituicoes.get(i);
	}
	
	/**
	 * Método que atribui uma coleção vazia à coleção atual.
	 */
	
	public void limparColecao()
	{
		instituicoes = new Vector<Instituicao>();
	}
	
	/**
	 * Método que lista as instituições contidas na coleção. Retorna o número de instituições contidas na coleção.
	 * @return int
	 */
	
	public int listageminstituicoes()
	{
		int i;
		System.out.println("INSTITUIÇÕES");
		for(i = 0; i < instituicoes.size(); i++)
		{
			System.out.println(instituicoes.get(i).toString());
		}
		return i;
	}
	
	/**
	 * Método para remover uma instituição da coleção de instituições, retorna true se a operação for executada com sucesso, ou false caso contrário.
	 * @param inst
	 * @return boolean
	 */
	
	public boolean removeInstituicao(Instituicao inst)
	{
		for(int i = 0; i < instituicoes.size(); i++)
		{
			if(instituicoes.get(i).equals(inst))
			{
				instituicoes.remove(inst);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Método que retorna o tamanho da coleção, ou seja, o número de dispositivos dentro da mesma.
	 * @return int
	 */
	
	public int size()
	{
		return instituicoes.size();
	}
	
	/**
	 * Método que retorna uma instituição após pesquisa pela mesma passada por parâmetro dentro da coleção. 
	 * @param inst
	 * @return Instituicao
	 */
	
	public Instituicao procuraInst(Instituicao inst)
	{
		for(int i = 0; i < instituicoes.size(); i++)
		{
			if(instituicoes.get(i).equals(inst))
			{
				return instituicoes.get(i);
			}
		}
		return null;
	}
	
	/**
	 * Método que recupera a coleção de instituições salvas num arquivo e atribui à coleção atual.
	 * @throws Exception
	 */
	
	@SuppressWarnings({ "unchecked" })
	public void recuperaArquivo() throws Exception
	{
		File file;
		FileInputStream fin;
		ObjectInputStream oin;
		try
		{
			file = new File("D:/Pen-Card Amway/IFPB/Projeto Automação das Salas/AutomacaoSalasIF/Exemplo Dados Salvos em Texto/conteudo.dat");
			if(file.exists())
			{
				fin = new FileInputStream(file);
				oin = new ObjectInputStream(fin);
				Vector<Instituicao> vector = (Vector<Instituicao>)oin.readObject();
				if(vector != null)
				{
					instituicoes = vector;
				}
				else
				{
					instituicoes = new Vector<Instituicao>();
				}
				oin.close();
				fin.close();
			}
		}
		catch(Exception e)
		{
			throw new Exception("Problema na leitura de arquivo!\n");
		}
	}
	
	/**
	 * Método que grava a coleção de instituições atual num arquivo.
	 * @throws Exception
	 */
	
	public void gravaArquivo() throws Exception
	{
		File file;
		FileOutputStream fout;
		ObjectOutputStream oout;
		try
		{
			file = new File("D:/Pen-Card Amway/IFPB/Projeto Automação das Salas/AutomacaoSalasIF/Exemplo Dados Salvos em Texto/conteudo.dat");
			fout = new FileOutputStream(file);
			oout = new ObjectOutputStream(fout);
			file.createNewFile();
			oout.writeObject(instituicoes);
			oout.close();
			fout.close();
		}
		catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
	}
}
