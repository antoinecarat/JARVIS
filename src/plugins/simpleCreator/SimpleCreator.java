package plugins.simpleCreator;

import platform.plugins.ICreator;
import platform.plugins.IPlugin;
import plugins.simpleBase.AgendaFrame;

public class SimpleCreator implements ICreator, IPlugin {

	@Override
	public void create(AgendaFrame a) {
		CreationFrame popup = new CreationFrame(a);
		popup.setVisible(true);
		a.refreshPrinter();
	}

	@Override
	public void handleEvent(String event) {
		// TODO Auto-generated method stub
		
	}

}
