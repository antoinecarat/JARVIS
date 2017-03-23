package plugins.simplePrinter;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import client.IAgenda;
import client.IEvent;
import platform.plugins.IPrinter;

public class SimplePrinter implements IPrinter {

	public JPanel display(IAgenda a) {
		
		JPanel panel = new JPanel();
		JButton button;
		GridLayout grid = new GridLayout(a.getEvents().size(), 1);
		
		panel.setLayout(grid);
		
		for (IEvent event : a.getEvents()) {
			button = new JButton(event.toString());
			panel.add(button);
		}
		
		return panel;
	}

}
