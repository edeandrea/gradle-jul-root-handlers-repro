package repro;

import java.util.logging.Handler;

import org.jboss.logmanager.LogContextInitializer;

/**
 * Installs {@link MarkerHandler} on the root logger when JBoss LogManager creates the log context.
 * This mirrors what Quarkus does with its {@code InitialConfigurator}.
 */
public final class MarkerInitializer implements LogContextInitializer {
  @Override
  public Handler[] getInitialHandlers(String loggerName) {
    return loggerName.isEmpty() ? new Handler[] { MarkerHandler.INSTANCE } : NO_HANDLERS;
  }
}
