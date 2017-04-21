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
		
		//TODO: use agenda as a plugin
		IAgenda agenda = new Agenda();
		
		
		//SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		/*try {
			agenda.addEvent(new Event("Event1", formatter.parse("06/01/2017"), formatter.parse("07/01/2017"), "Anniversaire", "Anniversaire de Keltoum", "Chez moi"));
			agenda.addEvent(new Event("Event2", formatter.parse("06/02/2017"), formatter.parse("07/02/2017"), "Anniversaire", "Anniversaire de Yasmine", "Chez moi"));
			agenda.addEvent(new Event("Event3", formatter.parse("06/03/2017"), formatter.parse("07/03/2017"), "Anniversaire", "Anniversaire de Margaux", "Chez moi"));
			agenda.addEvent(new Event("Event4", formatter.parse("06/04/2017"), formatter.parse("07/04/2017"), "Anniversaire", "Anniversaire de Marwan", "Chez moi"));
			agenda.addEvent(new Event("Event5", formatter.parse("06/05/2017"), formatter.parse("07/05/2017"), "Anniversaire", "Anniversaire de Pper", "Chez moi"));
			agenda.addEvent(new Event("Event6", formatter.parse("06/06/2017"), formatter.parse("07/06/2017"), "Anniversaire", "Anniversaire de Khemi", "Chez moi"));
			agenda.addEvent(new Event("Event7", formatter.parse("06/07/2017"), formatter.parse("07/07/2017"), "Anniversaire", "Anniversaire de Samy", "Chez moi"));
			agenda.addEvent(new Event("Event8", formatter.parse("06/08/2017"), formatter.parse("07/08/2017"), "Anniversaire", "Anniversaire de Quentin", "Chez moi"));
			
		} catch (ParseException e) {
			e.printStackTrace();
		}*/
			
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
				public void windowOpened(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowClosed(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowIconified(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowDeiconified(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowActivated(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowDeactivated(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}
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