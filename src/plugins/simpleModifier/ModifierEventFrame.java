package plugins.simpleModifier;

import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import client.Event;
import client.IEvent;
import plugins.simpleBase.AgendaFrame;

/**
 * Frame for modifying an event.
 */
public class ModifierEventFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	JTextField[] textFields;
	
	IEvent event;

	Method method;
	JLabel[] labels;
	
	AgendaFrame frame;

	
	public ModifierEventFrame(AgendaFrame frame, IEvent event) throws HeadlessException {
		super();
		this.frame = frame;
		this.event = event;
		
		this.setTitle("Modify the event");
		this.setSize(400, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
			try {
				Method m = Event.class.getDeclaredMethod("get"+upFirstChar(labels[i].getText()));
				if(fields[i].getType().equals(Date.class)){
					SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
					textFields[i].setText(formatter.format(m.invoke(event)));
				} else {
					textFields[i].setText(m.invoke(event).toString());
				}
			} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
			
			this.add(textFields[i]);
		}
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new QuitListener(this));
		this.add(cancel);
		JButton accept = new JButton("Confirm");
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
	
	private String upFirstChar(String toUp){
		return toUp.substring(0, 1).toUpperCase() + toUp.substring(1);
	}
	
	@Override
	public void dispose() {
		frame.refreshPrinter();
		super.dispose();
	}
	
}
