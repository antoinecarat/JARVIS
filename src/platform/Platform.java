package platform;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

public class Platform {

	public static Object getExtension(Class<IPlugin> class1) {		
		try {
						
			//Load config.json
			String baseClassName = extractFromJSON("base", "class", "config.json");
			
			//Load base config
			String baseClassConfigFile = extractFromJSON("base", "configFile", "config.json");
			//String baseClassName = extractFromJSON("extensions", "class", baseClassConfigFile);
			
			//Load base extension
			Class<?> cl = Class.forName(baseClassName);
			IPlugin ext = (IPlugin) cl.newInstance();
			
			//Execute base extension
			ext.run();
			
		}
		catch(Exception e){
			e.printStackTrace();
		}

		return null;
	}
	
	public static String extractFromJSON(String object, String prop, String fileName){
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
				
				return obj.getJSONObject(object).getString(prop);

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
