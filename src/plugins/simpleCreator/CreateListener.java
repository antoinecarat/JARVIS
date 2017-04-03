package plugins.simpleCreator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
		
		if (fieldsContent.size() > frame.getLabels().length){
			
			Class<?> cl;
			Method method;
			Field fields[] = Event.class.getDeclaredFields();
			Object contents[] = new Object[fields.length];
			Class<?> paramTypes[] = new Class<?>[fields.length];
			
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			
			for (int i = 0 ; i < fields.length ; ++i) {
				paramTypes[i] = fields[i].getType();
				
				if (paramTypes[i].equals(Date.class)){
					try {
						contents[i] = formatter.parse(fieldsContent.get(i));
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					contents[i] = (String) fieldsContent.get(i);
				}
			}
			
			Event event;
			try {
				
				Constructor<Event> m = Event.class.getConstructor(paramTypes);
				event = m.newInstance(contents);
				
				aFrame.getAgenda().addEvent(event);
				aFrame.refreshPrinter();
				
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (NoSuchMethodException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SecurityException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} else {
			//TODO: Replace it with a JDialog.
			System.out.println("Fill fields please.");
			//JDialog dial = new JDialog(frame, "Fill fields please.", true);
			//dial.setVisible(true);
		}
	}
	
	private String upFirstChar(String toUp){
		return toUp.substring(0, 1).toUpperCase() + toUp.substring(1);
	}

}
