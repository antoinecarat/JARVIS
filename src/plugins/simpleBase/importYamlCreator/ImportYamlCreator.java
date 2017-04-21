package plugins.simpleBase.importYamlCreator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.yaml.snakeyaml.Yaml;

import platform.Platform;
import plugins.simpleBase.Event;
import plugins.simpleBase.IAgenda;
import plugins.simpleBase.ICreator;


/**
 * Imports the events collection and display if the import went well.
 */
@SuppressWarnings("unchecked")
public class ImportYamlCreator  implements ICreator {

	@Override
	public void create(IAgenda agenda) {
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
				
				Map<String, Object> map = (Map<String, Object>) yaml.load(input);
			    
//			    add every event in yaml file
			    for (Object value : map.values()) {
			    	Field[] fields = Event.class.getDeclaredFields();
			    	Class<?> paramTypes[] = new Class<?>[fields.length];
			    	Object args[] = new Object[fields.length];
					Constructor<Event> m;
					SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
					List<String> fileContent = new ArrayList<String>();
					for (Object s : ((LinkedHashMap)value).values()){
						fileContent.add((String) s);
					}
					for (int i = 0 ; i < fields.length ; ++i) {
						paramTypes[i] = fields[i].getType();
						if (paramTypes[i].equals(Date.class)){
							args[i] = formatter.parse(fileContent.get(i));
						} else {
							args[i] = (String) fileContent.get(i);
						}
					}
					
					Event event;
					m = Event.class.getConstructor(paramTypes);
					event = m.newInstance(args);
					agenda.addEvent(event);
				}
			    JOptionPane.showMessageDialog(null, "Import done successfully.");
			    Platform.raiseEvent("event.added", null);
			} catch (FileNotFoundException | NoSuchMethodException | SecurityException | 
					InstantiationException | IllegalAccessException| IllegalArgumentException | 
					InvocationTargetException | ParseException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void handleEvent(String event, Object args) {
		
	}

	@Override
	public void startUp() {
		
	}
}
