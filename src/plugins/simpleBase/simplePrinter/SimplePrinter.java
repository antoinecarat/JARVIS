package plugins.simpleBase.simplePrinter;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import plugins.simpleBase.IAgenda;
import plugins.simpleBase.IEvent;
import plugins.simpleBase.IPrinter;

/**
 * Defines a printer plugin that prints all the events of a given Agenda.
 */
public class SimplePrinter implements IPrinter {

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
	public void handleEvent(String event, Object args) {}

	@Override
	public void startUp() {}

}
