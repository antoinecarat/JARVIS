package plugins.secondBase;

import platform.plugins.IAutorun;
import platform.plugins.IPlugin;

public class SecondBase implements IPlugin, IAutorun {

	@Override
	public void run() {
		System.out.println("pouet");
	}

}
