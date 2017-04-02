package plugins.simpleBase;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JScrollPane;

import client.Agenda;
import client.Event;
import client.IAgenda;
import client.IEvent;
import platform.plugins.IAutorun;

public class Base implements IAutorun{

	public void run() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		IAgenda agenda = new Agenda();
		
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		

		IEvent event;
		try {
			agenda.addEvent(new Event("Event1", formatter.parse("06/03/2017"), formatter.parse("07/03/2017"), "Anniversaire", "Anniversaire de Keltoum", "Chez moi"));
			agenda.addEvent(new Event("Event2", formatter.parse("06/04/2017"), formatter.parse("07/04/2017"), "Anniversaire", "Anniversaire de Margaux", "Chez moi"));
			agenda.addEvent(new Event("Event3", formatter.parse("06/07/2017"), formatter.parse("07/07/2017"), "Anniversaire", "Anniversaire de Marwan", "Chez moi"));
			agenda.addEvent(new Event("Event4", formatter.parse("06/07/2017"), formatter.parse("07/07/2017"), "Anniversaire", "Anniversaire de Marwan", "Chez moi"));
			agenda.addEvent(new Event("Event5", formatter.parse("06/07/2017"), formatter.parse("07/07/2017"), "Anniversaire", "Anniversaire de Marwan", "Chez moi"));
			agenda.addEvent(new Event("Event6", formatter.parse("06/07/2017"), formatter.parse("07/07/2017"), "Anniversaire", "Anniversaire de Marwan", "Chez moi"));
			agenda.addEvent(new Event("Event7", formatter.parse("06/07/2017"), formatter.parse("07/07/2017"), "Anniversaire", "Anniversaire de Marwan", "Chez moi"));
			agenda.addEvent(new Event("Event8", formatter.parse("06/07/2017"), formatter.parse("07/07/2017"), "Anniversaire", "Anniversaire de Marwan", "Chez moi"));
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		AgendaFrame frame = new AgendaFrame(agenda);
		
		frame.setVisible(true);
		
	}
}