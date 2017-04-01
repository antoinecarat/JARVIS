package plugins.simpleBase;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
		
		IEvent event = new Event("Poueeet", new Date(2015, 03, 06), new Date(2015, 03, 07), "Anniversaire", "Anniversaire de Margaaaaaaaaux", "Chez moi");
		
		agenda.addEvent(event);
		
		AgendaFrame frame = new AgendaFrame(agenda);
		
		frame.setVisible(true);
		
	}
}