package plugins.simpleModifier;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import client.IAgenda;
import client.IEvent;
import platform.plugins.IModifier;

public class SimpleModifier implements IModifier {

	public void modify(IAgenda a, IEvent e, Map<String, Object> list) throws NoSuchFieldException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		Object field;
		Method method;
		
		for (IEvent event : a.getEvents()) {
			if(event.equals(e)){
				
				for (String key : list.keySet()) {
					
					field = event.getClass().getDeclaredField(key);
					try {
						method = event.getClass().getMethod("set"+upFirstChar(key), field.getClass());
					
						method.invoke(event, list.get(key));

					} catch (NoSuchMethodException e1) {
						e1.printStackTrace();
					}
					
				}
				
			}
		}
	}
	
	private String upFirstChar(String toUp){
		return toUp.substring(0, 1).toUpperCase() + toUp.substring(1);
	}


}
