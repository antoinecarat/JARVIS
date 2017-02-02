package plugins;

import java.util.Date;

import client.IEvent;
import client.IModifier;

public class Modifier implements IModifier {

	public void modifyDate(IEvent e, Date d) {
		e.setDate(d);
	}

	public void modifyName(IEvent e, String name) {
		e.setName(name);
	}

}
