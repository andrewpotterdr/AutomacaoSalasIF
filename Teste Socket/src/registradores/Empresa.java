package registradores;

public class Empresa extends Instituicao
{
	private String CNPJ;
	
	public Empresa(String nome, String cidade, String CNPJ)
	{
		super(nome, cidade);
		this.CNPJ = CNPJ;
	}
	
	public String getCNPJ()
	{
		return this.CNPJ;
	}
	
	public String toString()
	{
		return super.toString() + "\nCNPJ: " + CNPJ;
	}
	
	public boolean equals(Empresa empresa)
	{
		if(super.equals((Instituicao)empresa) && this.CNPJ.equals(empresa.getCNPJ()))
		{
			return true;
		}
		return false;
	}
	
	public String toConteudo()
	{
		String conteudo = "INSTITUIÇÃO\r\n<<\r\nEMPRESA\r\n" + this.getNome() + "\r\n" + this.getCidade() + "\r\n" + this.getCNPJ() + "\r\n<\r\n";
		for(int i = 0; i < super.getColBlo().size(); i++)
		{
			conteudo += super.getColBlo().getBloco(i).toConteudo();
		}
		conteudo += ">\r\n";
		return conteudo;
	}
}
