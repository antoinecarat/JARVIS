package plugins.simpleBase;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import client.Event;
import client.Frequence;
import client.IEvent;

public class ChangePrinterListener implements ActionListener {

	AgendaFrame frame;
	int index;
	
	public ChangePrinterListener(AgendaFrame frame, int index) {
		super();
		this.frame = frame;
		this.index = index;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		frame.changePrinter(this.index);
		frame.refreshPrinter();
	}

}
