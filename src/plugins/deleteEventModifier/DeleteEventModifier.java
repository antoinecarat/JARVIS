package plugins.deleteEventModifier;

import javax.swing.JOptionPane;

import client.IAgenda;
import client.IEvent;
import platform.plugins.IModifier;
import plugins.simpleBase.AgendaFrame;
/**
 * Plugin to delete a event. 
 */

public class DeleteEventModifier implements IModifier{
	
	/**
	 * Delete a event of the agenda.
	 * @param frame the application JPanel for refreshing 
	 * @param a the agenda to which the event is delete
	 * @param e the event deletes
	 */
	public void modify(AgendaFrame frame, IAgenda a, IEvent e) {
		if (JOptionPane.showConfirmDialog(frame, "Do you really want to remove it?", "Confirmation window", JOptionPane.YES_NO_OPTION) 
				== JOptionPane.YES_OPTION){
			if(!a.getEvents().remove(e)){
				throw new IllegalArgumentException();
			}
			frame.refreshPrinter();
		}
		
	}
}
