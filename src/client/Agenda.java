package client;
import java.util.Collection;
import java.util.TreeSet;

public class Agenda implements IAgenda{
	
	String name;
	Collection<IEvent> events;
	
	public Agenda() {
		super();
		this.events = new TreeSet<IEvent>();
	}
	
	public Agenda(String name) {
		super();
		this.name = name;
		this.events = new TreeSet<IEvent>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Collection<IEvent> getEvents() {
		return events;
	}

	public void setEvents(Collection<IEvent> events) {
		this.events = events;
	}

	public void addEvent(IEvent event) {
		this.events.add(event);
	}


}
