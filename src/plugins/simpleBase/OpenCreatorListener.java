package plugins.simpleBase;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import platform.IPlugin;
import platform.IPluginDescriptor;
import platform.Platform;
import platform.UnkillableException;

/**
 * Manages the opening of creators
 */
public class OpenCreatorListener implements ActionListener {

	AgendaFrame frame;
	int index;
	ICreator creator;
	List<IPluginDescriptor> list;
	
	public OpenCreatorListener(AgendaFrame frame, List<IPluginDescriptor> list, int index) {
		super();
		this.frame = frame;
		this.list = list;
		this.index = index;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.creator = (ICreator) Platform.loadPlugin(list.get(index), ICreator.class);
		if (this.creator != null){
			this.creator.create(this.frame.getAgenda());
			try {
				Platform.killPlugin((IPlugin)this.creator);
			} catch (UnkillableException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
}
