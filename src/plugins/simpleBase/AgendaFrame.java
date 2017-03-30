package plugins.simpleBase;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Label;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import client.Event;
import client.IAgenda;
import platform.IPluginDescriptor;
import platform.Platform;
import platform.plugins.IPrinter;

public class AgendaFrame extends JFrame {

	IAgenda agenda;
	JPanel createEvent;
	JPanel printAgenda;
	IPrinter printer;
	GridBagConstraints gbc_createPanel;
	GridBagConstraints gbc_createButton;
	GridBagConstraints gbc_printPanel;
	GridBagConstraints gbc_printButtons;
	
	GridBagLayout gb;
	int nbPrinters;
	JLabel[] labels;
	JTextField[] textFields;
	List<IPrinter> running_printers;
	
	

	public AgendaFrame(IAgenda agenda) throws HeadlessException, ClassNotFoundException {
		super();
		this.agenda = agenda;
		
		this.setTitle("Agenda JARVIS");
		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		gb = new GridBagLayout();
		this.setLayout(gb);
		
		this.createEvent = new JPanel();
		Field[] fields = Event.class.getDeclaredFields();
		createEvent.setLayout(new GridLayout(fields.length, 2));
		
		List<IPluginDescriptor> listPluginDescriptor = Platform.getExtensions(IPrinter.class);
		nbPrinters = listPluginDescriptor.size();
		
		Map<String, Object> prop = new HashMap<String, Object>();
		prop.put("default", "True");
		List<IPluginDescriptor> defaults = Platform.getExtensions(IPrinter.class, prop);
		IPluginDescriptor defaultPrinter = defaults.size() > 0 ? defaults.get(0) : listPluginDescriptor.get(0);
		this.printer = (IPrinter) Platform.loadPlugin(defaultPrinter, IPrinter.class);
				
		this.running_printers = new ArrayList<IPrinter>();
		for(int i=0; i< nbPrinters; ++i){
			running_printers.add(null);
		}
		
		int index = listPluginDescriptor.indexOf(defaultPrinter);
		this.running_printers.set(index, this.printer);
		
		if(this.printer == null){
			this.printAgenda = new JPanel();
		} else {
			this.printAgenda = printer.display(agenda);
		}
		
		labels = new JLabel[fields.length];
		for (int i = 0; i < fields.length; ++i){
			labels[i] = new JLabel(fields[i].getName());
		}
		
		textFields = new JTextField[labels.length];
		for(int i=0; i < labels.length; ++i){
			createEvent.add(labels[i]);
			textFields[i] = new JTextField();
			createEvent.add(textFields[i]);
		}
		
		JPanel create = new JPanel(new FlowLayout());
		JButton createButton = new JButton("Create new Event");
		createButton.addActionListener(new CreateListener(this));
		create.add(createButton);
		
		JPanel printers = new JPanel(new FlowLayout());//TODO use a card layout
		JButton ip;
		for(int i = 0; i < listPluginDescriptor.size() ; ++i){
			ip = new JButton(listPluginDescriptor.get(i).getProperties().get("name"));
			ip.addActionListener(new ChangePrinterListener(this, i));
			printers.add(ip);
		}
		
		//GBConstraints
		gbc_createPanel = new GridBagConstraints();
		gbc_createPanel.anchor = GridBagConstraints.NORTH;
		gbc_createButton = new GridBagConstraints();
		gbc_createButton.anchor = GridBagConstraints.NORTH;
		gbc_printPanel = new GridBagConstraints();
		gbc_printPanel.anchor = GridBagConstraints.NORTH;
		gbc_printButtons = new GridBagConstraints();
		gbc_printButtons.anchor = GridBagConstraints.NORTH;
		
		//CreatePanel
		gbc_createPanel.gridx = 0;
		gbc_createPanel.gridy = 0;
		gbc_createPanel.gridheight = fields.length;
		gbc_createPanel.gridwidth = 2;
		gb.setConstraints(createEvent, gbc_createPanel);
		this.add(createEvent);
		
		//PrintPanel
		gbc_printPanel.gridx = 2;
		gbc_printPanel.gridy = 0;
		gbc_printPanel.gridheight = 9;
		gbc_printPanel.gridwidth = nbPrinters;
		gb.setConstraints(printAgenda, gbc_printPanel);
		this.add(printAgenda);
		
		//CreateButtons
		gbc_createButton.gridx = 1;
		gbc_createButton.gridy = 10;
		gbc_createButton.gridheight = 1;
		gbc_createButton.gridwidth = 1;
		gb.setConstraints(create, gbc_createButton);
		this.add(create);
		
		//PrintButtons
		gbc_printButtons.gridx = 2;
		gbc_printButtons.gridy = 10;
		gbc_printButtons.gridheight = 1;
		gbc_printButtons.gridwidth = nbPrinters;
		gb.setConstraints(printers, gbc_printButtons);
		this.add(printers);
		
	}

	public IAgenda getAgenda() {
		return this.agenda;
	}

	public void refreshPrinter() {
		this.remove(printAgenda);
		
		if(this.printer == null){
			this.printAgenda = new JPanel();
		} else {
			this.printAgenda = printer.display(agenda);
		}
		
		gb.setConstraints(printAgenda, gbc_printPanel);
		this.add(printAgenda);
		this.revalidate();
		this.repaint();
	}
	
	public List<String> getFieldsContent(){
		List<String> content = new ArrayList<String>();
		for (JTextField t : textFields){
			if(t.getText().length()>0){
				content.add(t.getText());
			}
		}
		
		return content;
		
	}

	public void changePrinter(int index) {
		List<IPluginDescriptor> listPluginDescriptor;
		try {
			if (running_printers.size() > index && running_printers.get(index) != null){
				this.printer = running_printers.get(index);
			} else {
				listPluginDescriptor = Platform.getExtensions(IPrinter.class);
				this.printer = (IPrinter) Platform.loadPlugin(listPluginDescriptor.get(index), IPrinter.class);
				this.running_printers.set(index, this.printer);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	
	
	
}
