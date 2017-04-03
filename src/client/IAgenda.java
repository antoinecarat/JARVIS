package client;

import java.util.Collection;

/**
 * Defines a Agenda with a bunch of events.
 */
public interface IAgenda {

	/**
	 * Returns the name of the agenda.
	 * @return the name of the agenda 
	 */
	public abstract String getName();

	/**
	 * Changes the name of the agenda.
	 * @param name the new name
	 */
	public abstract void setName(String name);

	/**
	 * Returns the collection of events of the agenda.
	 * @return a collection of events 
	 */
	public abstract Collection<IEvent> getEvents();

	/**
	 * Changes the collection of events of the agenda.
	 * @param events the new collection of events
	 */
	public abstract void setEvents(Collection<IEvent> events);

	/**
	 * Adds a event to the collection.
	 * @param event event to the list
	 */
	public abstract void addEvent(IEvent event);

}