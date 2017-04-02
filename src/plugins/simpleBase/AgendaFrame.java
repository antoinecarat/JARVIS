package plugins.simpleBase;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import client.Event;
import client.IAgenda;
import platform.IPluginDescriptor;
import platform.Platform;
import platform.plugins.ICreator;
import platform.plugins.IPrinter;
import plugins.simpleCreator.CreateListener;

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
	int nbCreators;
	JLabel[] labels;
	JTextField[] textFields;
	List<IPrinter> running_printers;
	JScrollPane scroll;
	
	

	public AgendaFrame(IAgenda agenda) throws HeadlessException, ClassNotFoundException {
		super();
		this.agenda = agenda;
		
		this.setTitle("Agenda JARVIS");
		this.setSize(800, 600);
		this.setLocation(0, 100);
		//this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		gb = new GridBagLayout();
		this.setLayout(gb);
		
		this.createEvent = new JPanel();
		Field[] fields = Event.class.getDeclaredFields();
		createEvent.setLayout(new GridLayout(fields.length, 2));
		//TODO: button per creator
		
		
		List<IPluginDescriptor> listPrinters = Platform.getExtensions(IPrinter.class);
		nbPrinters = listPrinters.size();
		List<IPluginDescriptor> listCreators = Platform.getExtensions(ICreator.class);
		nbCreators = listCreators.size();
		
		Map<String, Object> prop = new HashMap<String, Object>();
		prop.put("default", "True");
		List<IPluginDescriptor> defaults = Platform.getExtensions(IPrinter.class, prop);
		IPluginDescriptor defaultPrinter = defaults.size() > 0 ? defaults.get(0) : listPrinters.get(0);
		this.printer = (IPrinter) Platform.loadPlugin(defaultPrinter, IPrinter.class);

		
		if(this.printer == null){
			this.printAgenda = new JPanel();
		} else {
			this.printAgenda = printer.display(agenda, this);
		}
		
		for(int i=0; i<listCreators.size(); ++i){
			JButton button = new JButton(listCreators.get(i).getProperties().get("verbose"));
			button.addActionListener(new OpenCreatorListener(this, listCreators, i));
			createEvent.add(button);
		}

		
		/*JPanel create = new JPanel(new FlowLayout());
		JButton createButton = new JButton("Create new Event");
		createButton.addActionListener(new CreateListener(this));
		create.add(createButton);*/
		
		JPanel printers = new JPanel(new FlowLayout());
		JButton ip;
		for(int i = 0; i < listPrinters.size() ; ++i){
			ip = new JButton(listPrinters.get(i).getProperties().get("verbose"));
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
		gbc_createPanel.gridheight = nbCreators;
		gbc_createPanel.gridwidth = 1;
		gb.setConstraints(createEvent, gbc_createPanel);
		this.add(createEvent);
		
		//PrintPanel
		gbc_printPanel.gridx = 2;
		gbc_printPanel.gridy = 0;
		gbc_printPanel.gridheight = fields.length;
		gbc_printPanel.gridwidth = nbPrinters;
		
		scroll = new JScrollPane();
		scroll.setPreferredSize(new Dimension(650, 200));
		scroll.setViewportView(printAgenda);
		gb.setConstraints(scroll, gbc_printPanel);
		this.add(scroll);
	
		//CreateButtons
		/*gbc_createButton.gridx = 1;
		gbc_createButton.gridy = fields.length + 1;
		gbc_createButton.gridheight = 1;
		gbc_createButton.gridwidth = 1;
		gb.setConstraints(create, gbc_createButton);
		this.add(create);*/
		
		//PrintButtons
		gbc_printButtons.gridx = 2;
		gbc_printButtons.gridy = fields.length + 1;
		gbc_printButtons.gridheight = 1;
		gbc_printButtons.gridwidth = nbPrinters;
		gb.setConstraints(printers, gbc_printButtons);
		this.add(printers);
		
		
	}

	public IAgenda getAgenda() {
		return this.agenda;
	}

	public void refreshPrinter() {
		this.remove(scroll);
		this.remove(printAgenda);
		
		if(this.printer == null){
			this.printAgenda = new JPanel();
		} else {
			this.printAgenda = printer.display(agenda, this);
		}
		scroll = new JScrollPane();
		gb.setConstraints(printAgenda, gbc_printPanel);
		scroll.setPreferredSize(new Dimension(650, 200));
		scroll.setViewportView(printAgenda);
		gb.setConstraints(scroll, gbc_printPanel);
		this.add(scroll);
		this.revalidate();
		this.repaint();
	}
	
	public void changePrinter(int index) {
		List<IPluginDescriptor> listPluginDescriptor;
		try {
			listPluginDescriptor = Platform.getExtensions(IPrinter.class);
			this.printer = (IPrinter) Platform.loadPlugin(listPluginDescriptor.get(index), IPrinter.class);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
