package platform.plugins;

import java.util.Date;

import client.IEvent;

public interface IModifier {
	void modifyDate(IEvent e, Date d);
	void modifyName(IEvent e, String name);
}
