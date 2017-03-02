package plugins.simplePrinter;

import client.IAgenda;
import client.IEvent;
import platform.plugins.IPlugin;
import platform.plugins.IPrinter;

public class Printer implements IPlugin, IPrinter {

	public void display(IAgenda a) {
		System.out.println("Event list :");
		for (IEvent event : a.getEvents()) {
			System.out.println(event);
		}
	}

}
