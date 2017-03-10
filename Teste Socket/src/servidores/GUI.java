package servidores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import registradores.ColecaoDispositivos;
import registradores.Maquina;

public class GUI
{
	private JFrame janela;
	private JPanel painelPrincipal;
	
	public GUI()
	{
		
	}
	
	private void montaTela(ColecaoDispositivos maquinas)
	{
		preparaJanela();
		preparaPainelPrincipal();
		for(int i = 0; i < maquinas.size(); i++)
		{
			preparaBotaoMaquina((Maquina) maquinas.getDispositivo(i));
		}
		preparaBotaoSalvar();
		preparaBotaoSair();
		mostraJanela();
	}
	
	private void preparaJanela()
	{
		janela = new JFrame("Argentum");
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void preparaPainelPrincipal()
	{
		painelPrincipal = new JPanel();
		janela.add(painelPrincipal);
	}
	
	private boolean preparaBotaoMaquina(Maquina maq)
	{
		JButton botaoMaquina = new JButton(maq.getNome() + "\n" + maq.getMAC() + "\n" + maq.getIP());
		boolean desligar = true;
		BotaoMaquina maqbut = new BotaoMaquina();
		botaoMaquina.addActionListener(maqbut);
		desligar = maqbut.returnEstado();
		return desligar;
	}
	
	private void preparaBotaoSalvar()
	{
		JButton botaoSalvar = new JButton("Salvar Estado");
		botaoSalvar.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});
		painelPrincipal.add(botaoSalvar);
	}
	
	private void preparaBotaoSair()
	{
		JButton botaoSair = new JButton("Sair");
		botaoSair.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});
		painelPrincipal.add(botaoSair);
	}
	
	private void mostraJanela()
	{
		janela.pack();
		janela.setSize(540, 540);
		janela.setVisible(true);
	}
}
