package plugins.monitoring;

import java.awt.GridLayout;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import platform.IAutorun;
import platform.IPlugin;
import platform.IPluginDescriptor;
import platform.Platform;

public class Monitoring extends Thread implements IAutorun, IPlugin {

	private JFrame frame;
	private JTable plugins;
	private JTextArea logs;
	private JScrollPane logsPane;
	private JScrollPane pluginsPane;
	private JButton closeButton;
	private JButton exitButton;

	@Override
	public void run() {
		
		frame = new JFrame();
	    frame.setTitle("Monitoring");
	    frame.setSize(400, 500);
		frame.setLocation(850, 100);
		frame.setLayout(new GridLayout(4, 1, 0, 5));
	    frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	    
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
		this.closeButton = new JButton("Close Monitor");
		this.closeButton.addActionListener(new CloseListener(this, frame));
		this.exitButton = new JButton("Exit Platform");
		this.exitButton.addActionListener(new ExitListener(this, frame));
	    frame.add(pluginsPane);
	    frame.add(logsPane);
	    frame.add(closeButton);
	    frame.add(exitButton);
	    
	    frame.setVisible(true);
	    Platform.subscribeEvent("plugin", this);
	}

	@Override
	public void handleEvent(String event, Object args) {
		
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
			this.frame.remove(pluginsPane);
		}
		this.pluginsPane = new JScrollPane(plugins);
		this.frame.add(this.pluginsPane);
		
		if (this.logsPane != null) {
			this.frame.remove(this.logsPane);
		}
		
		if (args != null){
			this.logs.append(args.toString() + " " + event.split(Pattern.quote("."))[1] + "\n");
		}
		
		this.frame.add(this.logsPane);
		
		this.frame.remove(this.closeButton);
		this.frame.add(this.closeButton);
		this.frame.remove(this.exitButton);
		this.frame.add(this.exitButton);
		this.frame.revalidate();
		this.frame.repaint();
	}
}
