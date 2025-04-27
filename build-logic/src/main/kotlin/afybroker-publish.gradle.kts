plugins {
    java
    `maven-publish`
}

extensions.configure<PublishingExtension> {
    repositories {
//        maven("http://repo.afyer.net/repository/maven-releases/") {
//            credentials(PasswordCredentials::class.java)
//            isAllowInsecureProtocol = true
//        }
        maven("http://192.168.10.222:9098/releases") {
            credentials {
                username = "RTAkland"
                password = System.getenv("PUBLISH_TOKEN")
            }
            isAllowInsecureProtocol = true
        }
    }
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
}
