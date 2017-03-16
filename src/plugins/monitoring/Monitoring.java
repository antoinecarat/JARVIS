package plugins.monitoring;

import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import platform.IPluginDescriptor;
import platform.Platform;
import platform.PluginDescriptor;
import platform.plugins.IAutorun;
import platform.plugins.IMonitoring;
import platform.plugins.IPlugin;

public class Monitoring implements IPlugin, IMonitoring, IAutorun, Observer {

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

	
	public void update(Observable arg0, Object arg1) {
	    String s = "";
	    for (IPluginDescriptor p : Platform.getPluginDescript()){
	    	s += p.toString() + "\n";
	    }
	    
	    pd.setText(s);
	}
	
}
