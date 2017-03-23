package client;
import java.util.ArrayList;
import java.util.Date;


public class Event implements Comparable<Event>, IEvent {

	String name;
	Date dateDebut;
	Date dateFin;
	String mail;
	String tel;
	Frequence frequence;
	String type;
	String description;
	String lieu;
	ArrayList<String> participants;
	
	
	public Event() {
		super();
	}
	
	public Event(String name, Date dateDebut, String mail, String tel, Frequence frequence, String type,
			String description, String lieu, ArrayList<String> participants) {
		super();
		this.name = name;
		this.dateDebut = dateDebut;
		this.dateFin = endOfDay(dateDebut);
		this.mail = mail;
		this.tel = tel;
		this.frequence = frequence;
		this.type = type;
		this.description = description;
		this.lieu = lieu;
		this.participants = participants;
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
		
		this.mail = mail;
		this.tel = tel;
		this.frequence = frequence;
		this.type = type;
		this.description = description;
		this.lieu = lieu;
		this.participants = participants;
	}

	Date endOfDay(Date date){
		
		Date end = new Date(date.getYear(), date.getMonth(), date.getDate(), 23, 59, 59);
		
		return end;
	}

	/* (non-Javadoc)
	 * @see client.IEvent#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	/* (non-Javadoc)
	 * @see client.IEvent#setName(java.lang.String)
	 */
	@Override
	public void setName(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see client.IEvent#getDateDebut()
	 */
	@Override
	public Date getDateDebut() {
		return dateDebut;
	}

	/* (non-Javadoc)
	 * @see client.IEvent#setDateDebut(java.util.Date)
	 */
	@Override
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	/* (non-Javadoc)
	 * @see client.IEvent#getDateFin()
	 */
	@Override
	public Date getDateFin() {
		return dateFin;
	}

	/* (non-Javadoc)
	 * @see client.IEvent#setDateFin(java.util.Date)
	 */
	@Override
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	/* (non-Javadoc)
	 * @see client.IEvent#getMail()
	 */
	@Override
	public String getMail() {
		return mail;
	}

	/* (non-Javadoc)
	 * @see client.IEvent#setMail(java.lang.String)
	 */
	@Override
	public void setMail(String mail) {
		this.mail = mail;
	}

	/* (non-Javadoc)
	 * @see client.IEvent#getTel()
	 */
	@Override
	public String getTel() {
		return tel;
	}

	/* (non-Javadoc)
	 * @see client.IEvent#setTel(java.lang.String)
	 */
	@Override
	public void setTel(String tel) {
		this.tel = tel;
	}

	/* (non-Javadoc)
	 * @see client.IEvent#getFrequence()
	 */
	@Override
	public Frequence getFrequence() {
		return frequence;
	}

	/* (non-Javadoc)
	 * @see client.IEvent#setFrequence(client.Frequence)
	 */
	@Override
	public void setFrequence(Frequence frequence) {
		this.frequence = frequence;
	}

	/* (non-Javadoc)
	 * @see client.IEvent#getType()
	 */
	@Override
	public String getType() {
		return type;
	}

	/* (non-Javadoc)
	 * @see client.IEvent#setType(java.lang.String)
	 */
	@Override
	public void setType(String type) {
		this.type = type;
	}

	/* (non-Javadoc)
	 * @see client.IEvent#getDescription()
	 */
	@Override
	public String getDescription() {
		return description;
	}

	/* (non-Javadoc)
	 * @see client.IEvent#setDescription(java.lang.String)
	 */
	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	/* (non-Javadoc)
	 * @see client.IEvent#getLieu()
	 */
	@Override
	public String getLieu() {
		return lieu;
	}

	/* (non-Javadoc)
	 * @see client.IEvent#setLieu(java.lang.String)
	 */
	@Override
	public void setLieu(String lieu) {
		this.lieu = lieu;
	}

	/* (non-Javadoc)
	 * @see client.IEvent#getParticipants()
	 */
	@Override
	public ArrayList<String> getParticipants() {
		return participants;
	}

	/* (non-Javadoc)
	 * @see client.IEvent#setParticipants(java.util.ArrayList)
	 */
	@Override
	public void setParticipants(ArrayList<String> participants) {
		this.participants = participants;
	}

	@Override
	public int compareTo(Event o) {
		return this.dateDebut.compareTo(o.getDateDebut()); // TO FIX, need others parameters
	}

	@Override
	public String toString() {
		return "(" + dateDebut + ") " + name + " [" + type + "]";
	}
	
}
