package plugins.importYamlCreator;

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

import client.Event;
import client.IAgenda;
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
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
