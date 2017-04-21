package plugins.simpleBase.exportYamlPrinter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * Defines an {@link ActionListener} that manage the actual export for export plugin.
 */
public class ExportYamlActionListener implements ActionListener {
	private ExportYamlPrinter exportPrinter;
	
	protected ExportYamlActionListener(ExportYamlPrinter ep){
		this.exportPrinter = ep;
	}
	
	/**
	 * Exports the events collection and display if the export went well.
	 * @param e  
	 */
	@Override
	public void actionPerformed(ActionEvent e){
		if(e.getSource().equals(exportPrinter.getBrowseButton())){
			JFileChooser fc = new JFileChooser();
			int result = fc.showSaveDialog(null);
			
			if(result == JFileChooser.APPROVE_OPTION){
				String filename = fc.getSelectedFile().toString();
				
				if(!filename.contains(".yaml")){
					filename = filename + ".yaml";
				}
				
				exportPrinter.getFileTextField().setText(filename);
				
				exportPrinter.getExportButton().setEnabled(true);
			}
			
		}else if(e.getSource().equals(exportPrinter.getExportButton())){
			
			exportPrinter.createYaml(exportPrinter.getFileTextField().getText());
			
			JOptionPane.showMessageDialog(null, "Export done successfully.");
			exportPrinter.getFileTextField().setText("");
			exportPrinter.getExportButton().setEnabled(false);
		}		
	}
}
