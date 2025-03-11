package acktsap.module;

public class ClassBasedModule {
	public void hello() {
		ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
		System.out.printf("[%s] classLoader: %s%n", this, contextClassLoader);
		System.out.println("I'm in class based module2");
	}

	public void extra2() {
		System.out.println("extra2");
	}
}
