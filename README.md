# Gradle 9.8.0 removes root JUL handlers installed by a custom LogManager

Reproducer for a Gradle 9.7.1 -> 9.8.0 regression. There is no Quarkus here, only `org.jboss.logmanager:jboss-logmanager`.

The test JVM runs with `java.util.logging.manager=org.jboss.logmanager.LogManager`, the same setting the `io.quarkus` Gradle plugin applies to every `Test` task. A `LogContextInitializer` registered through `META-INF/services` puts `MarkerHandler` on the root logger when the log context is created, which is what Quarkus does with its `InitialConfigurator`.

## Run

```shell
./gradlew test
```

- **Gradle 9.8.0 (the wrapper in this repo):** `rootHandlerInstalledByLogManagerIsStillAttached` and `logRecordsReachRootHandlerInstalledByLogManager` fail. The root logger's handlers are now `[SLF4JBridgeHandler, ConsoleHandler]` and `MarkerHandler` is gone.
- **Gradle 9.7.1:** all tests pass. To check, set `distributionUrl` in `gradle/wrapper/gradle-wrapper.properties` to `gradle-9.7.1-bin.zip`, remove the `distributionSha256Sum` line, and run `./gradlew test --rerun-tasks`.
