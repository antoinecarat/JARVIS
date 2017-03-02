package plugins.simpleBase;

import java.util.ArrayList;
import java.util.Date;

import client.Agenda;
import client.Event;
import client.Frequence;
import client.IAgenda;
import client.IEvent;
import platform.Platform;
import platform.plugins.IAutorun;
import platform.plugins.IPlugin;
import platform.plugins.IPrinter;
import plugins.simplePrinter.SimplePrinter;

public class Base implements IPlugin, IAutorun{

	public void run() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		System.out.println("OK, let's go.");
		IAgenda agenda = new Agenda();
		ArrayList participants = new ArrayList();
		participants.add("Margaux");
		participants.add("Moi");
		IEvent event = new Event("Poueeet", new Date(2015, 03, 06), new Date(2015, 03, 07), "test@gmail.com", "02.40.56.56.65", Frequence.Année, "Anniversaire", "Anniversaire de Margaaaaaaaaux", "Chez moi", participants);

		agenda.addEvent(event);
		
		IPrinter printer = (IPrinter) Platform.getExtensions(IPrinter.class).get(1).newInstance();

		printer.display(agenda);
	}
}