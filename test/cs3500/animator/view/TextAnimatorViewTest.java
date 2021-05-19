package cs3500.animator.view;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import cs3500.animator.controller.SimpleController;
import cs3500.animator.model.animator.Animator;
import cs3500.animator.model.animator.BasicAnimator;
import cs3500.animator.model.symbol.SymbolType;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Class for testing the TextAnimatorView.
 */
public class TextAnimatorViewTest {

  private StringBuilder expectedOutput;
  private OutputStreamWriter actualOutput;
  private ByteArrayOutputStream ba;

  /**
   * Adds string builders for testing expected and actual outputs.
   */
  @Before
  public void beforeTest() {
    expectedOutput = new StringBuilder();
    ba = new ByteArrayOutputStream();
    actualOutput = new OutputStreamWriter(ba);
  }

  /**
   * Tests expected and actual outputs.
   */
  @After
  public void afterTest() {
    assertEquals(expectedOutput.toString(), ba.toString());
  }

  /**
   * Tests that the TextAnimatorView prints the text correctly.
   * The only new addition is the Canvas Left Corner point x dimension and y dimension.
   * The Motion description is more thoroughly tested in BasicAnimatorTest
   */
  @Test
  public void printTest() {
    Animator model = new BasicAnimator();
    model.setCanvas(1,2, 3, 4);
    model.createSymbol("R", SymbolType.RECTANGLE);
    model.lockKeyframe("R", 1,
        new Point(2, 3),
        new Dimension(4, 5),
        new Color(6, 7, 8));
    model.lockKeyframe("R", 21,
        new Point(22, 23),
        new Dimension(24, 25),
        new Color(26, 27, 28));

    expectedOutput.append("canvas 1 2 3 4\n");
    expectedOutput.append("shape R rectangle\n"
        + "motion R 1 2 3 4 5 6 7 8    21 22 23 24 25 26 27 28");

    SimpleController c = new SimpleController(model, new TextAnimatorView(actualOutput));
    c.startController();
  }

  @Test
  public void illegalArgumentTest() {
    Animator model = new BasicAnimator();
    model.createSymbol("R", SymbolType.RECTANGLE);
    model.lockKeyframe("R", 1,
        new Point(2, 3),
        new Dimension(4, 5),
        new Color(6, 7, 8));
    model.lockKeyframe("R", 21,
        new Point(22, 23),
        new Dimension(24, 25),
        new Color(26, 27, 28));


    try {
      AnimatorView av = new TextAnimatorView(null);
      fail();
    } catch (IllegalArgumentException e) {
      // Keep Going
    }
  }

  @Test
  public void testHW6Case() {
    expectedOutput.append("canvas 10 20 30 40\n");
    expectedOutput.append( "shape R rectangle\n"
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
        + "motion C 80 440 370 120 60 0 255 0    100 440 370 120 60 0 255 0");

    Animator ba = new BasicAnimator();
    ba.createSymbol("R", SymbolType.RECTANGLE);
    ba.createSymbol("C", SymbolType.ELLIPSE);
    ba.setCanvas(10, 20, 30, 40);

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

    SimpleController c = new SimpleController(ba, new TextAnimatorView(actualOutput));
    c.startController();
  }

  //Appendable Closed
  @Test (expected = IllegalStateException.class)
  public void appendableClosed() {
    Animator model = new BasicAnimator();
    model.setCanvas(1,2, 3, 4);
    model.createSymbol("R", SymbolType.RECTANGLE);
    model.lockKeyframe("R", 1,
        new Point(2, 3),
        new Dimension(4, 5),
        new Color(6, 7, 8));

    try {
      actualOutput.close();
    } catch (IOException e) {
      fail();
    }

    SimpleController c = new SimpleController(model, new TextAnimatorView(actualOutput));
    c.startController();
  }
}