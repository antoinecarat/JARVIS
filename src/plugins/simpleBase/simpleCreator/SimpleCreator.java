package plugins.simpleBase.simpleCreator;

import client.IAgenda;
import platform.IPlugin;
import platform.Platform;
import plugins.simpleBase.ICreator;

public class SimpleCreator implements ICreator, IPlugin {

	@Override
	public void create(IAgenda a) {
		CreationFrame popup = new CreationFrame(a);
		popup.setVisible(true);
	}

	@Override
	public void handleEvent(String event) {
		// TODO Auto-generated method stub
		
	}

}
