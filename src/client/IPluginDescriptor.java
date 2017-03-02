package client;

import java.util.Map;

public interface IPluginDescriptor {

	Map<String, String> getProperties();

	void addProperty(String key, String value);

	void removeProperty(String key);

}