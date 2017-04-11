package plugins.simpleModifier;

import platform.plugins.IModifier;
import platform.plugins.IPlugin;
import plugins.simpleBase.AgendaFrame;
import client.IAgenda;
import client.IEvent;

public class SimpleModifier implements IModifier, IPlugin {

	@Override
	public void modify(AgendaFrame frame, IAgenda a, IEvent e) {
		ModifierEventFrame popup = new ModifierEventFrame(frame, e);
		popup.setVisible(true);
		frame.refreshPrinter();
	}

	@Override
	public void handleEvent(String event) {
		// TODO Auto-generated method stub
		
	}

}
