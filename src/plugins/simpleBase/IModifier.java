package plugins.simpleBase;

/**
 * Plugin that is of type "Modifier". 
 * Plugin to modify a event.
 */
public interface IModifier {

	/**
	 * Changes (modify, delete...) a event of the agenda 
	 * @param frame the application JPanel for refreshing 
	 * @param a the agenda to which the event is modify
	 * @param e the event modifies
	 */
	void modify(IAgenda a, IEvent e);
	
}