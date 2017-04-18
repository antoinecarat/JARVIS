package plugins.simpleBase;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import platform.IPlugin;
import platform.IPluginDescriptor;
import platform.Platform;
import platform.UnkillableException;
/**
 * This is the frame delivered by Base plugin.
 */
public class AgendaFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
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

	private IPlugin basePlugin;
	
	

	public AgendaFrame(IAgenda agenda, IPlugin base) throws HeadlessException, ClassNotFoundException {
		super();
		this.agenda = agenda;
		this.basePlugin = base;
		
		this.setTitle("Agenda JARVIS");
		this.setSize(800, 600);
		this.setLocation(25, 100);
		this.setResizable(false);
		gb = new GridBagLayout();
		this.setLayout(gb);
		
		this.createEvent = new JPanel();
		Field[] fields = Event.class.getDeclaredFields();
		createEvent.setLayout(new GridLayout(fields.length, 2));
		
		List<IPluginDescriptor> listPrinters = Platform.getPlugins(IPrinter.class);
		nbPrinters = listPrinters.size();
		List<IPluginDescriptor> listCreators = Platform.getPlugins(ICreator.class);
		nbCreators = listCreators.size();
		
		Map<String, Object> prop = new HashMap<String, Object>();
		prop.put("default", true);
		
		if (nbPrinters > 0){
			List<IPluginDescriptor> defaults = Platform.getPlugins(IPrinter.class, prop);
			IPluginDescriptor defaultPrinter = defaults.size() > 0 ? defaults.get(0) : listPrinters.get(0);
			this.printer = (IPrinter) Platform.loadPlugin(defaultPrinter, IPrinter.class);
		}
		
		if(this.printer == null){
			this.printAgenda = new JPanel();
		} else {
			this.printAgenda = printer.display(agenda);
			Platform.raiseEvent("plugin.launched", null);
		}
		
		for(int i=0; i<listCreators.size(); ++i){
			JButton button = new JButton((String) listCreators.get(i).getProperties().get("verbose"));
			button.addActionListener(new OpenCreatorListener(this, listCreators, i));
			createEvent.add(button);
		}
		
		JPanel printers = new JPanel(new FlowLayout());
		JButton ip;
		for(int i = 0; i < listPrinters.size() ; ++i){
			ip = new JButton((String) listPrinters.get(i).getProperties().get("verbose"));
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
		
		//PrintButtons
		gbc_printButtons.gridx = 2;
		gbc_printButtons.gridy = fields.length + 1;
		gbc_printButtons.gridheight = 1;
		gbc_printButtons.gridwidth = nbPrinters;
		gb.setConstraints(printers, gbc_printButtons);
		this.add(printers);
	}

	/**
	 * Returns the agenda.
	 * @return agenda
	 */
	public IAgenda getAgenda() {
		return this.agenda;
	}

	/**
	 * Refreshes printer graphic object.
	 */
	public void refreshPrinter() {
		this.remove(scroll);
		this.remove(printAgenda);
		
		if(this.printer == null){
			this.printAgenda = new JPanel();
		} else {
			this.printAgenda = printer.display(agenda);
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
	
	/**
	 * Switch printer.
	 * @param index the index of the new printer.
	 */
	public void changePrinter(int index) {
		List<IPluginDescriptor> listPluginDescriptor;
		try {
			Platform.killPlugin((IPlugin) this.printer);
		} catch (UnkillableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		listPluginDescriptor = Platform.getPlugins(IPrinter.class);
		this.printer = (IPrinter) Platform.loadPlugin(listPluginDescriptor.get(index), IPrinter.class);
	}

	public IPlugin getPlugin() {
		return this.basePlugin;
	}
	
}
