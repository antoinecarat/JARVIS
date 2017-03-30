package platform.plugins;

import javax.swing.JFrame;
import javax.swing.JPanel;

import client.IAgenda;

public interface IPrinter {
	JPanel display(IAgenda a, JFrame frame);
}
