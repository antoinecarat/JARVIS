package plugins.simpleCreator;

import platform.plugins.ICreator;
import plugins.simpleBase.AgendaFrame;

/**
 * Creates a new event by filling fields.
 */
public class SimpleCreator implements ICreator {

	@Override
	public void create(AgendaFrame a) {
		CreationFrame popup = new CreationFrame(a);
		popup.setVisible(true);
		a.refreshPrinter();
	}

}
