package acktsap.module;

import acktsap.api.SomeInterface;

public class Module implements SomeInterface {
	@Override
	public void hell() {
		ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
		System.out.printf("[%s] classLoader: %s%n", this, contextClassLoader);
		System.out.println("I'm in module2");
	}
}
