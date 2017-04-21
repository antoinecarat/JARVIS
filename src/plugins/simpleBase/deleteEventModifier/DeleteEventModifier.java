package plugins.simpleBase.deleteEventModifier;

import javax.swing.JOptionPane;

import platform.Platform;
import plugins.simpleBase.IAgenda;
import plugins.simpleBase.IEvent;
import plugins.simpleBase.IModifier;

/**
 * Plugin to delete a event. 
 */

public class DeleteEventModifier implements IModifier {

	
	/**
	 * Delete a event of the agenda.
	 * @param frame the application JPanel for refreshing 
	 * @param a the agenda to which the event is delete
	 * @param e the event deletes
	 */
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
