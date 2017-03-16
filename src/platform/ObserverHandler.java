package platform;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Observer;

import platform.plugins.IMonitoring;

public class ObserverHandler implements InvocationHandler{
	
	private Object target;
	private Observer monitor;
	
	public ObserverHandler(Object target) {
		this.target = target;
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

		if(method.getName().equals("setObserver")){
			monitor = (Observer) args[0];
		}
		
		if(method.getName().equals("setState")){
			System.out.println("Target has been modified");
			
		}

		return method.invoke(target, args);
	}

}
