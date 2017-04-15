package plugins.simpleBase.exportYamlPrinter;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import platform.IPlugin;
import plugins.simpleBase.IPrinter;
import client.IAgenda;
import client.IEvent;

/**
 * Plugin to export the events collection.
 */
public class ExportYamlPrinter implements IPrinter, IPlugin {
	
	private IAgenda a;
	
	JButton exportButton;
	JButton browseButton;
	JTextField fileTextField;
	
	@Override
	public JPanel display(IAgenda a) {
		this.a = a;
		
		JPanel panel = new JPanel();
		
		this.browseButton = new JButton("Browse");
		this.exportButton = new JButton("Export to yaml");
		this.fileTextField = new JTextField(35);
		
		ActionListener actionListener = new ExportYamlActionListener(this);
		this.browseButton.addActionListener(actionListener);
		this.exportButton.addActionListener(actionListener);
		
		this.fileTextField.setEditable(false);
		this.exportButton.setEnabled(false);
		
		panel.add(fileTextField);
		panel.add(browseButton);
		panel.add(exportButton);
		
		panel.setLayout(new FlowLayout());
		
		return panel;
	}
	
	/**
	 * Creates the new file .yaml that will contain the export. 
	 * @param filename the name of the new file
	 */
	protected void createYaml(String filename){
		try {
			Map<Integer, Object> data = new HashMap<Integer, Object>();
			
			int cpt = 0;
			
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			PrintWriter writer = new PrintWriter(filename);
			
			for (IEvent e : a.getEvents()) {
				//data.put(cpt, (Object)e);
				writer.println(Integer.toString(cpt) + ": ");
				writer.println("  name: " + e.getName());
				writer.println("  startDate: " + formatter.format(e.getStartDate()));
				writer.println("  endDate: " + formatter.format(e.getEndDate()));
				writer.println("  type: " + e.getType());
				writer.println("  descritpion: " + e.getDescription());
				writer.println("  lociation: " + e.getLocation());
				++cpt;
			}
			writer.close();
			
//			write in the file
			//Yaml yaml = new Yaml();
			//FileWriter writer = new FileWriter(filename);
			//yaml.dump(data, writer);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns button for openning the browser.
	 * @return button Browse
	 */
	protected JButton getBrowseButton(){
		return this.browseButton;
	}

	/**
	 * Returns button for exporting events collection.
	 * @return button Export
	 */
	protected JButton getExportButton(){
		return this.exportButton;
	}
	
	/**
	 * Returns textField which contains the file path.
	 * @return textField
	 */
	protected JTextField getFileTextField(){
		return this.fileTextField;
	}

	@Override
	public void handleEvent(String event) {
		// TODO Auto-generated method stub
		
	}
}
