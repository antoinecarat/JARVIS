package plugins.simplePrinter;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import platform.plugins.IPlugin;
import platform.plugins.IPrinter;
import plugins.simpleBase.AgendaFrame;
import client.IAgenda;
import client.IEvent;

public class SimplePrinter implements IPrinter, IPlugin {

	public JPanel display(IAgenda a, AgendaFrame frame) {
		
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

	@Override
	public void handleEvent(String event) {
		// TODO Auto-generated method stub
		
	}

}
