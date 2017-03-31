package plugins.simpleModifier;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;

import client.Event;
import client.IEvent;

public class ModifyListener implements ActionListener {

	ModifierEventFrame frame;
	IEvent event;
	
	public ModifyListener(ModifierEventFrame frame, IEvent event) {
		super();
		this.frame = frame;
		this.event = event;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ArrayList<String> fieldsContent = (ArrayList<String>) frame.getFieldsContent();
		Field[] fields = Event.class.getDeclaredFields();
		Class<?> cl;
		Method method;
		
		for (int i = 0 ; i < fieldsContent.size() ; ++i) {
			String content = fieldsContent.get(i);
			String field = fields[i].getName();
			try {
				cl = fields[i].getClass();
				method = event.getClass().getMethod("set"+upFirstChar(field), cl);
				
				if(cl.equals(Date.class)){
					method.invoke(event, new Date(content));
				}else{
					method.invoke(event, content);
				}

			} catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
				e1.printStackTrace();
			}
			
		}
		frame.dispose();
	}
	
	private String upFirstChar(String toUp){
		return toUp.substring(0, 1).toUpperCase() + toUp.substring(1);
	}

}
