package plugins.hardPrinter;

import client.IAgenda;
import client.IEvent;
import platform.plugins.IPrinter;

public class HardPrinter implements IPrinter {

	public void display(IAgenda a) {
		System.out.println("EVENT LIST :");
		for (IEvent event : a.getEvents()) {
			System.out.println(event);
		}
	}

}
