package plugins.exportPrinter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;

public class ExportActionListener implements ActionListener {
	private ExportPrinter exportPrinter;
	
	protected ExportActionListener(ExportPrinter ep){
		this.exportPrinter = ep;
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		if(e.getSource().equals(exportPrinter.getBrowseButton())){
			JFileChooser fc = new JFileChooser();
			int result = fc.showSaveDialog(null);
			if(result == JFileChooser.APPROVE_OPTION){
				String filename = fc.getSelectedFile().toString();
				filename = filename + ".yaml";
				System.out.println(filename);
				exportPrinter.getFileTextField().setText(filename);
			}
		}else if(e.getSource().equals(exportPrinter.getExportButton())){
			exportPrinter.createYaml(exportPrinter.getFileTextField().getText());
		}
		
		
		
	}
}
