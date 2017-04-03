package plugins.simpleModifier;

import client.IAgenda;
import client.IEvent;
import platform.plugins.IModifier;
import plugins.simpleBase.AgendaFrame;

public class SimpleModifier implements IModifier {

	/**
	 * Modify a event of the agenda.
	 * @param frame the application JPanel for refreshing 
	 * @param a the agenda to which the event is modify
	 * @param e the event modifies
	 */
	public void modify(AgendaFrame frame, IAgenda a, IEvent e) {
		ModifierEventFrame popup = new ModifierEventFrame(frame, e);
		popup.setVisible(true);
		frame.refreshPrinter();
	}

}
