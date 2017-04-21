package plugins.simpleBase.simpleCreator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Defines an {@link ActionListener} that manage the closing of frame in simpleCreator plugin.
 */
public class QuitListener implements ActionListener {
	private CreationFrame frame;

	public QuitListener(CreationFrame frame) {
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.frame.dispose();
	}
}
