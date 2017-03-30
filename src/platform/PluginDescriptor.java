package platform;

import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import client.PluginState;

public class PluginDescriptor extends Observable implements IPluginDescriptor {
	
	private Map<String, String> properties;
	
	private PluginState state;
	
	public PluginDescriptor(Map<String, String> prop) {
		this.properties = prop;
		this.state = PluginState.AVAILABLE;
	}
	
	public Map<String, String> getProperties() {
		return properties;
	}
	
	public void addProperty(String key, String value){
		properties.put(key, value);
	}
	
	public void removeProperty(String key){
		properties.remove(key);
	}
	
	public PluginState getState() {
		return state;
	}

	public void setState(PluginState state) {
		this.state = state;
		
		this.setChanged();
		this.notifyObservers(state);
	}

	public String toString() {
		return "PluginDescriptor [properties=" + properties + ", state=" + state + "]";
	}

	public void setObserver(Observer o){
		this.addObserver(o);
	}
	
	
	//TODO : singleton
	
}
