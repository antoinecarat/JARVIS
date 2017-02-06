package plugins.simpleBase;

import java.util.Date;

import client.Agenda;
import client.Event;
import client.IAgenda;
import client.IEvent;
import platform.Platform;
import platform.plugins.IAutorun;
import platform.plugins.IPlugin;
import platform.plugins.IPrinter;
import plugins.Simpleprinter.Printer;

public class Base implements IPlugin, IAutorun{

	public void run() {
		System.out.println("OK, let's go.");
		IAgenda agenda = new Agenda();
		IEvent event = new Event("Pouet", new Date(2015, 05, 20));
		agenda.addEvent(event);
		
		//IPrinter printer = (IPrinter) Platform.getExtensions(IPrinter.class).get(0).newIntance();
		IPrinter printer = new Printer();
		printer.display(agenda);
	}
}