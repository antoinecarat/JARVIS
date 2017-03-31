package plugins.modifierPrinter;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import client.Event;
import client.IEvent;
import plugins.simpleBase.CreateListener;

public class ModifierEventFrame extends JFrame {

	GridBagConstraints gbc_createPanel;
	GridBagConstraints gbc_createButton;
	GridBagLayout gb;
	
	JPanel modifyEvent;

	JTextField[] textFields;
	
	IEvent event;

	Method method;
	JLabel[] labels;

	
	public ModifierEventFrame(IEvent event) throws HeadlessException {
		super();
		this.event = event;
		
		this.setTitle("Modify the event");
		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		gb = new GridBagLayout();
		this.setLayout(gb);
			
		Field[] fields = Event.class.getDeclaredFields();
		modifyEvent.setLayout(new GridLayout(fields.length, 2));

		labels = new JLabel[fields.length];
		for (int i = 0; i < fields.length; ++i){
			labels[i] = new JLabel(fields[i].getName());
		}
		
		textFields = new JTextField[labels.length];
		for(int i=0; i < labels.length; ++i){
			modifyEvent.add(labels[i]);
			textFields[i] = new JTextField();
			
			try {
				method = event.getClass().getMethod("get"+upFirstChar(fields[i].getName()));
			
				String getMethodResult = (String) method.invoke(event);
				
				textFields[i].setText(getMethodResult);
				modifyEvent.add(textFields[i]);
				
				JPanel create = new JPanel(new FlowLayout());
				JButton createButton = new JButton("Create new Event");
				createButton.addActionListener(new ModifierListener(this, event));
				create.add(createButton);
				
				//CreatePanel
				gbc_createPanel.gridx = 0;
				gbc_createPanel.gridy = 0;
				gbc_createPanel.gridheight = fields.length;
				gbc_createPanel.gridwidth = 2;
				gb.setConstraints(modifyEvent, gbc_createPanel);
				this.add(modifyEvent);
								
			} catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}

		}
	}
	
	private String upFirstChar(String toUp){
		return toUp.substring(0, 1).toUpperCase() + toUp.substring(1);
	}
	
	public List<String> getFieldsContent(){
		List<String> content = new ArrayList<String>();
		for (JTextField t : textFields){
			content.add(t.getText());
		}
		
		return content;
	}
	
	
	
}
