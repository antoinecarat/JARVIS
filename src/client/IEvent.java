package client;

import java.util.Date;

public interface IEvent {

	public abstract String getName();

	public abstract void setName(String name);

	public abstract Date getDate();

	public abstract void setDate(Date date);

	public abstract String toString();

}