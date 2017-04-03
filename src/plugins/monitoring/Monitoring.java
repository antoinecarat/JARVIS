package plugins.monitoring;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JTable;

import platform.IPluginDescriptor;
import platform.Platform;
import platform.PluginDescriptor;
import platform.PluginState;
import platform.plugins.IAutorun;

public class Monitoring implements IAutorun, Observer {

	private JFrame frame;
	private JTable table;

	public void run() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		//System.out.println(Platform.getPluginDescript());
		frame = new JFrame();
	    frame.setTitle("Monitoring");
	    frame.setSize(300, 400);
		frame.setLocation(900, 100);
	    //frame.setLocationRelativeTo(null);
	    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    
	    Object[][] data = new Object[Platform.getPluginDescript().size()][3];
//	    int i=0;
//	    for (IPluginDescriptor p : Platform.getPluginDescript()){
//	    	
////	    	((PluginDescriptor)p).setObserver(this);
//	    	
//	    	
//	    	data[i][0] = p.getProperties().get("name");
//	    	data[i][1] = p.getState();
//	    	++i;
//	    }
	    
	    List<IPluginDescriptor> l = Platform.getPluginDescript();
	    
	    for(int i=0; i<l.size(); ++i){
	    	data[i][0] = l.get(i).getProperties().get("name");
	    	data[i][1] = l.get(i).getState();
	    	data[i][2] = l.get(i).getInstances().size();
	    	
	    	((PluginDescriptor)Platform.getPluginDescript().get(i)).setObserver(this);
	    }
	    
		Object[] titles = {"Name", "State", "# Instances"};
		this.table = new JTable(data, titles);
		
	    frame.add(table);
	    
	    frame.setVisible(true);

	}

	@Override
	public void update(Observable o, Object arg) {
		Object[][] data = new Object[Platform.getPluginDescript().size()][3];
	    int i=0;
	    
	    for (IPluginDescriptor p : Platform.getPluginDescript()){
	    	if (p.getProperties().get("name")
	    			.equals(((IPluginDescriptor) o).getProperties().get("name"))){
	    		data[i][0] = p.getProperties().get("name");
		    	data[i][1] = (PluginState) arg;
		    	data[i][2] = p.getInstances().size();
		    	
	    	} else {
	    		data[i][0] = p.getProperties().get("name");
		    	data[i][1] = p.getState();
		    	data[i][2] = p.getInstances().size();
	    	}	    	
	    	++i;
	    }
	    
	    Object[] titles = {"Name", "State", "# Instances"};
		this.frame.remove(this.table);
		this.table = new JTable(data, titles);
		this.frame.add(this.table);
		this.frame.revalidate();
		this.frame.repaint();
	}
	
}
