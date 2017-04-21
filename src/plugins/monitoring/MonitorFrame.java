package plugins.monitoring;

import java.awt.GridLayout;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import platform.IPlugin;
import platform.IPluginDescriptor;
import platform.Platform;

/**
 * Defines a {@link JFrame} for the monitoring plugin.
 */
public class MonitorFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private IPlugin monitoringPlugin;
	
	private JTable plugins;
	private JTextArea logs;
	private JScrollPane logsPane;
	private JScrollPane pluginsPane;
	private JButton exitButton;

	
	public MonitorFrame(IPlugin plugin){
		this.monitoringPlugin = plugin;
		
		this.setTitle("Monitoring");
	    this.setSize(400, 600);
		this.setLocation(850, 100);
		this.setLayout(new GridLayout(3, 1, 0, 5));
	    this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	    
	    Object[][] data = new Object[Platform.getPluginDescript().size()][3];
	    
	    List<IPluginDescriptor> l = Platform.getPluginDescript();
	    
	    for(int i=0; i<l.size(); ++i){
	    	data[i][0] = l.get(i).getProperties().get("name");
	    	data[i][1] = l.get(i).getState();
	    	data[i][2] = l.get(i).getInstances().size();
	    }
	    
		Object[] titles = {"Name", "State", "# Instances"};

		this.plugins = new JTable(data, titles){
	        private static final long serialVersionUID = 1L;

	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
		};
		
		this.logs = new JTextArea();
		
		this.pluginsPane = new JScrollPane(plugins);
		this.logsPane = new JScrollPane(logs);
		this.exitButton = new JButton("Exit Platform");
		this.exitButton.addActionListener(new ExitListener(monitoringPlugin, this));
	    this.add(pluginsPane);
	    this.add(logsPane);
	    this.add(exitButton);
	    
	    this.setVisible(true);
		
	}

	public IPlugin getPlugin() {
		return monitoringPlugin;
	}

	public void refresh(String event, Object args) {
		Object[][] data = new Object[Platform.getPluginDescript().size()][3];
	    int i=0;
	    
	    for (IPluginDescriptor p : Platform.getPluginDescript()){
	    	data[i][0] = p.getProperties().get("name");
		    data[i][1] = p.getState();
		    data[i][2] = p.getInstances().size();   	
	    	++i;
	    }
	    
	    Object[] titles = {"Name", "State", "# Instances"};
		
		this.plugins = new JTable(data, titles){
	        private static final long serialVersionUID = 1L;

	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
		};
		if (this.pluginsPane != null){
			this.remove(pluginsPane);
		}
		this.pluginsPane = new JScrollPane(plugins);
		this.add(this.pluginsPane);
		
		if (this.logsPane != null) {
			this.remove(this.logsPane);
		}
		
		if (args != null){
			this.logs.append(args.toString() + " " + event.split(Pattern.quote("."))[1] + "\n");
		}
		
		this.add(this.logsPane);
		
		this.remove(this.exitButton);
		this.add(this.exitButton);
		this.revalidate();
		this.repaint();
	}
	
	
	
}
