package platform.plugins.modifierPrinter;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import client.IAgenda;
import client.IEvent;
import platform.IPluginDescriptor;
import platform.Platform;
import platform.plugins.IModifier;
import platform.plugins.IPrinter;

public class ModifierPrinter implements IPrinter{

	public JPanel display(IAgenda a) {
		
		JPanel panel = new JPanel();
		JPanel buttons = null; 
		JButton button;
		IPrinter printer;
		JLabel label;
		GridLayout grid = new GridLayout(a.getEvents().size(), 2);
		GridLayout subGrid;
		
		List<IPluginDescriptor> listPluginDescriptor;
		
		for (IEvent event : a.getEvents()) {

			try {
	
				listPluginDescriptor = Platform.getExtensions(IModifier.class);
				label = new JLabel(event.toString());

				panel.add(label);
				
				subGrid = new GridLayout(1, listPluginDescriptor.size());
				
				for (IPluginDescriptor iPluginDescriptor : listPluginDescriptor) {
					printer = (IPrinter) Platform.loadPlugin(iPluginDescriptor, IModifier.class);
					buttons = new JPanel();
					button = new JButton(printer.getClass().getName());
					
					buttons.add(button);
					buttons.setLayout(subGrid);
				}
				
				panel.add(buttons);
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
		}
		
		return panel;
	}

}
