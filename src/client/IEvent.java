package client;

import java.util.Date;

public interface IEvent {

	String getName();

	void setName(String name);

	Date getDateStart();

	void setDateStart(Date dateStart);

	Date getDateEnd();

	void setDateEnd(Date dateEnd);

	String getType();

	void setType(String type);

	String getDescription();

	void setDescription(String description);

	String getLocation();

	void setLocation(String location);
	
	String toString();
	
	int compareTo(Event e);

	boolean equals(Object obj);
}