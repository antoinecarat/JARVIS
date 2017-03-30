package platform.plugins;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import client.IAgenda;
import client.IEvent;

public interface IModifier {

	void modify(IAgenda a, IEvent e) throws NoSuchFieldException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException;

}