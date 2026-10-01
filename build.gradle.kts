plugins {
  java
}

repositories {
  mavenCentral()
}

dependencies {
  testImplementation("org.jboss.logmanager:jboss-logmanager:3.2.2.Final")
  testImplementation(platform("org.junit:junit-bom:5.14.3"))
  testImplementation("org.junit.jupiter:junit-jupiter")
  testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
  useJUnitPlatform()

  // Same setting the io.quarkus Gradle plugin applies to every Test task
  systemProperty("java.util.logging.manager", "org.jboss.logmanager.LogManager")

  testLogging {
    events("PASSED", "FAILED")
    exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
  }
}
