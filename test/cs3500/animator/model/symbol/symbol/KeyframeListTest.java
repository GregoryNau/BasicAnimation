package cs3500.animator.model.symbol.symbol;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import cs3500.animator.model.symbol.frame.RotatableFrame;
import cs3500.animator.model.symbol.symbol.shapes.Rectangle;
import org.junit.Before;
import org.junit.Test;
import tools.equality.EqualityTools;

/**
 * Tests {@link KeyframeList}.
 */
public class KeyframeListTest {

  private KeyframeList defaultSymbol;
  private KeyframeList noMotion;
  private KeyframeList allMotion;
  private KeyframeList defaultSymbolMirror;
  private KeyframeList allMotionMirror;
  private KeyframeList noMotionMirror;

  /**
   * Creates {@link KeyframeList} for testing.
   */
  @Before
  public void initialize() {
    this.defaultSymbol = new Rectangle();
    this.defaultSymbolMirror = new Rectangle();

    this.noMotion = new Rectangle();
    noMotion.setColor(123, 213, 132, 200);
    noMotion.moveTo(18, 3);
    noMotion.setSize(4, 9);
    noMotion.setRotation(360);
    noMotion.lockKeyframe(3);
    this.noMotionMirror = new Rectangle();
    noMotionMirror.setColor(123, 213, 132, 100);
    noMotionMirror.moveTo(18, 3);
    noMotionMirror.setSize(4, 9);
    noMotionMirror.setRotation(360);
    noMotionMirror.lockKeyframe(3);

    this.allMotion = new Rectangle();
    allMotion.lockKeyframe(1);
    allMotion.moveTo(10, 20);
    allMotion.setColor(200, 200, 200, 0);
    allMotion.setSize(20, 30);
    allMotion.setRotation(180);
    allMotion.lockKeyframe(97);

    this.allMotionMirror = new Rectangle();
    allMotionMirror.lockKeyframe(1);
    allMotionMirror.moveTo(10, 20);
    allMotionMirror.setColor(200, 200, 200, 0);
    allMotionMirror.setSize(20, 30);
    allMotionMirror.setRotation(180);
    allMotionMirror.lockKeyframe(97);
  }

  @Test
  public void overwriteKeyframe() {
    this.allMotion.moveTo(1, 1);
    this.allMotion.setSize(1, 1);
    this.allMotion.setRotation(1);
    this.allMotion.setColor(1, 1, 1, 0);
    this.allMotion.lockKeyframe(100);
    assertEquals(new Point(1, 1), this.allMotion.getPoint());
    assertEquals(new Dimension(1, 1), this.allMotion.getSize());
    assertEquals(new Color(1, 1, 1, 0), this.allMotion.getColor());
    assertEquals(1, this.allMotion.getRotation());
    assertNotEquals(this.allMotionMirror.getPoint(), this.allMotion.getPoint());
    assertNotEquals(this.allMotionMirror.getSize(), this.allMotion.getSize());
    assertNotEquals(this.allMotionMirror.getColor(), this.allMotion.getColor());
    assertNotEquals(this.allMotionMirror.getRotation(), this.allMotion.getRotation());
  }

  @Test
  public void testUnlockKeyframe() {
    noMotion.unlockKeyframe(3);
    assertEquals(0, noMotion.keyframes.size());
    allMotion.unlockKeyframe(97);
    assertEquals(1, allMotion.keyframes.size());
    allMotion.unlockKeyframe(1);
    assertEquals(0, allMotion.keyframes.size());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testUnlockE1() {
    defaultSymbol.unlockKeyframe(5);
  }

  @Test
  public void testGetKeyFrames() {
    assertNull(defaultSymbol.getKeyFrames());
    assertArrayEquals(new int[]{3}, noMotion.getKeyFrames());
    assertArrayEquals(new int[]{1, 97}, allMotion.getKeyFrames());
  }

  @Test
  public void testGoTo() {
    allMotion.goTo(1);
    assertEquals(allMotion.makeKeyframe(), allMotion.keyframes.get(1));
    allMotion.goTo(97);
    assertEquals(allMotion.makeKeyframe(), allMotion.keyframes.get(97));
    noMotion.goTo(1);
    assertEquals(noMotion.makeKeyframe(), noMotion.keyframes.get(3));
    noMotion.goTo(5);
    assertEquals(noMotion.makeKeyframe(), noMotion.keyframes.get(3));
    allMotion.goTo(110);
    assertEquals(allMotion.makeKeyframe(), allMotion.keyframes.get(97));
    RotatableFrame temp = defaultSymbol.makeKeyframe();
    defaultSymbol.goTo(1);
    assertEquals(temp, defaultSymbol.makeKeyframe());
    defaultSymbol.goTo(99999);
    assertEquals(temp, defaultSymbol.makeKeyframe());

    allMotion.goTo(49);
    RotatableFrame mid = new RotatableFrame(new Point(5, 10),
        new Color(100, 100, 100, 128),
        new Dimension(10, 15),
        90);
    RotatableFrame key = allMotion.makeKeyframe();
    assertEquals(mid.point().x, key.point().x);
    assertEquals(mid.point().y, key.point().y);
    assertEquals(mid.point(), key.point());
    assertEquals(mid.color().getRed(), key.color().getRed());
    assertEquals(mid.color().getGreen(), key.color().getGreen());
    assertEquals(mid.color().getBlue(), key.color().getBlue());
    assertEquals(mid.color().getAlpha(), key.color().getAlpha());
    assertEquals(mid.color(), key.color());
    assertEquals(mid.dimension().width, key.dimension().width);
    assertEquals(mid.dimension().height, key.dimension().height);
    assertEquals(mid.dimension(), key.dimension());
    assertEquals(mid.rotation(), key.rotation());
    assertEquals(mid, key);

    allMotion.goTo(25);
    RotatableFrame quarter = new RotatableFrame(new Point(3, 5),
        new Color(50, 50, 50, 191),
        new Dimension(5, 8),
        45);
    key = allMotion.makeKeyframe();
    assertEquals(quarter.point().x, key.point().x);
    assertEquals(quarter.point().y, key.point().y);
    assertEquals(quarter.point(), key.point());
    assertEquals(quarter.color().getRed(), key.color().getRed());
    assertEquals(quarter.color().getGreen(), key.color().getGreen());
    assertEquals(quarter.color().getGreen(), key.color().getBlue());
    assertEquals(quarter.color().getAlpha(), key.color().getAlpha());
    assertEquals(quarter.color(), key.color());
    assertEquals(quarter.dimension().width, key.dimension().width);
    assertEquals(quarter.dimension().height, key.dimension().height);
    assertEquals(quarter.dimension(), key.dimension());
    assertEquals(quarter.rotation(), key.rotation());
    assertEquals(quarter, key);
    assertEquals(quarter, key);
  }

  @Test
  public void testSetAll() {
    Point posn = new Point(5, 6);
    Color color = new Color(23, 32, 13, 10);
    Dimension size = new Dimension(8, 10);
    int degree = 322;
    defaultSymbol.setAll(posn, size, color, degree);
    assertNotSame(posn, defaultSymbol.point);
    assertEquals(posn, defaultSymbol.point);
    assertEquals(color, defaultSymbol.color);
    assertNotSame(size, defaultSymbol.size);
    assertEquals(size, defaultSymbol.size);
    assertNotSame(degree, defaultSymbol.degree);
    assertEquals(degree, defaultSymbol.degree);
  }

  @Test
  public void testMoveTo() {
    Point test = new Point(5, 7);
    defaultSymbol.moveTo(test);
    assertNotSame(test, defaultSymbol.point);
    assertEquals(test, defaultSymbol.point);
  }

  @Test
  public void testMoveTo1() {
    defaultSymbol.moveTo(5, 7);
    assertEquals(new Point(5, 7), defaultSymbol.point);
  }

  @Test
  public void testGetPosn() {
    assertNotSame(defaultSymbol.point, defaultSymbol.getPoint());
    assertEquals(defaultSymbol.point, defaultSymbol.getPoint());
    assertNotSame(noMotion.point, noMotion.getPoint());
    assertEquals(noMotion.point, noMotion.getPoint());
    assertNotSame(allMotion.point, allMotion.getPoint());
    assertEquals(allMotion.point, allMotion.getPoint());
  }

  @Test
  public void testSetColor() {
    Color test = new Color(123, 213, 231, 70);
    defaultSymbol.setColor(test);
    assertEquals(test, defaultSymbol.color);
  }

  @Test
  public void testSetColor1() {
    defaultSymbol.setColor(255, 144, 33, 20);
    assertEquals(new Color(255, 144, 33, 20), defaultSymbol.color);
  }

  @Test
  public void testSetColor2() {
    defaultSymbol.setColor(255, 144, 33);
    assertEquals(new Color(255, 144, 33), defaultSymbol.color);
  }

  @Test
  public void testGetColor() {
    assertEquals(defaultSymbol.color, defaultSymbol.getColor());
    assertEquals(noMotion.color, noMotion.getColor());
    assertEquals(allMotion.color, allMotion.getColor());
  }

  @Test
  public void testSetSize() {
    Dimension test = new Dimension(5, 3);
    defaultSymbol.setSize(test);
    assertEquals(new Dimension(5, 3), defaultSymbol.size);
    assertNotSame(test, defaultSymbol.size);
  }

  @Test
  public void testSetSize1() {
    defaultSymbol.setSize(5, 3);
    assertEquals(new Dimension(5, 3), defaultSymbol.size);
  }

  @Test
  public void testGetSize() {
    assertNotSame(defaultSymbol.size, defaultSymbol.getSize());
    assertEquals(defaultSymbol.size, defaultSymbol.getSize());
    assertNotSame(noMotion.size, noMotion.getSize());
    assertEquals(noMotion.size, noMotion.getSize());
    assertNotSame(allMotion.size, allMotion.getSize());
    assertEquals(allMotion.size, allMotion.getSize());
  }

  @Test
  public void testSetRotation() {
    defaultSymbol.setRotation(5);
    assertEquals(5, defaultSymbol.degree);
  }

  @Test
  public void testGetRotation() {
    assertEquals(0, defaultSymbol.getRotation());
    assertEquals(360, noMotion.getRotation());
    assertEquals(180, allMotion.getRotation());
  }

  @Test
  public void testCopy() {
    KeyframeList copy = new Rectangle();
    copy = allMotion.copy(copy);

    assertTrue(copy instanceof Rectangle);

    for (int frame : allMotion.getKeyFrames()) {
      assertEquals(allMotion.keyframes.get(frame), copy.keyframes.get(frame));
    }
  }

  @Test
  public void testEquals() {
    EqualityTools.testRefEquals(
        EqualityTools.makeMap(
            defaultSymbol, defaultSymbolMirror,
            noMotion, noMotionMirror,
            allMotion, allMotionMirror));
  }
}