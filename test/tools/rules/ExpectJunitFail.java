package tools.rules;

import junit.framework.AssertionFailedError;
import org.junit.internal.runners.statements.Fail;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * Rule that will fail if a {@link org.junit.Assert} did not fail.
 */
public class ExpectJunitFail implements TestRule {

  /**
   * {@link Statement} that will always pass.
   */
  private static class FreePass extends Statement {
    @Override
    public void evaluate() throws Throwable {
      // do nothing
    }
  }

  @Override
  public Statement apply(Statement statement, Description description) {
    try {
      // attempts to execute the statement
      statement.evaluate();
      // statement passed. Immediately fail.
      return new Fail(new AssertionFailedError("Statement passed: " + statement.toString()));
    } catch (AssertionError e) { // catch statement failure
      // Immediately pass.
      return new FreePass();
    } catch (Throwable e) {
      // Let JUnit handle whatever the fuck just happened.
      return statement;
    }
  }
}
