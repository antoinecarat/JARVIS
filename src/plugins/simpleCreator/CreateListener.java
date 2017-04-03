package plugins.simpleCreator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import client.Event;
import plugins.simpleBase.AgendaFrame;

public class CreateListener implements ActionListener {

	CreationFrame frame;
	AgendaFrame aFrame;
	
	public CreateListener(CreationFrame frame, AgendaFrame aFrame) {
		super();
		this.frame = frame;
		this.aFrame = aFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		List<String> fieldsContent = frame.getFieldsContent();
		
		if (fieldsContent.size() == frame.getLabels().length){
			
			Field fields[] = Event.class.getDeclaredFields();
			Object contents[] = new Object[fields.length];
			Class<?> paramTypes[] = new Class<?>[fields.length];
			Constructor<Event> m;
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			try {
				
				for (int i = 0 ; i < fields.length ; ++i) {
					paramTypes[i] = fields[i].getType();
					
					if (paramTypes[i].equals(Date.class)){
							contents[i] = formatter.parse(fieldsContent.get(i));
						
					} else {
						contents[i] = (String) fieldsContent.get(i);
					}
				}
				
				Event event;
				
				m = Event.class.getConstructor(paramTypes);
		
				event = m.newInstance(contents);
				
				aFrame.getAgenda().addEvent(event);
				aFrame.refreshPrinter();
				frame.dispose();
				
			} catch (InvocationTargetException e1){
				try {
					throw e1.getCause();
				} catch (IllegalArgumentException e11){
					System.out.println("Wrong Date value");
				} catch (Throwable e2) {
					e2.printStackTrace();
				}
			} catch (InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException e1) {
			} catch (ParseException e1) {
				System.out.println("Wrong date format");
			}

		} else {
			//TODO: Replace it with a JDialog.
			System.out.println("Fill fields please.");
			//JDialog dial = new JDialog(frame, "Fill fields please.", true);
			//dial.setVisible(true);
		}
	}

}
