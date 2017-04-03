package plugins.importYamlCreator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.yaml.snakeyaml.Yaml;

import client.IAgenda;
import client.IEvent;
import platform.plugins.ICreator;
import plugins.simpleBase.AgendaFrame;

public class ImportYamlCreator  implements ICreator {

	@Override
	public void create(AgendaFrame a) {
		JFileChooser fc = new JFileChooser();
		fc.setMultiSelectionEnabled(false);
		fc.setFileFilter(new FileFilterYaml());
		int result = fc.showOpenDialog(null);
		
		if(result == JFileChooser.APPROVE_OPTION){
			String filename = fc.getSelectedFile().toString();

//			read yaml file
			try {
				InputStream input = new FileInputStream(new File(filename));
				Yaml yaml = new Yaml();
				
			    @SuppressWarnings("unchecked")
				Map<String, Object> map = (Map<String, Object>) yaml.load(input);

			    IAgenda agenda = a.getAgenda();
			    
//			    add every event in yaml file
			    for (Object event : map.values()) {
			    	agenda.addEvent((IEvent)event);
				}
			    
			    JOptionPane.showMessageDialog(null, "Import done successfully.");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
}
