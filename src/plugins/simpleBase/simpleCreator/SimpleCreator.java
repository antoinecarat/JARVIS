package plugins.simpleBase.simpleCreator;

import java.util.List;

import platform.IPlugin;
import plugins.simpleBase.IAgenda;
import plugins.simpleBase.ICreator;

public class SimpleCreator implements ICreator, IPlugin {

	@Override
	public void create(IAgenda a) {
		CreationFrame popup = new CreationFrame(a);
		popup.setVisible(true);
	}

	@Override
	public void handleEvent(String event, Object args) {
		// TODO Auto-generated method stub
		
	}

}
