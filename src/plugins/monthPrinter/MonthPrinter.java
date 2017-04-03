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

	public JPanel display(IAgenda a, AgendaFrame frame) {
		
		JPanel panel = new JPanel();
		JLabel label;
		GridLayout grid = new GridLayout(a.getEvents().size(), 1);
		
		Date today = new Date();
		panel.setLayout(grid);
		
		for (IEvent event : a.getEvents()) {
			if((event.getDateStart().getMonth() == today.getMonth()) &&
				event.getDateStart().getYear() == today.getYear()){
				
				label = new JLabel(event.toString());
				panel.add(label);
			}
		}
		
		return panel;
	}

}
