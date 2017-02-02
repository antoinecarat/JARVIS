package platform;

import java.io.BufferedReader;
import java.io.FileReader;

public class Platform {

	public static Object getExtension(Class<IPlugin> class1) {		
		try {
						
			//Load config.json
			String baseClassName = ;
			//(new BufferedReader(new FileReader("config.json"))).readLine();
			
			//Load base config
					
			
			//Load base extension
			Class<?> cl = Class.forName(baseClassName);
			IPlugin ext = (IPlugin) cl.newInstance();
			
			//Execute base extension
			ext.run();
			
		}
		catch(Exception e){
			e.printStackTrace();
		}

		return null;
	}
	
}
