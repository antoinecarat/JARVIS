package plugins;

import platform.IPlugin;
import client.IPrinter;

public class Printer implements IPlugin, IPrinter {

	public void run() {
		System.out.println("Pouet.");
	}

}
