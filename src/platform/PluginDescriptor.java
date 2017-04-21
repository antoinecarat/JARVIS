package platform;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class PluginDescriptor implements IPluginDescriptor {
	
	private Map<String, Object> properties;
	
	private PluginState state;
	
	private List<IPlugin> instances;
	
	public PluginDescriptor(Map<String, Object> prop) {
		this.properties = prop;
		this.instances = new ArrayList<IPlugin>();
		this.state = PluginState.AVAILABLE;
	}
	
	@Override
	public Map<String, Object> getProperties() {
		return properties;
	}
	
	@Override
	public void addProperty(String key, Object value){
		properties.put(key, value);
	}
	
	@Override
	public void removeProperty(String key){
		properties.remove(key);
	}
	
	@Override
	public PluginState getState() {
		return state;
	}

	@Override
	public void setState(PluginState state) {
		this.state = state;
	}

	@Override
	public List<IPlugin> getInstances() {
		return instances;
	}

	@Override
	public void addInstance(IPlugin o) {
		instances.add(o);
	}
	
	@Override
	public void removeInstance(IPlugin o) {
		instances.remove(o);
	}
	
	@Override
	public String toString() {
		return properties.get("name") + ";";
	}
	
	
}
