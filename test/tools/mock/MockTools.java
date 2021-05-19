package tools.mock;

import java.util.List;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public abstract class MockTools {

  /**
   * Records the method call with all int parameters into passed log.
   * Basically adds a string representation of the java syntax to call a method to log.
   * @param log the log the new method call will be passed into.
   * @param method the name of the method call.
   * @param a all the parameters in the method.
   * @return log.
   */
  @Contract("_, _, _ -> param1")
  public static <A> List<String> addToLog(List<String> log, String method, @NotNull A ... a) {
    StringBuilder str = new StringBuilder();
    str.append(method).append("(");
    if (a.length > 0) {
      for (int i = 0; i < a.length - 1; i++) {
        /*
        String.valueOf turns any primitive into a string.
        String.valueOf will call toString on any Object.
        Thus it will not break on any type <A>
         */
        str.append(String.valueOf(a[i])).append(", ");
      }
      str.append(String.valueOf(a[a.length - 1]));
    }
    str.append(");");
    log.add(str.toString());
    return log;
  }
}
