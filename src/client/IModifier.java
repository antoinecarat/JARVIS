package client;

import java.util.Date;

public interface IModifier {
	void modifyDate(IEvent e, Date d);
	void modifyName(IEvent e, String name);
}
