package plugins.modifierPrinter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import client.IAgenda;
import client.IEvent;

public class CallModifyListener implements ActionListener{

	IAgenda agenda;
	IEvent event;
	
	
	public CallModifyListener(IAgenda agenda, IEvent event) {
		super();
		this.agenda = agenda;
		this.event = event;
	}


	public void actionPerformed(ActionEvent arg0) {
		
	}

}
