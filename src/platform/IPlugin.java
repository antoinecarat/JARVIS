package platform;

import java.util.List;

public interface IPlugin {

	void handleEvent(String event, Object args);
	
}
