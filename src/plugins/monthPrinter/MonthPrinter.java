package plugins.monthPrinter;

import java.awt.GridLayout;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;

import platform.plugins.IPlugin;
import platform.plugins.IPrinter;
import plugins.simpleBase.AgendaFrame;
import client.IAgenda;
import client.IEvent;

public class MonthPrinter implements IPrinter, IPlugin {
	
	/**
	 * Returns events display for the month.
	 * @param a the agenda to which the events collection 
	 * @param frame the application JPanel for refreshing
	 * @return the new JPanel with the events collection
	 */

	public JPanel display(IAgenda a, AgendaFrame frame) {
		
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
	public void handleEvent(String event) {
		// TODO Auto-generated method stub
		
	}

}
