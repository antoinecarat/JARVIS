package plugins.simpleModifier;

import client.IAgenda;
import client.IEvent;
import platform.plugins.IModifier;
import plugins.simpleBase.AgendaFrame;

public class SimpleModifier implements IModifier {

	public void modify(AgendaFrame frame, IAgenda a, IEvent e) {
		ModifierEventFrame popup = new ModifierEventFrame(frame, e);
		popup.setVisible(true);
		frame.refreshPrinter();
	}

}
