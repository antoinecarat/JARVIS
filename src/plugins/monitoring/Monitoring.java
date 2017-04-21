package plugins.monitoring;

import java.awt.HeadlessException;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import platform.IPlugin;
import platform.Platform;
import platform.UnkillableException;

/**
 * Defines a monitoring plugin showing each plugin status and some logs.
 */
public class Monitoring implements IPlugin {

	private MonitorFrame frame;
	
	@Override
	public void startUp() {
		
		try {
			frame = new MonitorFrame(this);
			frame.setVisible(true);
			frame.addWindowListener(new WindowListener() {

	            @Override
	            public void windowClosing(WindowEvent e) {
	                frame.setVisible(false);
	                frame.dispose();
	                try {
						Platform.killPlugin(frame.getPlugin());
					} catch (UnkillableException e1) {
						
					}
	            }

				@Override
				public void windowOpened(WindowEvent e) {}

				@Override
				public void windowClosed(WindowEvent e) {}

				@Override
				public void windowIconified(WindowEvent e) {}

				@Override
				public void windowDeiconified(WindowEvent e) {}

				@Override
				public void windowActivated(WindowEvent e) {}

				@Override
				public void windowDeactivated(WindowEvent e) {}
	        });
		} catch (HeadlessException e) {
			e.printStackTrace();
		}
	    Platform.subscribeEvent("plugin", this);
	}

	@Override
	public void handleEvent(String event, Object args) {
		frame.refresh(event, args);
	}
}
