package acktsap.main;

import acktsap.module.ClassBasedModule;

public class ClassBased {
	public static void main(String[] args) throws Exception {
		// see also: ${PROJECT_HOME}/main/build.gradle.kts
		// note that you have to sync gradle after changing the dependency in that file
		ClassBasedModule classBasedModule = new ClassBasedModule();
		classBasedModule.hello();

		// note that there is no extra1, extra2 method in ClassBasedModule of 'concrete' project
		// ClassBasedModule of 'concrete' project just works as interface for ClassBasedModule class
		// in concrete-module1, concrete-module2
	}
}
