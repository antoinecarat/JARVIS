package plugins.modifierPrinter;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import client.IAgenda;
import client.IEvent;
import platform.IPluginDescriptor;
import platform.Platform;
import platform.plugins.IModifier;
import platform.plugins.IPrinter;

public class ModifierPrinter implements IPrinter{

	public JPanel display(IAgenda a, JFrame frame) {
		
		JPanel panel = new JPanel();
		JPanel buttons = null; 
		JButton button;
		IPrinter printer;
		JLabel label;
		GridLayout grid = new GridLayout(a.getEvents().size(), 2);
		GridLayout subGrid;
		
		List<IPluginDescriptor> listPluginDescriptor;

		try {

			listPluginDescriptor = Platform.getExtensions(IModifier.class);
			
			for (IEvent event : a.getEvents()) {
				
				label = new JLabel(event.toString());

				panel.add(label);
				
				subGrid = new GridLayout(1, listPluginDescriptor.size());
				buttons = new JPanel();
				
				for (IPluginDescriptor iPluginDescriptor : listPluginDescriptor) {
					button = new JButton(iPluginDescriptor.getProperties().get("name"));
					//TODO: ajouter le lien entre le bouton et le modify ? (wtf ? je sais pas comment faire)
					//button.addActionListener(new );
					buttons.add(button);
					buttons.setLayout(subGrid);
				}
				
				panel.add(buttons);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
			
		
		
		return panel;
	}

}
