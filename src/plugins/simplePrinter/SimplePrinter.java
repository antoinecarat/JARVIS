package plugins.simplePrinter;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import client.IAgenda;
import client.IEvent;
import platform.plugins.IPrinter;

public class SimplePrinter implements IPrinter {

	public JPanel display(IAgenda a, JFrame frame) {
		
		JPanel panel = new JPanel();
		JLabel label;
		GridLayout grid = new GridLayout(a.getEvents().size(), 1);
		
		panel.setLayout(grid);
		
		for (IEvent event : a.getEvents()) {
			label = new JLabel(event.toString());
			panel.add(label);
		}
		
		return panel;
	}

}
