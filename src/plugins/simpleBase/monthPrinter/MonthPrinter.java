package plugins.simpleBase.monthPrinter;

import java.awt.GridLayout;
import java.util.Date;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import platform.IPlugin;
import plugins.simpleBase.AgendaFrame;
import plugins.simpleBase.IAgenda;
import plugins.simpleBase.IEvent;
import plugins.simpleBase.IPrinter;

public class MonthPrinter implements IPrinter, IPlugin {
	
	/**
	 * Returns events display for the month.
	 * @param a the agenda to which the events collection 
	 * @param frame the application JPanel for refreshing
	 * @return the new JPanel with the events collection
	 */

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
	public void handleEvent(String event, Object args) {
		// TODO Auto-generated method stub
		
	}

}
