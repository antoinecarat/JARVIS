package plugins.monthPrinter;

import java.awt.GridLayout;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import client.IAgenda;
import client.IEvent;
import platform.plugins.IPrinter;

public class MonthPrinter implements IPrinter {

	public JPanel display(IAgenda a, JFrame frame) {
		
		JPanel panel = new JPanel();
		JLabel label;
		GridLayout grid = new GridLayout(a.getEvents().size(), 1);
		
		Date today = new Date();
		panel.setLayout(grid);
		
		for (IEvent event : a.getEvents()) {
			if((event.getDateDebut().getMonth() == today.getMonth()) &&
				event.getDateDebut().getYear() == today.getYear()){
				
				label = new JLabel(event.toString());
				panel.add(label);
			}
		}
		
		return panel;
	}

}
