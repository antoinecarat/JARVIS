package platform.plugins;

import javax.swing.JPanel;

import client.IAgenda;

public interface IPrinter {
	JPanel display(IAgenda a);
}
