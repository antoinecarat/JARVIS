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
		System.out.println("OK, let's go.");
		IAgenda agenda = new Agenda();
		
		ArrayList<String> participants = new ArrayList<String>();
		participants.add("Margaux");
		participants.add("Moi");
		IEvent event = new Event("Poueeet", new Date(2015, 03, 06), new Date(2015, 03, 07), "test@gmail.com", "02.40.56.56.65", Frequence.Année, "Anniversaire", "Anniversaire de Margaaaaaaaaux", "Chez moi", participants);
		
		agenda.addEvent(event);
		
		
		JFrame frame = new JFrame("Agenda JARVIS");
		frame.setSize(800,600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		frame.setLayout(new GridLayout(2, 2, 0, 1));
		
		JPanel createEvent = new JPanel();
		createEvent.setLayout(new GridLayout(9, 2));
		
		String label[] = {"name", "dateDebut", "dateFin", "mail", "tel", "frequence", "type", "description", "lieu"};

		JTextField tf;
		for(int i=0; i < label.length; ++i){
			tf = new JTextField();
			createEvent.add(new Label(label[i]));
			createEvent.add(tf);
		}
		frame.add(createEvent);
		
		List<IPluginDescriptor> listPluginDescriptor = Platform.getExtensions(IPrinter.class);
		IPrinter printer = (IPrinter) Platform.loadPlugin(listPluginDescriptor.get(0), IPrinter.class);
		JPanel printAgenda = printer.display(agenda);
		frame.add(printAgenda);
		
		JPanel create = new JPanel(new FlowLayout());
		JButton createButton = new JButton("Create new Event");
		createButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				IEvent event = new Event("Petard de bon diou", new Date(2017, 03, 06), new Date(2017, 03, 07), "test@gmail.com", "02.40.56.56.65", Frequence.Année, "Anniversaire", "Anniversaire de Margaaaaaaaaux", "Chez moi", participants);
				agenda.addEvent(event);
			}
		});
		
		create.add(createButton);
		frame.add(create);
		
		JPanel printers = new JPanel(new FlowLayout());
		JButton ip;
		for(int i = 0; i < listPluginDescriptor.size() ; ++i){
			ip = new JButton(listPluginDescriptor.get(i).getProperties().get("name"));
			printers.add(ip);
		}
		frame.add(printers);
		
		frame.setVisible(true);
		
	}
}