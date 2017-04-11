package plugins.deleteEventModifier;

import javax.swing.JOptionPane;

import platform.IPlugin;
import platform.Platform;
import platform.plugins.IModifier;
import plugins.simpleBase.AgendaFrame;
import client.IAgenda;
import client.IEvent;
/**
 * Plugin to delete a event. 
 */

public class DeleteEventModifier implements IModifier, IPlugin {

	
	/**
	 * Delete a event of the agenda.
	 * @param frame the application JPanel for refreshing 
	 * @param a the agenda to which the event is delete
	 * @param e the event deletes
	 */
	public void modify(IAgenda a, IEvent e) {
		if (JOptionPane.showConfirmDialog(null, "Do you really want to remove it?", "Confirmation window", JOptionPane.YES_NO_OPTION) 
				== JOptionPane.YES_OPTION){
			if(!a.getEvents().remove(e)){
				throw new IllegalArgumentException();
			}
			Platform.raiseEvent("event.removed");
		}
		
	}

	@Override
	public void handleEvent(String event) {
		// TODO Auto-generated method stub
		
	}
}
