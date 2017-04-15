package plugins.simpleBase.modifierPrinter;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import platform.IPlugin;
import platform.IPluginDescriptor;
import platform.Platform;
import plugins.simpleBase.AgendaFrame;
import plugins.simpleBase.IAgenda;
import plugins.simpleBase.IEvent;
import plugins.simpleBase.IModifier;
import plugins.simpleBase.IPrinter;
import plugins.simpleBase.simpleModifier.ModifierListener;


public class ModifierPrinter implements IPrinter, IPlugin{

	/**
	 * Returns events display with buttons for each modifier.
	 * @param a the agenda to which the events collection 
	 * @param frame the application JPanel for refreshing
	 * @return the new JPanel with the events collection
	 */
	public JPanel display(IAgenda a) {
		
		JPanel panel = new JPanel();
		JPanel buttons = null; 
		JButton button;
		JLabel label;
		GridLayout grid = new GridLayout(a.getEvents().size(), 2);
		GridLayout subGrid;
		
		List<IPluginDescriptor> modifiers;
		panel.setLayout(grid);

		try {

			modifiers = Platform.getPlugins(IModifier.class);
			
			for (IEvent event : a.getEvents()) {
				
				label = new JLabel(event.toString());

				panel.add(label);
				
				subGrid = new GridLayout(1, modifiers.size());
				buttons = new JPanel();
				int i = 0;
				for (IPluginDescriptor iPluginDescriptor : modifiers) {
					button = new JButton((String) iPluginDescriptor.getProperties().get("verbose"));
					ModifierListener listener = new ModifierListener(i, modifiers, a, event);
					
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
