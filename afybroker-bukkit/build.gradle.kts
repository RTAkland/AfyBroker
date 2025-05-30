plugins {
    id("com.github.johnrengelman.shadow")
}

repositories {
    maven("https://repo.tabooproject.org/repository/releases/") {
        isAllowInsecureProtocol = true
    }
}

dependencies {
    compileOnly("ink.ptms.core:v12004:12004:mapped")
    compileOnly("org.jetbrains:annotations:20.1.0")
    compileOnly("commons-lang:commons-lang:2.6")
    implementation(project(":afybroker-client")) {
        exclude(group = "io.netty", module = "netty-all")
        exclude(group = "com.google.guava", module = "guava")
        exclude(group = "org.jetbrains", module = "annotations")
    }
}

tasks.assemble {
    dependsOn(tasks.shadowJar)
}

tasks.processResources {
    val props = mapOf(
        "version" to project.version
    )
    inputs.properties(props)
    filesMatching("plugin.yml") {
        expand(props)
    }
}
