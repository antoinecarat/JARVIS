package platform.plugins;

import client.IAgenda;
import client.IEvent;
import plugins.simpleBase.AgendaFrame;

public interface IModifier {

	void modify(AgendaFrame frame, IAgenda a, IEvent e);
	
}