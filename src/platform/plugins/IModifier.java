package platform.plugins;

import client.IAgenda;
import client.IEvent;
import plugins.simpleBase.AgendaFrame;

/**
 * Plugin that is of type "Modifier". 
 * Plugin to modify a event.
 */
public interface IModifier {

	/**
	 * Changes (modify, delete...) a event of the agenda 
	 * @param frame the application JPanel for refreshing 
	 * @param a the agenda to which the event is modify
	 * @param e the event modifies
	 */
	void modify(AgendaFrame frame, IAgenda a, IEvent e);
	
}