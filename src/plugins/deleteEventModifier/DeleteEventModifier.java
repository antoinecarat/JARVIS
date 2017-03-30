package plugins.deleteEventModifier;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import client.IAgenda;
import client.IEvent;
import platform.plugins.IModifier;

public class DeleteEventModifier implements IModifier{

	public void modify(IAgenda a, IEvent e) throws NoSuchFieldException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
					
		if(!a.getEvents().remove(e)){
			throw new IllegalArgumentException();
		}
	}
}
