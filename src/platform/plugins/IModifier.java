package platform.plugins;

import java.util.Date;

import client.IEvent;

public interface IModifier {

	void modifyDateDebut(IEvent e, Date d);

	void modifyName(IEvent e, String name);

}