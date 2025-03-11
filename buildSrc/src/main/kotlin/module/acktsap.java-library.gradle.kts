package module

plugins {
    // to use 'api(...)' for transitive dependencies
    // https://docs.gradle.org/current/userguide/java_library_plugin.html
    `java-library`

    // coverage
    // https://docs.gradle.org/current/userguide/jacoco_plugin.html
    jacoco

    // linting
    // https://docs.gradle.org/current/userguide/checkstyle_plugin.html
    checkstyle
}


/* java-library */

java {
    toolchain {
        // uses common toolchain
        // it automatically sets sourceCompatibility & targetCompatibility to matching version
        languageVersion.set(JavaLanguageVersion.of(21))
    }

    // as it's a library, generate source & javadoc
    withSourcesJar() // generate source jar
    withJavadocJar() // generate javadoc file
}

tasks.jar {
    // customizing manifest file
    manifest {
        attributes(
            mapOf(
                "Implementation-Title" to project.name,
                "Implementation-Version" to project.version,
            ),
        )
    }
}

tasks.withType<JavaCompile> { // both for compileJava & compileTestJava
    options.encoding = "UTF-8"
    options.compilerArgs.add("-parameters") // for parameter name injection in spring
    options.compilerArgs.add("-Xlint:deprecation") // show usage of deprecated code
}

tasks.javadoc {
    options {
        (this as CoreJavadocOptions).addStringOption("Xdoclint:none", "-quiet")
    }
}

tasks.named<Test>("test") {
    useJUnitPlatform()
    maxParallelForks = Runtime.getRuntime().availableProcessors()
}


/* jacoco */

jacoco {
    toolVersion = "0.8.12"
    reportsDirectory = layout.buildDirectory.dir("reports/jacoco")
}

tasks.jacocoTestReport {
    dependsOn(tasks.withType<Test>())

    // set execution data from all `.exec` files
    executionData.setFrom(files(project.fileTree("build/jacoco").include("*.exec")))

    reports {
        html.required = true
        xml.required = true
        csv.required = false
        html.outputLocation = layout.buildDirectory.dir("jacoco/html")
        xml.outputLocation = layout.buildDirectory.file("jacoco/result.xml")
    }

    doLast {
        val indexFile = project.layout.buildDirectory.dir("jacoco/html").get().file("index.html")
        println("jacoco html report is generated to $indexFile")
    }
}

tasks.jacocoTestCoverageVerification {
    enabled = false // disabled in subproject
}


/* checkstyle */

tasks.withType<Checkstyle>().configureEach {
    reports {
        configFile = file("${project.rootDir}/buildSrc/config/checkstyle.xml")
        configProperties =
            mapOf(
                "suppressionFile" to file("${project.rootDir}/buildSrc/config/checkstyle-suppressions.xml"),
            )
        xml.required.set(false)
        html.required.set(true)
    }
}
