package plugins.simpleBase;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Label;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import client.IAgenda;
import platform.IPluginDescriptor;
import platform.Platform;
import platform.plugins.IPrinter;

public class AgendaFrame extends JFrame {

	IAgenda agenda;
	JPanel createEvent;
	JPanel printAgenda;
	IPrinter printer;
	

	public AgendaFrame(IAgenda agenda) throws HeadlessException, ClassNotFoundException {
		super();
		this.agenda = agenda;
		
		this.setTitle("Agenda JARVIS");
		this.setSize(800,600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		this.setLayout(new GridLayout(2, 2, 0, 1));
		
		this.createEvent = new JPanel();
		createEvent.setLayout(new GridLayout(9, 2));
		
		String label[] = {"name", "dateDebut", "dateFin", "mail", "tel", "frequence", "type", "description", "lieu"};

		JTextField tf;
		for(int i=0; i < label.length; ++i){
			tf = new JTextField();
			createEvent.add(new Label(label[i]));
			createEvent.add(tf);
		}
		this.add(createEvent);
		
		List<IPluginDescriptor> listPluginDescriptor = Platform.getExtensions(IPrinter.class);
		this.printer = (IPrinter) Platform.loadPlugin(listPluginDescriptor.get(0), IPrinter.class);
		this.printAgenda = printer.display(agenda);
		this.add(printAgenda);
		
		JPanel create = new JPanel(new FlowLayout());
		JButton createButton = new JButton("Create new Event");
		createButton.addActionListener(new CreateListener(this));
		create.add(createButton);
		this.add(create);
		
		JPanel printers = new JPanel(new FlowLayout());
		JButton ip;
		for(int i = 0; i < listPluginDescriptor.size() ; ++i){
			ip = new JButton(listPluginDescriptor.get(i).getProperties().get("name"));
			printers.add(ip);
		}
		this.add(printers);
		
	}

	public IAgenda getAgenda() {
		return this.agenda;
	}

	public void refreshPrinter() {
		this.remove(printAgenda);
		this.printAgenda = this.printer.display(this.agenda);
		this.add(printAgenda);
		this.revalidate();
		this.repaint();
	}
	
	
	
	
}
