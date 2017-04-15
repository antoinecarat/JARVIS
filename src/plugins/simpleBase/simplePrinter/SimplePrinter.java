package plugins.simpleBase.simplePrinter;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import platform.IPlugin;
import plugins.simpleBase.AgendaFrame;
import plugins.simpleBase.IPrinter;
import client.IAgenda;
import client.IEvent;

public class SimplePrinter implements IPrinter, IPlugin {

	public JPanel display(IAgenda a) {
		
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
