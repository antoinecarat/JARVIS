package plugins.simpleModifier;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Closes the frame.
 *
 */
public class QuitListener implements ActionListener {

	
	
	private ModifierEventFrame frame;

	public QuitListener(ModifierEventFrame frame) {
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.frame.dispose();
	}

}
