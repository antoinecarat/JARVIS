package plugins.simpleBase;

import java.util.Date;

/**
 * Defines a event with a name, a start and an end date, a type, a description and a location.
 */
public interface IEvent {

	/**
	 * Returns the name of the event.
	 * @return the name of the event
	 */
	String getName();

	/**
	 * Changes the name of the event.
	 * @param name the new name
	 */
	void setName(String name);

	/**
	 * Returns the start date of the event.
	 * @return the start date of the event
	 */
	Date getStartDate();

	/**
	 * Changes the start date of the event.
	 * @param startDate the new date start 
	 */
	void setStartDate(Date startDate);

	/**
	 * Returns the end date of the event.
	 * @return the end date of the event
	 */
	Date getEndDate();

	/**
	 * Changes the end date of the event.
	 * @param endDate the new end date
	 */
	void setEndDate(Date endDate);

	/**
	 * Returns the type of the event.
	 * @return the type of the event
	 */
	String getType();

	/**
	 * Changes the type of the event.
	 * @param type the new type of the event 
	 */
	void setType(String type);

	/**
	 * Returns the description of the event.
	 * @return the description of the event 
	 */
	String getDescription();

	/**
	 * Changes the description of the event.
	 * @param description the new description of the event 
	 */
	void setDescription(String description);

	/**
	 * Returns the location of the event.
	 * @return the location of the event
	 */
	String getLocation();

	/**
	 * Changes the location of the event.
	 * @param location the new location of the event
	 */
	void setLocation(String location);
	
}