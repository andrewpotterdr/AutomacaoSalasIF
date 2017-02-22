package registradores;

public class InstituicaoEnsino extends Instituicao
{
	private String campus;
	
	public InstituicaoEnsino(String nome, String cidade, String campus)
	{
		super(nome, cidade);
		this.campus = campus;
	}
	
	public String getCampus()
	{
		return this.campus;
	}
	
	public String toString()
	{
		return super.toString() + "\nCampus: " + campus;
	}
	
	public boolean equals(InstituicaoEnsino instituicaoEnsino)
	{
		if(super.equals((Instituicao)instituicaoEnsino) && this.campus.equals(instituicaoEnsino.getCampus()))
		{
			return true;
		}
		return false;
	}
	
	public String toConteudo()
	{
		String conteudo = "INSTITUIÇÃO\r\n<<\r\nINSTITUIÇÃO DE ENSINO\r\n" + this.getNome() + "\r\n" + this.getCidade() + "\r\n" + this.getCampus() + "\r\n<\r\n";
		for(int i = 0; i < super.getColBlo().size(); i++)
		{
			conteudo += super.getColBlo().getBloco(i).toConteudo();
		}
		conteudo += ">\r\n";
		return conteudo;
	}
}
