package plugins.simpleModifier;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import client.IEvent;
import platform.IPluginDescriptor;
import platform.Platform;
import platform.plugins.IModifier;
import plugins.simpleBase.AgendaFrame;

public class ModifierListener implements ActionListener{

	IModifier modifier;
	List<IPluginDescriptor> list;
	int index;
	AgendaFrame frame;
	IEvent event;

	public ModifierListener(int index, List<IPluginDescriptor> list, AgendaFrame frame, IEvent event) {
		super();
		this.index = index;
		this.list = list;
		this.frame = frame;
		this.event = event;
	}


	public void actionPerformed(ActionEvent arg0) {
		this.modifier = (IModifier) Platform.loadPlugin(list.get(index), IModifier.class);
		if (modifier != null){
			this.modifier.modify(frame, frame.getAgenda(), event);
		}
	}

}
