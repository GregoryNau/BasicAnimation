package cs3500.animator.model.symbol.symbol.shapes;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;

import cs3500.animator.model.symbol.symbol.KeyframeList;
import cs3500.animator.model.symbol.symbol.Symbol;
import org.junit.Test;

/**
 * Tests {@link Ellipse}.
 */
public class EllipseTest {

  @Test
  public void testGetType() {
    assertEquals("ellipse", new Ellipse().getType());
  }

  @Test
  public void testCopy() {
    KeyframeList e = new Ellipse();
    e.moveTo(5, 3);
    e.setColor(253, 255, 254, 74);
    e.setRotation(763);
    e.setSize(789, 345);
    e.lockKeyframe(3);
    e.moveTo(8, 2);
    e.setColor(231, 235, 222, 30);
    e.setSize(700, 300);
    e.setRotation(23);
    e.lockKeyframe(5);
    Symbol temp = e.clone();
    e.moveTo(123, 123);
    e.setColor(123, 123, 123, 12);
    e.setSize(123, 123);
    e.setRotation(123);

    // assert that temp is not null
    assertNotNull(temp);

    // assert that they are the same type of object
    assertTrue(temp instanceof Ellipse);
    KeyframeList ec = (Ellipse) temp;

    // assert that they are referencing different objects.
    assertNotSame(e, ec);

    // assert that the list of keyframes is the same.
    assertArrayEquals(e.getKeyFrames(), ec.getKeyFrames());

    // assert that the temporary values are not the same (ec didn't getFrame unlocked traits)
    assertNotEquals(e.getRotation(), ec.getRotation());
    assertNotEquals(e.getPoint(), ec.getPoint());
    assertNotEquals(e.getSize(), ec.getSize());
    assertNotEquals(e.getColor(), ec.getColor());

    // assert that each Keyframe in their list of keyframes are equal to each other.
    assertEquals(e.getKeyFrames()[0], ec.getKeyFrames()[0]);
    assertEquals(e.getKeyFrames()[1], ec.getKeyFrames()[1]);
  }
}