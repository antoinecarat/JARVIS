package plugins.simpleModifier;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import client.IAgenda;
import client.IEvent;
import platform.IPluginDescriptor;
import platform.Platform;
import platform.plugins.IModifier;
import plugins.simpleBase.AgendaFrame;

/**
 * Manages the call of a modifier
 */
public class ModifierListener implements ActionListener{

	IModifier modifier;
	List<IPluginDescriptor> list;
	int index;
	IAgenda agenda;
	IEvent event;

	public ModifierListener(int index, List<IPluginDescriptor> list, IAgenda agenda, IEvent event) {
		super();
		this.index = index;
		this.list = list;
		this.agenda = agenda;
		this.event = event;
	}


	public void actionPerformed(ActionEvent arg0) {
		this.modifier = (IModifier) Platform.loadPlugin(list.get(index), IModifier.class);
		if (modifier != null){
			this.modifier.modify(agenda, event);
		}
	}

}
