package plugins.simpleModifier;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import client.IAgenda;
import client.IEvent;
import platform.plugins.IModifier;
import plugins.simpleBase.AgendaFrame;

public class SimpleModifier implements IModifier {

	public void modify(AgendaFrame frame, IAgenda a, IEvent e) {
		ModifierEventFrame popup = new ModifierEventFrame(frame, e);
		popup.setVisible(true);
		frame.refreshPrinter();
	}

}
