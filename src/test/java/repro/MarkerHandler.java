package repro;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * Records every message that reaches it.
 */
public final class MarkerHandler extends Handler {
  public static final MarkerHandler INSTANCE = new MarkerHandler();

  private final List<String> messages = new CopyOnWriteArrayList<>();

  private MarkerHandler() {
  }

  public List<String> messages() {
    return messages;
  }

  @Override
  public void publish(LogRecord logRecord) {
    messages.add(logRecord.getMessage());
  }

  @Override
  public void flush() {
  }

  @Override
  public void close() {
  }
}
