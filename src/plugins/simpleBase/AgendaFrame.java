package plugins.simpleBase;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Label;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import client.IAgenda;
import platform.IPluginDescriptor;
import platform.Platform;
import platform.plugins.IPrinter;

public class AgendaFrame extends JFrame {

	IAgenda agenda;
	JPanel createEvent;
	JPanel printAgenda;
	IPrinter printer;
	GridBagConstraints gbc;
	GridBagLayout gb;
	int nbPrinters;
	

	public AgendaFrame(IAgenda agenda) throws HeadlessException, ClassNotFoundException {
		super();
		this.agenda = agenda;
		
		this.setTitle("Agenda JARVIS");
		this.setSize(800,600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		gb = new GridBagLayout();
		this.setLayout(gb);
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.NONE;
		
		this.createEvent = new JPanel();
		createEvent.setLayout(new GridLayout(9, 2));
		
		String label[] = {"name", "dateDebut", "dateFin", "mail", "tel", "frequence", "type", "description", "lieu"};

		JTextField tf;
		for(int i=0; i < label.length; ++i){
			tf = new JTextField();
			createEvent.add(new Label(label[i]));
			createEvent.add(tf);
		}
		
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridheight = 9;
		gbc.gridwidth = 2;
		gb.setConstraints(createEvent, gbc);
		this.add(createEvent);
		
		List<IPluginDescriptor> listPluginDescriptor = Platform.getExtensions(IPrinter.class);
		nbPrinters = listPluginDescriptor.size();
		this.printer = (IPrinter) Platform.loadPlugin(listPluginDescriptor.get(0), IPrinter.class);
		this.printAgenda = printer.display(agenda);
		
		gbc.gridx = 3;
		gbc.gridy = 0;
		gbc.gridheight = 9;
		gbc.gridwidth = nbPrinters;
		gb.setConstraints(printAgenda, gbc);
		this.add(printAgenda);
		
		JPanel create = new JPanel(new FlowLayout());
		JButton createButton = new JButton("Create new Event");
		createButton.addActionListener(new CreateListener(this));
		create.add(createButton);
		
		gbc.gridx = 10;
		gbc.gridy = 1;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gb.setConstraints(create, gbc);
		this.add(create);
		
		JPanel printers = new JPanel(new FlowLayout());
		JButton ip;
		for(int i = 0; i < listPluginDescriptor.size() ; ++i){
			ip = new JButton(listPluginDescriptor.get(i).getProperties().get("name"));
			printers.add(ip);
		}
		
		gbc.gridx = 10;
		gbc.gridy = 3;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gb.setConstraints(printers, gbc);
		this.add(printers);
		
	}

	public IAgenda getAgenda() {
		return this.agenda;
	}

	public void refreshPrinter() {
		this.remove(printAgenda);
		this.printAgenda = this.printer.display(this.agenda);
		
		gbc.gridx = 3;
		gbc.gridy = 0;
		gbc.gridheight = 9;
		gbc.gridwidth = nbPrinters;
		gb.setConstraints(printAgenda, gbc);
		this.add(printAgenda);
		this.revalidate();
		this.repaint();
	}
	
	
	
	
}
