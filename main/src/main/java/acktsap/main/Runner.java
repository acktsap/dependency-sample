package acktsap.main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import acktsap.api.SomeInterface;

public class Runner {
	public static class CustomClassLoader extends ClassLoader {
		private final String classPath;

		public CustomClassLoader(String classPath) {
			this.classPath = classPath;
		}

		@Override
		protected Class<?> findClass(String name) throws ClassNotFoundException {
			System.out.printf("[CustomClassLoader] finding class: %s%n", name);
			byte[] classBytes = loadClassFromFile(name);
			return defineClass(name, classBytes, 0, classBytes.length);
		}

		private byte[] loadClassFromFile(String className) throws ClassNotFoundException {
			try {
				String filePath = classPath + File.separator + className.replace('.', File.separatorChar) + ".class";
				System.out.println("path: " + filePath);
				Path path = Paths.get(filePath);
				System.out.println(path.toAbsolutePath());
				return Files.readAllBytes(path);
			} catch (IOException e) {
				throw new ClassNotFoundException("Could not load class " + className, e);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		// select class dir (1 or 2)
		// make sure that all subprojects are build
		String classDir = "module1/out/production/classes";
		// String classDir = "module2/out/production/classes";

		CustomClassLoader customClassLoader = new CustomClassLoader(classDir);
		System.out.printf("main classLoader before: %s%n", Thread.currentThread().getContextClassLoader());

		// Set the custom class loader as the context class loader
		Thread.currentThread().setContextClassLoader(customClassLoader);
		System.out.printf("main classLoader after: %s%n", Thread.currentThread().getContextClassLoader());

		// Load SomeClass using the custom class loader
		// Class<?> clazz = customClassLoader.loadClass("acktsap.classloader.SomeClass");
		Class<?> clazz = Thread.currentThread().getContextClassLoader().loadClass("acktsap.module.Module");

		// Create an instance of SomeClass using reflection
		Object obj = clazz.getDeclaredConstructor().newInstance();

		// Cast the object to SomeClass and invoke the hello method
		SomeInterface someInterface = (SomeInterface)obj;
		someInterface.hell();
	}
}
