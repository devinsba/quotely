plugins {
    id("application")
    id("java")
}

group = "com.briandevins"
version = "1.0-SNAPSHOT"

application {
    applicationName = "quotely"
    mainClass = "com.briandevins.quotely.Main"
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}