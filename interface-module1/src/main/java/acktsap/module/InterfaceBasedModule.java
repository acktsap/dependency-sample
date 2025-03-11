package acktsap.module;

import acktsap.api.SomeInterface;

public class InterfaceBasedModule implements SomeInterface {
	@Override
	public void hell() {
		ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
		System.out.printf("[%s] classLoader: %s%n", this, contextClassLoader);
		System.out.println("I'm in interface based module1");
	}
}
