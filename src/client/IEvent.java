package client;

import java.util.Date;

public interface IEvent {

	String getName();

	void setName(String name);

	Date getDateDebut();

	void setDateDebut(Date dateDebut);

	Date getDateFin();

	void setDateFin(Date dateFin);

	String getType();

	void setType(String type);

	String getDescription();

	void setDescription(String description);

	String getLieu();

	void setLieu(String lieu);
	
	String toString();
	
	int compareTo(Event e);

	boolean equals(Object obj);
}