package plugins.simpleModifier;

import java.util.Date;

import client.IEvent;
import platform.plugins.IModifier;

public class SimpleModifier implements IModifier {


	public void modifyDateDebut(IEvent e, Date d) {
		e.setDateDebut(d);
	}

	public void modifyName(IEvent e, String name) {
		e.setName(name);
	}

}
