package tools.equality;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import tools.rules.ExpectJunitFail;

/**
 * Tests {@link EqualityTools}.
 */
@RunWith(HierarchicalContextRunner.class) // outside dependency.
public class EqualityToolsTest {

  /**
   * An {@link Object} that equals any {@link ObjectA}.
   */
  private class ObjectA extends Object {

    @Override
    public boolean equals(Object o) {
      return o instanceof ObjectA;
    }

    @Override
    public int hashCode() {
      return 1;
    }
  }

  /**
   * An {@link Object} that equals any {@link ObjectB}.
   */
  private class ObjectB extends Object {

    @Override
    public boolean equals(Object o) {
      return o instanceof ObjectB;
    }

    @Override
    public int hashCode() {
      return 2;
    }
  }

  /**
   * An {@link Object} that is equal to everything but this.
   * <p>Will never return the same hashCode also.</p>
   */
  private class ObjectR extends Object {

    /**
     * A counter of hashCode calls.
     */
    private int hash;

    /**
     * Constructs a {@link ObjectR} with starting hash count of 0.
     */
    private ObjectR() {
      this.hash = 0;
    }

    @Override
    public boolean equals(Object o) {
      return this != o;
    }

    @Override
    public int hashCode() {
      this.hash++;
      return hash;
    }
  }

  @Test
  public void testPass() {
    EqualityTools.testValEquals(EqualityTools.makeMap(
        new ObjectA(), new ObjectA(), new ObjectB(), new ObjectB()));
    EqualityTools.testRefEquals(EqualityTools.makeMap(
        new Object(), new Object(), new Object(), new Object()));
  }

  /**
   * All tests that need to fail in {@link EqualityTools}.
   * <p>Verifies that they fail in the right places.</p>
   */
  @SuppressWarnings("unused") // does getFrame used with HierarchicalContextRunner
  public class EqualityToolsFail {

    @Rule
    public final ExpectJunitFail expectToFail = new ExpectJunitFail();
    // self-made rule which turns fails into passes and passes into fails.

    @Test
    public void testFail() {
      EqualityTools.testValEquals(EqualityTools.makeMap(
          new ObjectA(), new ObjectB(), new ObjectB(), new ObjectA()));
      Object a = new ObjectR();
      EqualityTools.testRefEquals(EqualityTools.makeMap(
          a, a, a, a));
    }
  }
}