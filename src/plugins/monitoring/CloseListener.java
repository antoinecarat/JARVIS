package plugins.monitoring;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import platform.IPlugin;
import platform.Platform;
import platform.UnkillableException;

public class CloseListener implements ActionListener {

	private IPlugin plugin;
	private JFrame frame;

	public CloseListener(IPlugin plugin, JFrame frame){
		this.plugin = plugin;
		this.frame = frame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			Platform.killPlugin(plugin);
		} catch (UnkillableException e1) {
			
		}
		frame.dispose();
	}

}
