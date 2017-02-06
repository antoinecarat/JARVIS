package platform;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import platform.plugins.IAutorun;

public class Platform {

	private static List<String> autoruns;
	private static Map<String,List<String>> dependencies;
	
	public static void main(String[] args) {
		
		try {
			autoruns = extractArrayFromJSON("autoruns","config.json");
			System.out.println(autoruns);
			
			for (String clS : autoruns){
				Class<?> cl = Class.forName(clS);
				IAutorun auto = (IAutorun) cl.newInstance();
				//TODO: Load dependencies into dependencies
				auto.run();
			}
			
		} catch (InstantiationException | ClassNotFoundException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	public static Object getExtension(Class<?> targetClass) throws ClassNotFoundException, InstantiationException, IllegalAccessException {		
		return targetClass.newInstance();
	}

	private static List<String> extractArrayFromJSON(String prop, String fileName) {
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
				List<String> items = new ArrayList<String>();
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
	
	public static String extractStringFromJSON(String prop, String fileName){
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
