package plugins.simpleBase.deleteEventModifier;

import javax.swing.JOptionPane;

import platform.Platform;
import plugins.simpleBase.IAgenda;
import plugins.simpleBase.IEvent;
import plugins.simpleBase.IModifier;

/**
 * Defines a modifier plugin that delete a given event from a given agenda. 
 */
public class DeleteEventModifier implements IModifier {

	@Override
	public void modify(IAgenda a, IEvent e) {
		if (JOptionPane.showConfirmDialog(null, "Do you really want to remove it?", "Confirmation window", JOptionPane.YES_NO_OPTION) 
				== JOptionPane.YES_OPTION){
			a.getEvents().remove(e);
			Platform.raiseEvent("event.removed", null);
		}
	}

	@Override
	public void handleEvent(String event, Object args) {}

	@Override
	public void startUp() {
		
	}
}
