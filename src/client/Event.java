package client;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Event implements Comparable<Event>, IEvent {

	String name;
	Date dateDebut;
	Date dateFin;
	String type;
	String description;
	String lieu;	
	
	public Event(String name, Date dateDebut, Date dateFin, String type, String description, String lieu) throws IllegalArgumentException{
		super();
		this.name = name;
		this.dateDebut = dateDebut;
		
		if(dateFin.compareTo(dateDebut) >= 0 ){
			this.dateFin = dateFin;
		}else{
			throw new IllegalArgumentException();
		}
		
		this.type = type;
		this.description = description;
		this.lieu = lieu;
	}

	public Event() {
		super();
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
	public void setDateDebut(Date dateDebut) throws IllegalArgumentException{
		if(dateFin.compareTo(dateDebut) >= 0 ){
			this.dateDebut = dateDebut;
		}else{
			throw new IllegalArgumentException();
		}	
	}


	@Override
	public Date getDateFin() {
		return dateFin;
	}


	@Override
	public void setDateFin(Date dateFin) throws IllegalArgumentException{
		if(dateFin.compareTo(dateDebut) >= 0 ){
			this.dateFin = dateFin;
		}else{
			throw new IllegalArgumentException();
		}
	}
	
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
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		return "(" + formatter.format(dateDebut) + ") " + name + " [" + type + "]";
	}

	@Override
	public boolean equals(Object obj) {
		
		IEvent e = (IEvent) obj;
		
		return this.dateDebut.equals(e.getDateDebut()) && this.name.equals(e.getName());
	}


	
}
