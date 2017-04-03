package plugins.monthPrinter;

import java.awt.GridLayout;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;

import client.IAgenda;
import client.IEvent;
import platform.plugins.IPrinter;
import plugins.simpleBase.AgendaFrame;

public class MonthPrinter implements IPrinter{

	@SuppressWarnings("deprecation")
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

}
