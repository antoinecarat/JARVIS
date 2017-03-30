package plugins.simpleBase;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import client.Event;
import client.Frequence;
import client.IEvent;

public class CreateListener implements ActionListener {

	AgendaFrame frame;
	
	public CreateListener(AgendaFrame frame) {
		super();
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		List<String> tf = frame.getFieldsContent();
		//TODO: call invoke with reflect; Frequency & participants /!\.
		
		ArrayList<String> participants = new ArrayList<String>();
		IEvent event = new Event(tf.get(0), new Date(tf.get(1)), new Date(tf.get(2)), tf.get(3), tf.get(4), Frequence.Année, tf.get(6), tf.get(7), tf.get(8), participants);
		
		frame.getAgenda().addEvent(event);
		frame.refreshPrinter();
	}

}
