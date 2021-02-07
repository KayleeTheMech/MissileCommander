package gui;

import com.google.common.base.Stopwatch;
import java.util.concurrent.TimeUnit;

class DelayedInfoString {

  private final String string;
  private final int millis;

  private final Stopwatch sw;

  DelayedInfoString(String text, int millis) {
    this.millis = millis;
    this.string = text;
    sw = Stopwatch.createStarted();
  }

  boolean isActive() {
    return sw.elapsed(TimeUnit.MILLISECONDS) < millis;
  }

  String getString() {
    return string;
  }
}
