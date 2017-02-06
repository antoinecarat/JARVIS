package platform;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import platform.plugins.IPlugin;

public class Platform {

	public static void main(String[] args) {
		
		try {
			
			IPlugin ext = (IPlugin) getBaseExtension();
			ext.run();
			
		} catch (InstantiationException | ClassNotFoundException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	/* todo :
	 * getExtensions(Class<?>). gives all the extensions matching with class
	 * use Plugin class as a template
	 * 
	 * 
	 */
	
	public static List<String> getExtensions(Class<?> target){
		List<String> extensions = new ArrayList<String>();
		
		return extensions;
	}
	
	public static Object getBaseExtension() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		//Load config.json
		String baseClassName = extractFromJSON("base", "config.json");
		Class<?> cl = Class.forName(baseClassName);
		return cl.newInstance();
		
		//Load base config
		/*String[] tmp = baseClassName.replace('.', '/').split("/");
		String baseConfig = "src/";
		for (int i=0; i<tmp.length-1; ++i){
			baseConfig += tmp[i] + "/";
		}
		baseConfig += "config.json";
		
		Collection<String> dependencies = extractArrayFromJSON("dependencies", baseConfig);
		//String baseClassConfigFile = extractFromJSON("base", "configFile", "config.json");
		//String baseClassName = extractFromJSON("extensions", "class", baseClassConfigFile);
		
		//Load base extension*/
		
	}
	
	public static Object getExtension(Class<?> targetClass) throws ClassNotFoundException, InstantiationException, IllegalAccessException {		
		return targetClass.newInstance();
	}

	private static Collection<String> extractArrayFromJSON(String prop, String fileName) {
		try {
			String json = new String();
			String line = null;
			try {
				
				BufferedReader reader = new BufferedReader(new FileReader(fileName));
				
			    while ((line = reader.readLine()) != null) {
			    	json += line;
			    }
			    reader.close();
			    
				JSONObject obj = new JSONObject(json);
				
				JSONArray array = obj.getJSONArray(prop);
				Collection<String> items = new ArrayList<String>();
				for (int i=0; i < array.length() ; ++i){
					items.add(array.getString(i));
				}
				return items;

			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return null;
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}

	
	
	public static String extractFromJSON(String prop, String fileName){
		try {
			String json = new String();
			String line = null;
			try {
				
				BufferedReader reader = new BufferedReader(new FileReader(fileName));
				
			    while ((line = reader.readLine()) != null) {
			    	json += line;
			    }
			    reader.close();
			    
				JSONObject obj = new JSONObject(json);
				
				return obj.getString(prop);

			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return null;
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
}
