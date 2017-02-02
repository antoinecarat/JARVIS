package client;
import java.util.Collection;
import java.util.TreeSet;


public class Agenda {
	
	String name;
	Collection<Event> events;
	
	public Agenda() {
		super();
		this.events = new TreeSet<Event>();
	}
	
	public Agenda(String name) {
		super();
		this.name = name;
		this.events = new TreeSet<Event>();
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Collection<Event> getEvents() {
		return events;
	}
	
	public void setEvents(Collection<Event> events) {
		this.events = events;
	}
}
