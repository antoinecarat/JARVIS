package client;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Event implements Comparable<Event>, IEvent {

	String name;
	Date dateStart;
	Date dateEnd;
	String type;
	String description;
	String location;	
	
	public Event(String name, Date dateStart, Date dateFin, String type, String description, String location) throws IllegalArgumentException{
		super();
		this.name = name;
		this.dateStart = dateStart;
		
		if(dateFin.compareTo(dateStart) >= 0 ){
			this.dateEnd = dateFin;
		}else{
			throw new IllegalArgumentException();
		}
		
		this.type = type;
		this.description = description;
		this.location = location;
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
	public Date getDateStart() {
		return dateStart;
	}


	@Override
	public void setDateStart(Date dateStart) throws IllegalArgumentException{
//		if(dateEnd != null){
//			if(dateStart.compareTo(dateEnd) >= 0 ){
//				this.dateStart = dateStart;
//			}else{
//				throw new IllegalArgumentException();
//			}
//		}else{
			this.dateStart = dateStart;
//		}
	}


	@Override
	public Date getDateEnd() {
		return dateEnd;
	}


	@Override
	public void setDateEnd(Date dateEnd) throws IllegalArgumentException{
		if(dateStart != null){
			if(dateEnd.compareTo(dateStart) >= 0 ){
				this.dateEnd = dateEnd;
			}else{
				throw new IllegalArgumentException();
			}
		}else{
			this.dateEnd = dateEnd;
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
	public String getLocation() {
		return location;
	}

	@Override
	public void setLocation(String lieu) {
		this.location = lieu;
	}

	@Override
	public int compareTo(Event e) {
		
		int compareDate = dateStart.compareTo(e.getDateStart());
		
		if( compareDate == 0){
			return name.compareTo(e.getName());
		}
		
		return compareDate;
	}

	@Override
	public String toString() {
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		return "(" + formatter.format(dateStart) + ") " + name + " [" + type + "]";
	}

	@Override
	public boolean equals(Object obj) {
		
		IEvent e = (IEvent) obj;
		
		return this.dateStart.equals(e.getDateStart()) && this.name.equals(e.getName());
	}


	
}
