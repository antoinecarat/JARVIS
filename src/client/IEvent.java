package client;

import java.util.ArrayList;
import java.util.Date;

public interface IEvent {

	String getName();

	void setName(String name);

	Date getDateDebut();

	void setDateDebut(Date dateDebut);

	Date getDateFin();

	void setDateFin(Date dateFin);

	String getMail();

	void setMail(String mail);

	String getTel();

	void setTel(String tel);

	Frequence getFrequence();

	void setFrequence(Frequence frequence);

	String getType();

	void setType(String type);

	String getDescription();

	void setDescription(String description);

	String getLieu();

	void setLieu(String lieu);

	ArrayList<String> getParticipants();

	void setParticipants(ArrayList<String> participants);
	
	String toString();
	
	int compareTo(Event e);

}