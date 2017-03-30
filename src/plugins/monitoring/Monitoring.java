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
	    frame.setSize(800, 600);
	    frame.setLocationRelativeTo(null);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    Object[][] data = new Object[Platform.getPluginDescript().size()][2];
	    int i=0;
	    for (IPluginDescriptor p : Platform.getPluginDescript()){
	    	data[i][0] = p.getProperties().get("name");
	    	data[i][1] = p.getState();
	    ++i;
	    }
	    

		String[] titles = {"Name","State"}; 
		TableModel model = new AbstractTableModel() {
		    public String getColumnName(int col) {
		        return columnNames[col].toString();
		    }
		    public int getRowCount() { return rowData.length; }
		    public int getColumnCount() { return columnNames.length; }
		    public Object getValueAt(int row, int col) {
		        return rowData[row][col];
		    }
		    public boolean isCellEditable(int row, int col)
		        { return true; }
		    public void setValueAt(Object value, int row, int col) {
		        rowData[row][col] = value;
		        fireTableCellUpdated(row, col);
		    }
		}
		JTable table = new JTable(data, titles);

	    
	    DefaultListModel<String> listModel = new DefaultListModel<String>();
	    
	    
	    
	    JList list = new JList(listModel);
	    
	    frame.add(list);
	    
	    frame.setVisible(true);

	}
	
}
