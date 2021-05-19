package cs3500.animator.model.animator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import cs3500.animator.model.symbol.SymbolType;
import org.junit.Test;

/**
 * Tests {@link BasicAnimator}.
 */
public class BasicAnimatorTest {

  @Test(expected = IllegalArgumentException.class)
  public void betterAnimatorNullSymbol() {
    Animator ba = new BasicAnimator();
    ba.createSymbol("Hello World", null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void betterAnimatorNullName() {
    Animator ba = new BasicAnimator();
    ba.createSymbol(null,  SymbolType.RECTANGLE);
  }

  @Test
  public void betterAnimatorNullSymbolName() {
    Animator ba = new BasicAnimator();
    ba.createSymbol("R", SymbolType.RECTANGLE);
    String name = "R";
    Point p = new Point(1, 1);
    Dimension d = new Dimension(1, 2);
    Color c = new Color(1, 2, 3);

    try {
      ba.lockKeyframe(null, 1, p, d, c);
      fail();
    } catch (IllegalArgumentException e) {
      //Do Nothing
    }

    try {
      ba.lockKeyframe(name, 2, null, d, c);
      fail();
    } catch (IllegalArgumentException e) {
      //Do Nothing
    }

    try {
      ba.lockKeyframe(name, 2, p, null, c);
      fail();
    } catch (IllegalArgumentException e) {
      //Do Nothing
    }

    try {
      ba.lockKeyframe(name, 2, p, d, null);
      fail();
    } catch (IllegalArgumentException e) {
      //Do Nothing
    }
  }

  @Test(expected = IllegalStateException.class)
  public void noSymbolsToDescribe() {
    Animator ba = new BasicAnimator();
    ba.motionDescription();
  }

  // Test Not printing motion if there are less than 2 key frames
  @Test()
  public void testMotionDescriptionTwoKeyframes() {
    Animator ba = new BasicAnimator();
    ba.createSymbol("R", SymbolType.RECTANGLE);
    String expStr = "shape R rectangle";
    assertEquals(expStr, ba.motionDescription());
    ba.lockKeyframe("R",
        1,
        new Point(1, 1),
        new Dimension(1, 1),
        new Color(1, 1, 1));
    assertEquals(expStr, ba.motionDescription());

    expStr = "shape R rectangle\nshape C ellipse";

    ba.createSymbol("C", SymbolType.ELLIPSE);
    assertEquals(expStr, ba.motionDescription());
  }

  //Cannot Overwrite mapped value
  @Test(expected = IllegalArgumentException.class)
  public void testOverwritingSymbolError() {
    Animator ba = new BasicAnimator();
    ba.createSymbol("R", SymbolType.RECTANGLE);
    ba.createSymbol("R", SymbolType.ELLIPSE);
  }

  //Cannot Overwrite mapped value
  @Test
  public void testOverwritingKeyframe() {
    Animator ba = new BasicAnimator();
    ba.createSymbol("R", SymbolType.RECTANGLE);
    Point p = new Point(1, 1);
    Dimension d = new Dimension(1, 2);
    Color c = new Color(1, 2, 3);
    Color c2 = new Color(1, 2, 4);
    ba.lockKeyframe("R", 1, p, d, c);
    ba.lockKeyframe("R", 2, p, d, c);

    String expStr = "shape R rectangle\nmotion R 1 1 1 1 2 1 2 3    2 1 1 1 2 1 2 3";
    assertEquals(expStr, ba.motionDescription());

    ba.lockKeyframe("R", 2, p, d, c2);

    expStr = "shape R rectangle\nmotion R 1 1 1 1 2 1 2 3    2 1 1 1 2 1 2 4";
    assertEquals(expStr, ba.motionDescription());
  }

  @Test
  public void testHW5Case() {
    String expStr = "shape R rectangle\n"
        + "motion R 1 200 200 50 100 255 0 0    10 200 200 50 100 255 0 0\n"
        + "motion R 10 200 200 50 100 255 0 0    50 300 300 50 100 255 0 0\n"
        + "motion R 50 300 300 50 100 255 0 0    51 300 300 50 100 255 0 0\n"
        + "motion R 51 300 300 50 100 255 0 0    70 300 300 25 100 255 0 0\n"
        + "motion R 70 300 300 25 100 255 0 0    100 200 200 25 100 255 0 0\n"
        + "shape C ellipse\n"
        + "motion C 6 440 70 120 60 0 0 255    20 440 70 120 60 0 0 255\n"
        + "motion C 20 440 70 120 60 0 0 255    50 440 250 120 60 0 0 255\n"
        + "motion C 50 440 250 120 60 0 0 255    70 440 370 120 60 0 170 85\n"
        + "motion C 70 440 370 120 60 0 170 85    80 440 370 120 60 0 255 0\n"
        + "motion C 80 440 370 120 60 0 255 0    100 440 370 120 60 0 255 0";

    Animator ba = new BasicAnimator();
    ba.createSymbol("R", SymbolType.RECTANGLE);
    ba.createSymbol("C", SymbolType.ELLIPSE);

    //motion R 1 200 200 50 100 255 0 0
    ba.lockKeyframe("R",
        1,
        new Point(200, 200),
        new Dimension(50, 100),
        new Color(255, 0, 0));

    //motion R 10 200 200 50 100 255 0 0
    ba.lockKeyframe("R",
        10,
        new Point(200, 200),
        new Dimension(50, 100),
        new Color(255, 0, 0));

    //motion R 50 300 300 50 100 255 0 0
    ba.lockKeyframe("R",
        50,
        new Point(300, 300),
        new Dimension(50, 100),
        new Color(255, 0, 0));

    //motion R 51 300 300 50 100 255 0 0
    ba.lockKeyframe("R",
        51,
        new Point(300, 300),
        new Dimension(50, 100),
        new Color(255, 0, 0));

    //motion R 70 300 300 25 100 255 0 0
    ba.lockKeyframe("R",
        70,
        new Point(300, 300),
        new Dimension(25, 100),
        new Color(255, 0, 0));

    //motion R 100 200 200 25 100 255 0 0
    ba.lockKeyframe("R",
        100,
        new Point(200, 200),
        new Dimension(25, 100),
        new Color(255, 0, 0));

    //motion C 6 440 70 120 60 0 0 255
    ba.lockKeyframe("C",
        6,
        new Point(440, 70),
        new Dimension(120, 60),
        new Color(0, 0, 255));

    //motion C 20 440 70 120 60 0 0 255
    ba.lockKeyframe("C",
        20,
        new Point(440, 70),
        new Dimension(120, 60),
        new Color(0, 0, 255));

    //motion C 50 440 250 120 60 0 0 255
    ba.lockKeyframe("C",
        50,
        new Point(440, 250),
        new Dimension(120, 60),
        new Color(0, 0, 255));

    //motion C 70 440 370 120 60 0 170 85
    ba.lockKeyframe("C",
        70,
        new Point(440, 370),
        new Dimension(120, 60),
        new Color(0, 170, 85));

    //motion C 80 440 370 120 60 0 255 0
    ba.lockKeyframe("C",
        80,
        new Point(440, 370),
        new Dimension(120, 60),
        new Color(0, 255, 0));

    // motion C 100 440 370 120 60 0 255 0
    ba.lockKeyframe("C",
        100,
        new Point(440, 370),
        new Dimension(120, 60),
        new Color(0, 255, 0));

    assertEquals(expStr, ba.motionDescription());

    //Test Printing Twice
    assertEquals(expStr, ba.motionDescription());
  }

  //TODO: Test Copying map
}