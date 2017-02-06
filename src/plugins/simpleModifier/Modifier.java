package plugins.simpleModifier;

import java.util.Date;

import client.IEvent;
import platform.plugins.IModifier;
import platform.plugins.IPlugin;

public class Modifier implements IPlugin, IModifier {

	public void modifyDate(IEvent e, Date d) {
		e.setDate(d);
	}

	public void modifyName(IEvent e, String name) {
		e.setName(name);
	}

}
