package client;
import java.util.Date;


public class Event implements Comparable<Event>, IEvent {

	String name;
	Date date;
	
	public Event() {
		super();
	}
	
	public Event(String name, Date date) {
		super();
		this.name = name;
		this.date = date;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}

	public int compareTo(Event o) {
		return this.date.compareTo(o.getDate());
	}

	public String toString() {
		return "Event [name=" + name + ", date=" + date + "]";
	}
	
	
}
