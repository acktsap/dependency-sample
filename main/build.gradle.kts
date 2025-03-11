plugins {
    id("acktsap.java-application-jdk21-conventions")
}

dependencies {
    /* InterfaceBased */
    implementation(project(":interface"))


    /* ClassBased */
    // concrete interface dependency
    compileOnly(project(":concrete"))

    // provided implementation for ConcreteBasedModule at runtime
    // choose one
    runtimeOnly(project(":concrete-module1"))
    // runtimeOnly(project(":concrete-module2"))
}
