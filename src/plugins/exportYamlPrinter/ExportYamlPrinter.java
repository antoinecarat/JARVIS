package plugins.exportYamlPrinter;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.yaml.snakeyaml.Yaml;

import client.IAgenda;
import client.IEvent;
import platform.plugins.IPrinter;
import plugins.simpleBase.AgendaFrame;

/**
 * Plugin to export the collection events.
 */
public class ExportYamlPrinter implements IPrinter{
	
	private IAgenda a;
	
	JButton exportButton;
	JButton browseButton;
	JTextField fileTextField;
	
	@Override
	public JPanel display(IAgenda a, AgendaFrame frame) {
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
			
			for (IEvent e : a.getEvents()) {
				data.put(cpt, (Object)e);
				++cpt;
			}
			
//			write in the file
			Yaml yaml = new Yaml();
			FileWriter writer = new FileWriter(filename);
			yaml.dump(data, writer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns button for open the browser.
	 * @return button for the browser
	 */
	protected JButton getBrowseButton(){
		return this.browseButton;
	}

	/**
	 * Returns button for export events collection.
	 * @return button for the export
	 */
	protected JButton getExportButton(){
		return this.exportButton;
	}
	/**
	 * getFileTextFiled, 
	 * @return
	 */
	protected JTextField getFileTextField(){
		return this.fileTextField;
	}
}
