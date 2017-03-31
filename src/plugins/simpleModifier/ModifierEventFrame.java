package plugins.simpleModifier;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

	JTextField[] textFields;
	
	IEvent event;

	Method method;
	JLabel[] labels;

	
	public ModifierEventFrame(JFrame frame, IEvent event) throws HeadlessException {
		super();
		this.event = event;
		
		this.setTitle("Modify the event");
		this.setSize(400, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		Field[] fields = Event.class.getDeclaredFields();
		this.setLayout(new GridLayout(fields.length+1, 2));

		labels = new JLabel[fields.length];
		for (int i = 0; i < fields.length; ++i){
			labels[i] = new JLabel(fields[i].getName());
		}
		
		textFields = new JTextField[labels.length];
		for(int i=0; i < labels.length; ++i){
			this.add(labels[i]);
			textFields[i] = new JTextField();
			//TODO: retrieve value of the field with reflect
			textFields[i].setText("Value");
			this.add(textFields[i]);
		}
		JButton cancel = new JButton("Annuler");
		cancel.addActionListener(new QuitListener(this));
		this.add(cancel);
		JButton accept = new JButton("Confirmer");
		accept.addActionListener(new ModifyListener(this, event));
		this.add(accept);
		
	}
	
	public List<String> getFieldsContent(){
		List<String> content = new ArrayList<String>();
		for (JTextField t : textFields){
			content.add(t.getText());
		}
		return content;
	}
	
	
	
}
