package plugins.deleteEventModifier;

import javax.swing.JOptionPane;

import platform.plugins.IModifier;
import platform.plugins.IPlugin;
import plugins.simpleBase.AgendaFrame;
import client.IAgenda;
import client.IEvent;

public class DeleteEventModifier implements IModifier, IPlugin {

	public void modify(AgendaFrame frame, IAgenda a, IEvent e) {
		
		if (JOptionPane.showConfirmDialog(frame, "Do you really want to remove it?", "Confirmation window", JOptionPane.YES_NO_OPTION) 
				== JOptionPane.YES_OPTION){
			if(!a.getEvents().remove(e)){
				throw new IllegalArgumentException();
			}
			frame.refreshPrinter();
		}
		
	}

	@Override
	public void handleEvent(String event) {
		// TODO Auto-generated method stub
		
	}
}
