package cs3500.animator.model.symbol.symbol.shapes;

import cs3500.animator.model.symbol.symbol.KeyframeList;
import cs3500.animator.model.symbol.symbol.Symbol;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

/**
 * Tests {@link Rectangle}.
 */
public class RectangleTest {

  private void assertNotEquals(Object a, Object b) {
    assertThat(a, is(not(b)));
  }

  @Test
  public void testType() {
    assertEquals("rectangle", new Rectangle().getType());
  }

  @Test
  public void testCopy() {
    KeyframeList r = new Rectangle();
    r.moveTo(5, 3);
    r.setColor(253, 255, 254, 255);
    r.setRotation(763);
    r.setSize(789, 345);
    r.lockKeyframe(3);
    r.moveTo(8, 2);
    r.setColor(231, 235, 222, 150);
    r.setSize(700, 300);
    r.setRotation(23);
    r.lockKeyframe(5);
    Symbol temp = r.clone();
    r.moveTo(123, 123);
    r.setColor(123, 123, 123, 70);
    r.setSize(123, 123);
    r.setRotation(123);

    // assert that temp is not null
    assertNotNull(temp);

    // assert that they are the same type of object
    assertTrue(temp instanceof Rectangle);
    KeyframeList rc = (Rectangle) temp;

    // assert that they are referencing different objects.
    assertNotSame(r, rc);

    // assert that the list of keyframes is the same.
    assertArrayEquals(r.getKeyFrames(), rc.getKeyFrames());

    // assert that the temporary values are not the same (rc didn't getFrame unlocked traits)
    assertNotEquals(r.getRotation(), rc.getRotation());
    assertNotEquals(r.getPoint(), rc.getPoint());
    assertNotEquals(r.getSize(), rc.getSize());
    assertNotEquals(r.getColor(), rc.getColor());

    // assert that each Keyframe in their list of keyframes are equal to each other.
    assertEquals(r.getKeyFrames()[0], rc.getKeyFrames()[0]);
    assertEquals(r.getKeyFrames()[1], rc.getKeyFrames()[1]);
  }
}