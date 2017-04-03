package plugins.simpleCreator;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import client.Event;
import plugins.simpleBase.AgendaFrame;

public class CreationFrame extends JFrame{

	JLabel[] labels;
	JTextField[] textFields;
	
	public CreationFrame(AgendaFrame a) {
		
		this.setTitle("Agenda JARVIS");
		this.setSize(400, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		
		Field[] fields = Event.class.getDeclaredFields();
		this.setLayout(new GridLayout(fields.length, 2));
		
		labels = new JLabel[fields.length];
		for (int i = 0; i < fields.length; ++i){
			labels[i] = new JLabel(fields[i].getName());
		}
		
		textFields = new JTextField[labels.length];
		for(int i=0; i < labels.length; ++i){
			this.add(labels[i]);
			textFields[i] = new JTextField();
			this.add(textFields[i]);
		}

		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new QuitListener(this));
		this.add(cancel);
		JButton createButton = new JButton("Create new Event");
		createButton.addActionListener(new CreateListener(this, a));
		this.add(createButton);
	
	}
	
	public List<String> getFieldsContent(){
		List<String> content = new ArrayList<String>();
		for (JTextField t : textFields){
			if(t.getText().length()>0){
				content.add(t.getText());
			}
		}
		
		return content;
		
	}
	
	public void clearFields() {
		for (JTextField t : textFields){
			if(t.getText().length()>0){
				t.setText("");
			}
		}
	}
	
	public JLabel[] getLabels() {
		return this.labels;
	}

}
