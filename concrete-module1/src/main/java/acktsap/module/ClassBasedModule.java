package acktsap.module;

public class ClassBasedModule {
	public void hello() {
		ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
		System.out.printf("[%s] classLoader: %s%n", this, contextClassLoader);
		System.out.println("I'm in class based module1");
	}

	public void extra1() {
		System.out.println("extra1");
	}
}
