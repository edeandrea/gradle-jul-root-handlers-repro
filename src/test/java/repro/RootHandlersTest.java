package repro;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

class RootHandlersTest {
  @Test
  void usesJBossLogManager() {
    assertEquals("org.jboss.logmanager.LogManager", LogManager.getLogManager().getClass().getName());
  }

  @Test
  void rootHandlerInstalledByLogManagerIsStillAttached() {
    var rootHandlers = Logger.getLogger("").getHandlers();

    assertTrue(
        Arrays.asList(rootHandlers).contains(MarkerHandler.INSTANCE),
        () -> "MarkerHandler was removed from the root logger. Root handlers are now: " + Arrays.toString(rootHandlers));
  }

  @Test
  void logRecordsReachRootHandlerInstalledByLogManager() {
    Logger.getLogger("repro").info("hello from the test");

    assertTrue(
        MarkerHandler.INSTANCE.messages().contains("hello from the test"),
        () -> "MarkerHandler did not receive the log record. It received: " + MarkerHandler.INSTANCE.messages());
  }
}
