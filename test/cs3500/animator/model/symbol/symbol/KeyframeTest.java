package cs3500.animator.model.symbol.symbol;

import static org.junit.Assert.assertEquals;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import cs3500.animator.model.symbol.frame.RotatableFrame;
import org.junit.Before;
import org.junit.Test;
import tools.equality.EqualityTools;

/**
 * Tests {@link RotatableFrame}.
 */
public class KeyframeTest {

  private RotatableFrame a;
  private RotatableFrame b;
  private RotatableFrame aCopy;
  private RotatableFrame bCopy;

  /**
   * Initializes common {@link RotatableFrame}s before every test.
   */
  @Before
  public void initialize() {
    a = new RotatableFrame(new Point(1, 2), new Color(3, 4,
        5, 255), new Dimension(7, 8), 9);
    b = new RotatableFrame(new Point(10, 11), new Color(12, 13,
        14, 100), new Dimension(16, 17), 18);
    aCopy = new RotatableFrame(new Point(1, 2), new Color(3, 4,
        5, 255), new Dimension(7, 8), 9);
    bCopy = new RotatableFrame(new Point(10, 11), new Color(12, 13,
        14, 100), new Dimension(16, 17), 18);
  }

  @Test
  public void testPosn() {
    assertEquals(new Point(1, 2), a.point());
    assertEquals(new Point(10, 11), b.point());
  }

  @Test
  public void testColor() {
    assertEquals(new Color(3, 4, 5, 255), a.color());
    assertEquals(new Color(12, 13, 14, 100), b.color());
  }

  @Test
  public void testSize() {
    assertEquals(new Dimension(16, 17), b.dimension());
    assertEquals(new Dimension(7, 8), a.dimension());
  }

  @Test
  public void testDegree() {
    assertEquals(9, a.rotation());
    assertEquals(18, b.rotation());
  }

  @Test
  public void testEquals() {
    EqualityTools.testValEquals(
        EqualityTools.makeMap(
            a, aCopy,
            b, bCopy));
  }
}