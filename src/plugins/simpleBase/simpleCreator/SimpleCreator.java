package plugins.simpleBase.simpleCreator;

import plugins.simpleBase.IAgenda;
import plugins.simpleBase.ICreator;

/**
 * Defines a creator plugin that creates an event in a given Agenda.
 */
public class SimpleCreator implements ICreator {

	@Override
	public void create(IAgenda a) {
		CreationFrame popup = new CreationFrame(a);
		popup.setVisible(true);
	}

	@Override
	public void handleEvent(String event, Object args) {}

	@Override
	public void startUp() {}

}
