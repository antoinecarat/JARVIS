package plugins.monitoring;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import platform.IPluginDescriptor;
import platform.Platform;
import platform.plugins.IAutorun;
import platform.plugins.IMonitoring;

public class Monitoring implements IMonitoring, IAutorun {

	public void run() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		//System.out.println(Platform.getPluginDescript());
		JFrame frame = new JFrame();
	    frame.setTitle("Monitoring");
	    frame.setSize(500, 400);
	    frame.setLocationRelativeTo(null);
	    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    
	    Object[][] data = new Object[Platform.getPluginDescript().size()][2];
	    int i=0;
	    for (IPluginDescriptor p : Platform.getPluginDescript()){
	    	data[i][0] = p.getProperties().get("name");
	    	data[i][1] = p.getState();
	    	++i;
	    }
	    
		Object[] titles = {"Name","State"};
		JTable table = new JTable(data, titles);
		
	    frame.add(table);
	    
	    frame.setVisible(true);

	}
	
}
