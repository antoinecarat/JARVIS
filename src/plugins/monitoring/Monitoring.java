package plugins.monitoring;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JTable;

import platform.IPlugin;
import platform.IPluginDescriptor;
import platform.Platform;
import platform.PluginDescriptor;
import platform.PluginState;
import platform.plugins.IAutorun;

public class Monitoring extends Thread implements IAutorun, IPlugin {

	private JFrame frame;
	private JTable table;

	@Override
	public void run() {
				
	    Platform.subscribeEvent("plugin.launched", this);
	    Platform.subscribeEvent("plugin.crashed", this);
	    Platform.subscribeEvent("plugin.killed", this);
	    
		frame = new JFrame();
	    frame.setTitle("Monitoring");
	    frame.setSize(300, 400);
		frame.setLocation(900, 100);

	    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    
	    Object[][] data = new Object[Platform.getPluginDescript().size()][3];
	    
	    List<IPluginDescriptor> l = Platform.getPluginDescript();
	    
	    for(int i=0; i<l.size(); ++i){
	    	data[i][0] = l.get(i).getProperties().get("name");
	    	data[i][1] = l.get(i).getState();
	    	data[i][2] = l.get(i).getInstances().size();
	    	
	    	//((PluginDescriptor)Platform.getPluginDescript().get(i)).setObserver(this);
	    }
	    
		Object[] titles = {"Name", "State", "# Instances"};

		this.table = new JTable(data, titles){
	        private static final long serialVersionUID = 1L;

	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
		};
		
	    frame.add(table);
	    
	    frame.setVisible(true);
	    
	}

	@Override
	public void handleEvent(String event) {
		String cat = event.split(Pattern.quote("."))[0];
		if (cat.equals("plugin")){
			Object[][] data = new Object[Platform.getPluginDescript().size()][3];
		    int i=0;
		    
		    for (IPluginDescriptor p : Platform.getPluginDescript()){
		    	data[i][0] = p.getProperties().get("name");
			    data[i][1] = p.getState();
			    data[i][2] = p.getInstances().size();   	
		    	++i;
		    }
		    
		    Object[] titles = {"Name", "State", "# Instances"};
			if (this.table != null) {
				this.frame.remove(this.table);
			}
			
			this.table = new JTable(data, titles){
		        private static final long serialVersionUID = 1L;
	
		        public boolean isCellEditable(int row, int column) {                
		                return false;               
		        };
			};		
			this.frame.add(this.table);
			this.frame.revalidate();
			this.frame.repaint();
		}
	}
	
}
