package plugins.simplePrinter;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import client.IAgenda;
import client.IEvent;
import platform.plugins.IPrinter;
import plugins.simpleBase.AgendaFrame;

public class SimplePrinter implements IPrinter {

	/**
	 * Returns a display with all events.
	 * @param a the agenda to which the events collection 
	 * @param frame the application JPanel for refreshing
	 * @return the new JPanel with the events collection
	 */
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

}
