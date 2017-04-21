package plugins.simpleBase.monthPrinter;

import java.awt.GridLayout;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;

import plugins.simpleBase.IAgenda;
import plugins.simpleBase.IEvent;
import plugins.simpleBase.IPrinter;

/**
 * Defines a printer plugin that prints only the event starting in the current month.
 */
public class MonthPrinter implements IPrinter {
	
	public JPanel display(IAgenda a) {
		
		JPanel panel = new JPanel();
		JLabel label;
		GridLayout grid = new GridLayout(a.getEvents().size(), 1);
		
		Date today = new Date();
		panel.setLayout(grid);
		
		for (IEvent event : a.getEvents()) {
			
			if((event.getStartDate().getMonth() == today.getMonth()) &&
				event.getStartDate().getYear() == today.getYear()){
				
				label = new JLabel(event.toString());
				panel.add(label);
			}
		}
		
		return panel;
	}

	@Override
	public void handleEvent(String event, Object args) {}

	@Override
	public void startUp() {}

}
