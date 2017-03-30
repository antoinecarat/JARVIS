package plugins.simpleModifier;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import client.IAgenda;
import client.IEvent;
import platform.plugins.IModifier;
import plugins.modifierPrinter.ModifierEventFrame;

public class SimpleModifier implements IModifier {

	public void modify(IAgenda a, IEvent e) throws NoSuchFieldException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		Map<String, Object> list = new HashMap<String, Object>();
		Object field;
		Method method;
		
		ModifierEventFrame frame = new ModifierEventFrame(e);
		
		frame.setVisible(true);
	}

}
