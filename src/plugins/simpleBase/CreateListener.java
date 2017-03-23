package plugins.simpleBase;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;

import client.Event;
import client.Frequence;
import client.IAgenda;
import client.IEvent;
import platform.plugins.IPrinter;

public class CreateListener implements ActionListener {

	AgendaFrame frame;
	
	//Mafenetre f;
	
	public CreateListener(AgendaFrame frame) {
		super();
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ArrayList<String> participants = new ArrayList<String>();
		
		IEvent event = new Event("Petard de bon diou", new Date(2017, 03, 06), new Date(2017, 03, 07), "test@gmail.com", "02.40.56.56.65", Frequence.Année, "Anniversaire", "Anniversaire de Margaaaaaaaaux", "Chez moi", participants );
		frame.getAgenda().addEvent(event);
		frame.refreshPrinter();
	}

}
