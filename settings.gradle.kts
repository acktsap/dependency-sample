dependencyResolutionManagement {
    // repositories to fetch dependencies
    repositories {
        mavenCentral()
        mavenLocal() // uses gradle base over maven base ($HOME/.m2)
    }
}

rootProject.name = "dependency-sample"

include(
    "main",
    "interface",
    "interface-module1",
    "interface-module2",
    "concrete",
    "concrete-module1",
    "concrete-module2"
)
