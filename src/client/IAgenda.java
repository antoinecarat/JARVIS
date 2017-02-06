package client;

import java.util.Collection;

public interface IAgenda {

	/**
	 * @return the name
	 */
	public abstract String getName();

	/**
	 * @param name the name to set
	 */
	public abstract void setName(String name);

	/**
	 * @return the events
	 */
	public abstract Collection<IEvent> getEvents();

	/**
	 * @param events the events to set
	 */
	public abstract void setEvents(Collection<IEvent> events);

	public abstract void addEvent(IEvent event);

}