package plugins.simpleBase;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AgendaFrameListener implements ActionListener {
	private AgendaFrame frame;
	
	public AgendaFrameListener(AgendaFrame frame){
		this.frame = frame;
	}
	
	public void actionPerformed(ActionEvent e) {
		this.frame.addBranche(frame.getTextFieldValue());
	}
}