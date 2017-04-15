package plugins.simpleBase.simpleModifier;

import platform.IPlugin;
import plugins.simpleBase.AgendaFrame;
import plugins.simpleBase.IModifier;
import client.IAgenda;
import client.IEvent;

public class SimpleModifier implements IModifier, IPlugin {

	public void modify(IAgenda a, IEvent e) {
		ModifierEventFrame popup = new ModifierEventFrame(e);
		popup.setVisible(true);
	}

	@Override
	public void handleEvent(String event) {
		// TODO Auto-generated method stub
		
	}

}
