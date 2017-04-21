package plugins.simpleBase;
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

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public Collection<IEvent> getEvents() {
		return events;
	}

	@Override
	public void setEvents(Collection<IEvent> events) {
		this.events = events;
	}

	@Override
	public void addEvent(IEvent event) {
		this.events.add(event);
	}


}
