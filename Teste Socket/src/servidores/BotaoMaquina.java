package servidores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BotaoMaquina implements ActionListener
{
	private boolean desligar = true;
	
	public void actionPerformed(ActionEvent e)
	{
		desligar = false;
	}
	
	public boolean returnEstado()
	{
		return this.desligar;
	}
}
