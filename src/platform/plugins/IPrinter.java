package platform.plugins;

import javax.swing.JPanel;

import client.IAgenda;
import plugins.simpleBase.AgendaFrame;

public interface IPrinter {
	JPanel display(IAgenda a, AgendaFrame frame);
}
