group = "net.minestom"
version = "1.0"

allprojects {
    version = "1.0"
}
val supportedVersions = project.properties["supportedVersions"].toString().split(",").map(String::trim)
tasks {
    var eulaCheck = false
    for (mcVersion in supportedVersions) {
        val outputLocation: String =
            (findProperty("output") ?: rootDir.resolve("MinestomData").resolve(mcVersion).absolutePath) as String
        val compileVersions = getVersionsRequiredForCompile(mcVersion)
        if (!compileVersions.contains(mcVersion)) {
            compileVersions.add(mcVersion)
        }
        val implementedVersion = compileVersions[0]

        register("generateData_$mcVersion") {
            if (!eulaCheck) {
                logger.warn("Mojang requires all source-code and mappings used to be governed by the Minecraft EULA.")
                logger.warn("Please read the Minecraft EULA located at https://account.mojang.com/documents/minecraft_eula.")
                logger.warn("In order to agree to the EULA you must create a file called eula.txt with the text 'eula=true'.")
                val eulaTxt = File("${rootProject.projectDir}/eula.txt")
                logger.warn("The file must be located at '${eulaTxt.absolutePath}'.")
                if ((eulaTxt.exists() && eulaTxt.readText(Charsets.UTF_8)
                        .equals("eula=true", true)) || project.properties["eula"].toString().toBoolean()
                ) {
                    logger.warn("")
                    logger.warn("The EULA has been accepted and signed.")
                    logger.warn("")
                } else {
                    throw GradleException("Data generation has been halted as the EULA has not been signed.")
                }
                logger.warn("It is unclear if the data from the data generator also adhere to the Minecraft EULA.")
                logger.warn("Please consult your own legal team!")
                logger.warn("All data is given independently without warranty, guarantee or liability of any kind.")
                logger.warn("The data may or may not be the intellectual property of Mojang Studios.")
                logger.warn("")
                eulaCheck = true
            }
            // Here is an example:
            // We want to run the data generator for the version 1.16.3
            // This will mean we want to run the code from the 1.16.5 generators with the 1.16.3 JAR on runtime
            // First of all we will deobf the 1.16.3 JAR and then, to COMPILE the 1.16.5 generators, deobf the 1.16.5 JAR
            // Then the 1.16.5 generators are included in the runtime of DataGenerator and using reflection they are accessed.
            // Why use reflection???
            // --> We want to allow the DataGenerator module to hit any version without having to necessarily compile every version
            // E.g. If we referenced the 1.17 generator in DataGenerator we NEED it for compile
            // However if we use reflection we can just "ignore its unavailability in the classpath"
            // This also allows the 1.17 generator to reference the 1.16.5 generator without much hassle.
            // As long as the 1.16.5 JAR is also decompiled for compiling the 1.16.5 generators.

            // TL;DR: We decompile one (or more) version for compile, and only ever one for runtime.

            // Run the DataGenerator
            dependsOn(project(":DataGenerator").tasks.getByName<JavaExec>("run_$implementedVersion") {
                doFirst {
                    args = arrayListOf(mcVersion, outputLocation)
                }
                // Compile deobfuscation plus runtime deobufscation
                for (compileVersion in compileVersions) {
                    dependsOn(project(":Deobfuscator").tasks.getByName<JavaExec>("run_deobfuscator_$compileVersion"))
                }
            })
        }
    }
}


// Returns a List of versions required to get data for the specified version.
fun getVersionsRequiredForCompile(version: String): ArrayList<String> {
    // IMPORTANT: THE FIRST RETURNED VERSION IS THE GENERATOR VERSION (IMPLEMENTED VERSION)
    // TODO: Make sure this corresponds to DataGen and the versions required.
    when (version) {
        "1.16" -> {
            return arrayListOf("1.16.5")
        }
        "1.16.1" -> {
            return arrayListOf("1.16.5")
        }
        "1.16.2" -> {
            return arrayListOf("1.16.5")
        }
        "1.16.3" -> {
            return arrayListOf("1.16.5")
        }
        "1.16.4" -> {
            return arrayListOf("1.16.5")
        }
        "1.16.5" -> {
            return arrayListOf("1.16.5")
        }
        // 1.17 (uses some 1.16.5 generators)
        "1.17-pre1" -> {
            return arrayListOf("1.17", "1.16.5")
        }
        "1.17-pre2" -> {
            return arrayListOf("1.17", "1.16.5")
        }
        "1.17-pre3" -> {
            return arrayListOf("1.17", "1.16.5")
        }
        "1.17-pre4" -> {
            return arrayListOf("1.17", "1.16.5")
        }
        "1.17-pre5" -> {
            return arrayListOf("1.17", "1.16.5")
        }
        "1.17-rc1" -> {
            return arrayListOf("1.17", "1.16.5")
        }
        "1.17-rc2" -> {
            return arrayListOf("1.17", "1.16.5")
        }
        "1.17" -> {
            return arrayListOf("1.17", "1.16.5")
        }
        "1.17.1-pre1" -> {
            return arrayListOf("1.17", "1.16.5")
        }
        // Attempt with 1.16.5
        else -> {
            return arrayListOf("1.16.5")
        }
    }

}

