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

	@Override
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

	@Override
	public void handleEvent(String event) {
		// TODO Auto-generated method stub
		
	}

}
