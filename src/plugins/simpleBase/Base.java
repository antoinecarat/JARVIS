package plugins.simpleBase;

import java.awt.HeadlessException;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import platform.IPlugin;
import platform.Platform;
import platform.UnkillableException;

/**
 * Defines the agenda application which is calling other plugins.
 */
public class Base implements IPlugin {

	AgendaFrame frame;
	
	@Override
	public void startUp() {
		
		IAgenda agenda = new Agenda();
			
		try {
			frame = new AgendaFrame(agenda, this);
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
		} catch (HeadlessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Platform.subscribeEvent("event", this);
	}

	@Override
	public void handleEvent(String event, Object args) {
		frame.refreshPrinter();
	}
}