package plugins.simpleBase;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import client.Agenda;
import client.Event;
import client.Frequence;
import client.IAgenda;
import client.IEvent;
import platform.IPluginDescriptor;
import platform.Platform;
import platform.plugins.IAutorun;
import platform.plugins.IPrinter;

public class Base implements IAutorun{

	public void run() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		IAgenda agenda = new Agenda();
		
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		

		IEvent event;
		try {
			agenda.addEvent(new Event("Event1", formatter.parse("06/03/2017"), formatter.parse("07/03/2017"), "Anniversaire", "Anniversaire de Keltoum", "Chez moi"));
			agenda.addEvent(new Event("Event2", formatter.parse("06/04/2017"), formatter.parse("07/04/2017"), "Anniversaire", "Anniversaire de Margaux", "Chez moi"));
			agenda.addEvent(new Event("Event3", formatter.parse("06/07/2017"), formatter.parse("07/07/2017"), "Anniversaire", "Anniversaire de Marwan", "Chez moi"));
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		AgendaFrame frame = new AgendaFrame(agenda);
		
		frame.setVisible(true);
		
	}
}