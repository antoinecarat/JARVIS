package plugins.modifierPrinter;

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
import plugins.simpleBase.AgendaFrame;
import plugins.simpleModifier.ModifierListener;

public class ModifierPrinter implements IPrinter{

	public JPanel display(IAgenda a, AgendaFrame frame) {
		
		JPanel panel = new JPanel();
		JPanel buttons = null; 
		JButton button;
		IPrinter printer;
		JLabel label;
		GridLayout grid = new GridLayout(a.getEvents().size(), 2);
		GridLayout subGrid;
		
		List<IPluginDescriptor> modifiers;

		try {

			modifiers = Platform.getExtensions(IModifier.class);
			
			for (IEvent event : a.getEvents()) {
				
				label = new JLabel(event.toString());

				panel.add(label);
				
				subGrid = new GridLayout(1, modifiers.size());
				buttons = new JPanel();
				int i = 0;
				for (IPluginDescriptor iPluginDescriptor : modifiers) {
					button = new JButton(iPluginDescriptor.getProperties().get("name"));
					button.addActionListener(new ModifierListener(i, modifiers, frame, event));
					buttons.add(button);
					buttons.setLayout(subGrid);
					++i;
				}
				
				panel.add(buttons);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return panel;
	}

}
