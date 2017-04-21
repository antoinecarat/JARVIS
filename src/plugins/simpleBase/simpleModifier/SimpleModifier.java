package plugins.simpleBase.simpleModifier;

import plugins.simpleBase.IAgenda;
import plugins.simpleBase.IEvent;
import plugins.simpleBase.IModifier;

/**
 * Defines a modifier plugin for editing an event.
 */
public class SimpleModifier implements IModifier {

	public void modify(IAgenda a, IEvent e) {
		ModifierEventFrame popup = new ModifierEventFrame(e);
		popup.setVisible(true);
	}

	@Override
	public void handleEvent(String event, Object args) {}

	@Override
	public void startUp() {}

}
