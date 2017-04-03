package client;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Event implements Comparable<Event>, IEvent {

	String name;
	Date startDate;
	Date endDate;
	String type;
	String description;
	String location;	
	
	public Event(String name, Date dateStart, Date dateFin, String type, String description, String location) throws IllegalArgumentException{
		super();
		this.name = name;
		this.startDate = dateStart;
		
		if(dateFin.compareTo(dateStart) >= 0 ){
			this.endDate = dateFin;
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
	public Date getStartDate() {
		return startDate;
	}


	@Override
	public void setStartDate(Date startDate) throws IllegalArgumentException{
		if(startDate.compareTo(endDate) >= 0 ){
			this.startDate = startDate;
		}else{
			throw new IllegalArgumentException();
		}

	}


	@Override
	public Date getEndDate() {
		return endDate;
	}


	@Override
	public void setEndDate(Date dateEnd) throws IllegalArgumentException{
		//if(startDate != null){
			if(dateEnd.compareTo(startDate) >= 0 ){
				this.endDate = dateEnd;
			}else{
				throw new IllegalArgumentException();
			}
		//}else{
		//	this.endDate = dateEnd;
		//}
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
		
		int compareDate = startDate.compareTo(e.getStartDate());
		
		if( compareDate == 0){
			return name.compareTo(e.getName());
		}
		
		return compareDate;
	}

	@Override
	public String toString() {
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		return "(" + formatter.format(startDate) + ") " + name + " [" + type + "]";
	}

	@Override
	public boolean equals(Object obj) {
		
		IEvent e = (IEvent) obj;
		
		return this.startDate.equals(e.getStartDate()) && this.name.equals(e.getName());
	}


	
}
