package registradores;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Vector;

/**
 * @author Pablo Bezerra Guedes Lins de Albuquerque e Michael Almeida da Franca Monteiro.
 * Classe referente à coleção de objetos do tipo Instituicao.
 *
 */
public class ColecaoInstituicoes implements Serializable 
{
	
	private static final long serialVersionUID = 7663710807871190013L;
	private Vector<Instituicao> instituicoes;
	
	/**
	 * Construtor de ColecaoInstituicoes.
	 */
	public ColecaoInstituicoes()
	{
		
		instituicoes = new Vector<Instituicao>();
	}
	
	/**
	 * adiciona um objeto do tipo Instituicao, retorna true se a operação der certo e false se houver falha.
	 * @param inst
	 * @return retorna verdadeiro caso o objeto seja adicionado com sucesso.
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
	
	public Instituicao getInst(int i)
	{
		return instituicoes.get(i);
	}
	
	/**
	 * Seta a coleção como null limpando-a assim.
	 */
	public void limparColecao()
	{
		
		instituicoes = null;
	}
	
	/**
	 * Lista todas as Instituioes, retorna a quantidade de instituições.
	 * @return int.
	 */
	public int listageminstituicoes()
	{
		
		int i;
		System.out.println("INSTITUIÃ‡Ã•ES");
		for(i = 0; i < instituicoes.size(); i++)
		{
			System.out.println(instituicoes.get(i).toString());
		}
		return i;
	}
	
	/**
	 * @param inst
	 * remove a Instituicao especificado, retorna true se a operação der certo e false se houver falha.
	 * @return boolean.
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
	 * Retorna o tamanho da Coleção.
	 * @return int.
	 */
	public int size()
	{
		
		return instituicoes.size();
	}
	
	/**
	 * Pesquisa a Instituição especificada, retorna a mesma caso exista e null caso não exista.
	 * @param inst
	 * @return Instituicao.
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
	 * @throws Exception
	 * Método que acessa o arquivo e atribui à coleção instituicoes a coleção presente no arquivo caso haja, caso não ele cria um Vector vazio
	 * e atribui a coleção.
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
			throw new Exception("Deu merda");
		}
	}
	
	/**
	 * @throws Exception
	 * Método que escreve os objetos no arquivo.
	 */
	public void gravaArquivo() throws Exception
	{
		
		File file;
		FileOutputStream fout;
		ObjectOutputStream oout;
		try
		{
			file = new File("D:\\Pen-Card Amway\\IFPB\\Projeto Automação das Salas\\AutomacaoSalasIF\\Exemplo Dados Salvos em Texto\\conteudo.dat");
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
