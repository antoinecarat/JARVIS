package plugins.simpleBase;

import java.awt.HeadlessException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import platform.IPlugin;
import platform.Platform;
import platform.plugins.IAutorun;
import client.Agenda;
import client.Event;
import client.IAgenda;
/**
 * Defines the agenda application which is calling other plugins.
 */
public class Base extends Thread implements IAutorun, IPlugin {

	AgendaFrame frame;
	
	@Override
	public void run() {
		
		//TODO: use agenda as a plugin
		IAgenda agenda = new Agenda();
		
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			agenda.addEvent(new Event("Event1", formatter.parse("06/01/2017"), formatter.parse("07/01/2017"), "Anniversaire", "Anniversaire de Keltoum", "Chez moi"));
			agenda.addEvent(new Event("Event2", formatter.parse("06/02/2017"), formatter.parse("07/02/2017"), "Anniversaire", "Anniversaire de Yasmine", "Chez moi"));
			agenda.addEvent(new Event("Event3", formatter.parse("06/03/2017"), formatter.parse("07/03/2017"), "Anniversaire", "Anniversaire de Margaux", "Chez moi"));
			agenda.addEvent(new Event("Event4", formatter.parse("06/04/2017"), formatter.parse("07/04/2017"), "Anniversaire", "Anniversaire de Marwan", "Chez moi"));
			agenda.addEvent(new Event("Event5", formatter.parse("06/05/2017"), formatter.parse("07/05/2017"), "Anniversaire", "Anniversaire de Pper", "Chez moi"));
			agenda.addEvent(new Event("Event6", formatter.parse("06/06/2017"), formatter.parse("07/06/2017"), "Anniversaire", "Anniversaire de Khemi", "Chez moi"));
			agenda.addEvent(new Event("Event7", formatter.parse("06/07/2017"), formatter.parse("07/07/2017"), "Anniversaire", "Anniversaire de Samy", "Chez moi"));
			agenda.addEvent(new Event("Event8", formatter.parse("06/08/2017"), formatter.parse("07/08/2017"), "Anniversaire", "Anniversaire de Quentin", "Chez moi"));
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
			
		try {
			frame = new AgendaFrame(agenda);
			frame.setVisible(true);
		} catch (HeadlessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Platform.subscribeEvent("event.added", this);
		Platform.subscribeEvent("event.modified", this);
		Platform.subscribeEvent("event.removed", this);
		Platform.subscribeEvent("printer.changed", this);
	}

	@Override
	public void handleEvent(String event) {
		//String cat = event.split(".")[0];
		//if (cat.equals("event")){
			frame.refreshPrinter();
		//}
	}
}