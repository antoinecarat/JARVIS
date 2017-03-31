package plugins.deleteEventModifier;

import javax.swing.JDialog;

import client.IAgenda;
import client.IEvent;
import platform.plugins.IModifier;
import plugins.simpleBase.AgendaFrame;

public class DeleteEventModifier implements IModifier{

	public void modify(AgendaFrame frame, IAgenda a, IEvent e) {
		//TODO Show confirmation dialog
		JDialog dialog = new JDialog(frame, "Confirmer");
		dialog.show();
		//TODO Confirmation dialog accept =
		if(!a.getEvents().remove(e)){
			throw new IllegalArgumentException();
		}
	}
}
