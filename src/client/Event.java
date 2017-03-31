package client;
import java.util.ArrayList;
import java.util.Date;


public class Event implements Comparable<Event>, IEvent {

	String name;
	Date dateDebut;
	Date dateFin;
	//String mail;
	//String tel;
	//Frequence frequence;
	String type;
	String description;
	String lieu;
	//ArrayList<String> participants;
	
	
	public Event() {
		super();
	}
	
	public Event(String name, Date dateDebut, String mail, String tel, Frequence frequence, String type,
			String description, String lieu, ArrayList<String> participants) {
		super();
		this.name = name;
		this.dateDebut = dateDebut;
		this.dateFin = endOfDay(dateDebut);
		//this.mail = mail;
		//this.tel = tel;
		//this.frequence = frequence;
		this.type = type;
		this.description = description;
		this.lieu = lieu;
		//this.participants = participants;
	}
	
	public Event(String name, Date dateDebut, Date dateFin, String mail, String tel, Frequence frequence, String type,
			String description, String lieu, ArrayList<String> participants) {
		super();
		this.name = name;
		this.dateDebut = dateDebut;
		
		if(dateFin.compareTo(dateDebut) >= 0 ){
			this.dateFin = dateFin;
		}else{
			throw new IllegalArgumentException("DateFin isn't a valide value");
		}
		
		//this.mail = mail;
		//this.tel = tel;
		//this.frequence = frequence;
		//this.type = type;
		this.description = description;
		this.lieu = lieu;
		//this.participants = participants;
	}

	Date endOfDay(Date date){
		
		Date end = new Date(date.getYear(), date.getMonth(), date.getDate(), 23, 59, 59);
		
		return end;
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
	public Date getDateDebut() {
		return dateDebut;
	}


	@Override
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}


	@Override
	public Date getDateFin() {
		return dateFin;
	}


	@Override
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}
	
	/* 
	@Override
	public String getMail() {
		return mail;
	}

	@Override
	public void setMail(String mail) {
		this.mail = mail;
	}

	
	@Override
	public String getTel() {
		return tel;
	}


	@Override
	public void setTel(String tel) {
		this.tel = tel;
	}

	
	@Override
	public Frequence getFrequence() {
		return frequence;
	}

	@Override
	public void setFrequence(Frequence frequence) {
		this.frequence = frequence;
	}
	*/
	
	@Override
	public String getType() {
		return type;
	}

	@Override
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String getLieu() {
		return lieu;
	}

	@Override
	public void setLieu(String lieu) {
		this.lieu = lieu;
	}

	/*
	@Override
	public ArrayList<String> getParticipants() {
		return participants;
	}

	@Override
	public void setParticipants(ArrayList<String> participants) {
		this.participants = participants;
	}*/

	@Override
	public int compareTo(Event e) {
		
		int compareDate = dateDebut.compareTo(e.getDateDebut());
		
		if( compareDate == 0){
			return name.compareTo(e.getName());
		}
		
		return compareDate;
	}

	@Override
	public String toString() {
		return "(" + dateDebut.getDay()+ ":" + dateDebut.getMonth() + ":" + dateDebut.getYear() + ") " + name + " [" + type + "]";
	}

	@Override
	public boolean equals(Object obj) {
		
		IEvent e = (IEvent) obj;
		
		return this.dateDebut.equals(e.getDateDebut()) && this.name.equals(e.getName());
	}


	
}
