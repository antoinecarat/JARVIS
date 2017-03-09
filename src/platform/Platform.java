package platform;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import client.IPluginDescriptor;
import client.PluginDescriptor;
import platform.plugins.IAutorun;
import platform.plugins.IPrinter;

public class Platform {

	private static List<IPluginDescriptor> pluginDescript;

	public static List<IPluginDescriptor> getPluginDescript() {
		return pluginDescript;
	}

	public static void setPluginDescript(List<IPluginDescriptor> pluginDescript) {
		Platform.pluginDescript = pluginDescript;
	}

	public static void main(String[] args) {

		loadPluginDescriptors();
		// Run autoruns
	}

	public static Object getExtension(Class<?> targetClass)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		return targetClass.newInstance();
	}

	public static List<Class<?>> getExtensions(Class<?> need) throws ClassNotFoundException {

		List<String> plugins = extractArrayFromJSON("plugins", "config.json");
		List<Class<?>> pluginsObject = new ArrayList<Class<?>>();

		for (String p : plugins) {
			Class<?> cl = Class.forName(p);

			if (need.isAssignableFrom(cl)) {
				pluginsObject.add(cl);
			}
		}

		// TODO
		// Load pluginDescriptors that match need.

		return pluginsObject;
	}

	public static void loadPluginDescriptors() {
		List<String> plugins = extractArrayFromJSON("plugins", "config.json");
		IPluginDescriptor desc;

		for (String p : plugins) {
			String[] tmp = p.split(".");
			String pluginFile = tmp[tmp.length - 1] + ".json";
			Map<String, String> prop = jsonIntoMap(pluginFile);
			desc = new PluginDescriptor(prop);
			pluginDescript.add(desc);
		}
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
				for (int i = 0; i < array.length(); ++i) {
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

	public static String extractStringFromJSON(String prop, String fileName) {
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

	public static Map<String, String> jsonIntoMap(String fileName) {
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
				Map<String, String> map = new HashMap<>();
				Object o = obj.keys().next();
				while (o != null) {
					map.put((String) o, obj.getString((String) o));
				}

				return map;

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
