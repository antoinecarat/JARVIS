package plugins.modifierPrinter;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import platform.IPluginDescriptor;
import platform.Platform;
import platform.plugins.IModifier;
import platform.plugins.IPlugin;
import platform.plugins.IPrinter;
import plugins.simpleBase.AgendaFrame;
import plugins.simpleModifier.ModifierListener;
import client.IAgenda;
import client.IEvent;

public class ModifierPrinter implements IPrinter, IPlugin{

	/**
	 * Returns events display with buttons for each modifier.
	 * @param a the agenda to which the events collection 
	 * @param frame the application JPanel for refreshing
	 * @return the new JPanel with the events collection
	 */
	public JPanel display(IAgenda a, AgendaFrame frame) {
		
		JPanel panel = new JPanel();
		JPanel buttons = null; 
		JButton button;
		JLabel label;
		GridLayout grid = new GridLayout(a.getEvents().size(), 2);
		GridLayout subGrid;
		
		List<IPluginDescriptor> modifiers;
		panel.setLayout(grid);

		try {

			modifiers = Platform.getExtensions(IModifier.class);
			
			for (IEvent event : a.getEvents()) {
				
				label = new JLabel(event.toString());

				panel.add(label);
				
				subGrid = new GridLayout(1, modifiers.size());
				buttons = new JPanel();
				int i = 0;
				for (IPluginDescriptor iPluginDescriptor : modifiers) {
					button = new JButton(iPluginDescriptor.getProperties().get("verbose"));
					ModifierListener listener = new ModifierListener(i, modifiers, frame, event);
					
					button.addActionListener(listener);
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

	@Override
	public void handleEvent(String event) {
		// TODO Auto-generated method stub
		
	}

}
