package plugins.simpleBase;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import platform.IPlugin;
import platform.Platform;
/**
 * Manages the change of printer
 */
public class ChangePrinterListener implements ActionListener {

	AgendaFrame frame;
	int index;
	
	public ChangePrinterListener(AgendaFrame frame, int index) {
		super();
		this.frame = frame;
		this.index = index;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		frame.changePrinter(this.index);
		frame.refreshPrinter();
	}

}
