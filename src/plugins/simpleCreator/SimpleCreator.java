package plugins.simpleCreator;

import platform.plugins.ICreator;
import plugins.simpleBase.AgendaFrame;

public class SimpleCreator implements ICreator {

	@Override
	public void create(AgendaFrame a) {
		CreationFrame popup = new CreationFrame(a);
		popup.setVisible(true);
		a.refreshPrinter();
	}

}
