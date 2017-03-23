package plugins.monitoring;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import platform.IPluginDescriptor;
import platform.Platform;
import platform.plugins.IAutorun;
import platform.plugins.IMonitoring;

public class Monitoring implements IMonitoring, IAutorun {

	private JTextArea pd;
	
	public void run() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		System.out.println(Platform.getPluginDescript());
		JFrame frame = new JFrame();
	    frame.setTitle("Monitoring");
	    frame.setSize(800, 600);
	    frame.setLocationRelativeTo(null);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   
	    pd = new JTextArea();
	    String s = "";
	    for (IPluginDescriptor p : Platform.getPluginDescript()){
	    	s += p.toString() + "\n";
	    }
	    pd.setText(s);
	    frame.add(pd);
	    
	    frame.setVisible(true);

	}
	
}
