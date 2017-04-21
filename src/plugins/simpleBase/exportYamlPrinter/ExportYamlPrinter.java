package plugins.simpleBase.exportYamlPrinter;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import plugins.simpleBase.IAgenda;
import plugins.simpleBase.IEvent;
import plugins.simpleBase.IPrinter;

/**
 * Defines a printer plugin to export a given agenda. 
 */
public class ExportYamlPrinter implements IPrinter {
	
	private IAgenda a;
	private JPanel panel;
	private JButton exportButton;
	private JButton browseButton;
	private JTextField fileTextField;
	
	@Override
	public JPanel display(IAgenda a) {
		this.a = a;
		
		this.panel = new JPanel();
		
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
		
		return this.panel;
	}
	
	/**
	 * Creates the new file .yaml that will contain the export. 
	 * @param filename the name of the new file
	 */
	protected void createYaml(String filename){
		try {
			int cpt = 0;
			
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			PrintWriter writer = new PrintWriter(filename);
			
			for (IEvent e : a.getEvents()) {
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
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns button for opening the browser.
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
	public void handleEvent(String event, Object args) {
		
	}

	@Override
	public void startUp() {
		
	}
}
