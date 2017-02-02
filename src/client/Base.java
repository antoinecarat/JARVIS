package client;

import java.util.Date;

import platform.IPlugin;

public class Base implements IPlugin{

	//@SuppressWarnings("deprecation")
	public void run() {
		System.out.println("OK, let's go.");
		IAgenda agenda = new Agenda();
		//agenda.addEvent(new Event("Pouet", new Date(2015, 05, 20)));
		//agenda.addEvent(new Event("Party", new Date(2015, 07, 11)));
		
	}

}
