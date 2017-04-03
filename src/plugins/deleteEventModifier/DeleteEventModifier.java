package plugins.deleteEventModifier;

import javax.swing.JOptionPane;

import client.IAgenda;
import client.IEvent;
import platform.plugins.IModifier;
import plugins.simpleBase.AgendaFrame;

public class DeleteEventModifier implements IModifier{

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
