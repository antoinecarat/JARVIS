package plugins.simpleBase.simpleModifier;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Defines an {@link ActionListener} that manage the closing of frame in simpleModifier plugin.
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
