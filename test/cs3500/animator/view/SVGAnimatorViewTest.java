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
 * Class for testing the SVGAnimatorViewTest.
 */
public class SVGAnimatorViewTest {

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
  public void printRectTest() {
    Animator model = new BasicAnimator();
    model.setCanvas(0,0, 3, 4);
    model.createSymbol("R", SymbolType.RECTANGLE);
    model.lockKeyframe("R", 1,
        new Point(2, 3),
        new Dimension(4, 5),
        new Color(6, 7, 8));
    model.lockKeyframe("R", 21,
        new Point(22, 23),
        new Dimension(24, 25),
        new Color(26, 27, 28));
    model.setSpeed(1);

    expectedOutput.append("<svg width=\"3\" height=\"4\" version=\"1.1\" "
        + "xmlns=\"http://www.w3.org/2000/svg\">\n"
        + "<rect id=\"R\" x=\"2\" y=\"3\" width=\"4\" height=\"5\" fill=\"rgb(6,7,8)\" "
        + "visibility=\"visible\" >\n"
        + "    <animate attributeType=\"xml\" begin=\"1000ms\" dur=\"20000ms\" "
        + "attributeName=\"x\" from=\"2\" to=\"22\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"1000ms\" dur=\"20000ms\" "
        + "attributeName=\"y\" from=\"3\" to=\"23\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"1000ms\" dur=\"20000ms\" "
        + "attributeName=\"width\" from=\"4\" to=\"24\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"1000ms\" dur=\"20000ms\" "
        + "attributeName=\"height\" from=\"5\" to=\"25\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"1000ms\" dur=\"20000ms\" "
        + "attributeName=\"fill\" from=\"rgb(6,7,8)\" to=\"rgb(26,27,28)\" fill=\"freeze\" />\n"
        + "</rect>\n"
        + "</svg>");

    SimpleController c = new SimpleController(model, new SVGAnimatorView(actualOutput));
    c.startController();
  }

  /**
   * Tests that the TextAnimatorView prints the text correctly.
   * The only new addition is the Canvas Left Corner point x dimension and y dimension.
   * The Motion description is more thoroughly tested in BasicAnimatorTest
   */
  @Test
  public void printEllipseTest() {
    Animator model = new BasicAnimator();
    model.setCanvas(0,0, 3, 4);
    model.createSymbol("E", SymbolType.ELLIPSE);
    model.lockKeyframe("E", 1,
        new Point(2, 3),
        new Dimension(4, 5),
        new Color(6, 7, 8));
    model.lockKeyframe("E", 21,
        new Point(22, 23),
        new Dimension(24, 25),
        new Color(26, 27, 28));
    model.lockKeyframe("E", 22,
        new Point(32, 33),
        new Dimension(34, 35),
        new Color(36, 37, 38));
    model.setSpeed(2);

    expectedOutput.append("<svg width=\"3\" height=\"4\" version=\"1.1\" "
        + "xmlns=\"http://www.w3.org/2000/svg\">\n"
        + "<ellipse id=\"E\" cx=\"2\" cy=\"3\" rx=\"4\" ry=\"5\" fill=\"rgb(6,7,8)\" "
        + "visibility=\"visible\" >\n"
        + "    <animate attributeType=\"xml\" begin=\"500ms\" dur=\"10000ms\" "
        + "attributeName=\"cx\" from=\"2\" to=\"22\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"500ms\" dur=\"10000ms\" "
        + "attributeName=\"cy\" from=\"3\" to=\"23\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"500ms\" dur=\"10000ms\" "
        + "attributeName=\"rx\" from=\"4\" to=\"24\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"500ms\" dur=\"10000ms\" "
        + "attributeName=\"ry\" from=\"5\" to=\"25\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"500ms\" dur=\"10000ms\" "
        + "attributeName=\"fill\" from=\"rgb(6,7,8)\" to=\"rgb(26,27,28)\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"10500ms\" dur=\"500ms\" "
        + "attributeName=\"cx\" from=\"22\" to=\"32\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"10500ms\" dur=\"500ms\" "
        + "attributeName=\"cy\" from=\"23\" to=\"33\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"10500ms\" dur=\"500ms\" "
        + "attributeName=\"rx\" from=\"24\" to=\"34\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"10500ms\" dur=\"500ms\" "
        + "attributeName=\"ry\" from=\"25\" to=\"35\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"10500ms\" dur=\"500ms\" "
        + "attributeName=\"fill\" from=\"rgb(26,27,28)\" to=\"rgb(36,37,38)\" fill=\"freeze\" />\n"
        + "</ellipse>\n"
        + "</svg>");

    SimpleController c = new SimpleController(model, new SVGAnimatorView(actualOutput));
    c.startController();
  }

  /**
   * Tests that the TextAnimatorView prints the text correctly.
   * The only new addition is the Canvas Left Corner point x dimension and y dimension.
   * The Motion description is more thoroughly tested in BasicAnimatorTest
   */
  @Test
  public void bothTogether() {
    Animator model = new BasicAnimator();
    model.setCanvas(0,0, 3, 4);
    model.createSymbol("R", SymbolType.RECTANGLE);
    model.lockKeyframe("R", 1,
        new Point(2, 3),
        new Dimension(4, 5),
        new Color(6, 7, 8));
    model.lockKeyframe("R", 42,
        new Point(22, 23),
        new Dimension(24, 25),
        new Color(26, 27, 28));
    model.createSymbol("E", SymbolType.ELLIPSE);
    model.lockKeyframe("E", 1,
        new Point(2, 3),
        new Dimension(4, 5),
        new Color(6, 7, 8));
    model.lockKeyframe("E", 21,
        new Point(22, 23),
        new Dimension(24, 25),
        new Color(26, 27, 28));
    model.setSpeed(2);

    expectedOutput.append("<svg width=\"3\" height=\"4\" version=\"1.1\" "
        + "xmlns=\"http://www.w3.org/2000/svg\">\n"
        + "<rect id=\"R\" x=\"2\" y=\"3\" width=\"4\" height=\"5\" fill=\"rgb(6,7,8)\" "
        + "visibility=\"visible\" >\n"
        + "    <animate attributeType=\"xml\" begin=\"500ms\" dur=\"20500ms\" "
        + "attributeName=\"x\" from=\"2\" to=\"22\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"500ms\" dur=\"20500ms\" "
        + "attributeName=\"y\" from=\"3\" to=\"23\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"500ms\" dur=\"20500ms\" "
        + "attributeName=\"width\" from=\"4\" to=\"24\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"500ms\" dur=\"20500ms\" "
        + "attributeName=\"height\" from=\"5\" to=\"25\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"500ms\" dur=\"20500ms\" "
        + "attributeName=\"fill\" from=\"rgb(6,7,8)\" to=\"rgb(26,27,28)\" fill=\"freeze\" />\n"
        + "</rect>\n"
        + "<ellipse id=\"E\" cx=\"2\" cy=\"3\" rx=\"4\" ry=\"5\" fill=\"rgb(6,7,8)\" "
        + "visibility=\"visible\" >\n"
        + "    <animate attributeType=\"xml\" begin=\"500ms\" dur=\"10000ms\" "
        + "attributeName=\"cx\" from=\"2\" to=\"22\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"500ms\" dur=\"10000ms\" "
        + "attributeName=\"cy\" from=\"3\" to=\"23\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"500ms\" dur=\"10000ms\" "
        + "attributeName=\"rx\" from=\"4\" to=\"24\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"500ms\" dur=\"10000ms\" "
        + "attributeName=\"ry\" from=\"5\" to=\"25\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"500ms\" dur=\"10000ms\" "
        + "attributeName=\"fill\" from=\"rgb(6,7,8)\" to=\"rgb(26,27,28)\" fill=\"freeze\" />\n"
        + "</ellipse>\n"
        + "</svg>");

    SimpleController c = new SimpleController(model, new SVGAnimatorView(actualOutput));
    c.startController();
  }

  // Animation without x
  @Test
  public void animationWithoutX() {
    Animator model = new BasicAnimator();
    model.setCanvas(0,0, 3, 4);
    model.createSymbol("R", SymbolType.RECTANGLE);
    model.lockKeyframe("R", 1,
        new Point(2, 3),
        new Dimension(4, 5),
        new Color(6, 7, 8));
    model.lockKeyframe("R", 21,
        new Point(2, 23),
        new Dimension(24, 25),
        new Color(26, 27, 28));
    model.setSpeed(1);

    expectedOutput.append("<svg width=\"3\" height=\"4\" version=\"1.1\" "
        + "xmlns=\"http://www.w3.org/2000/svg\">\n"
        + "<rect id=\"R\" x=\"2\" y=\"3\" width=\"4\" height=\"5\" fill=\"rgb(6,7,8)\" "
        + "visibility=\"visible\" >\n"
        + "    <animate attributeType=\"xml\" begin=\"1000ms\" dur=\"20000ms\" "
        + "attributeName=\"y\" from=\"3\" to=\"23\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"1000ms\" dur=\"20000ms\" "
        + "attributeName=\"width\" from=\"4\" to=\"24\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"1000ms\" dur=\"20000ms\" "
        + "attributeName=\"height\" from=\"5\" to=\"25\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"1000ms\" dur=\"20000ms\" "
        + "attributeName=\"fill\" from=\"rgb(6,7,8)\" to=\"rgb(26,27,28)\" fill=\"freeze\" />\n"
        + "</rect>\n"
        + "</svg>");

    SimpleController c = new SimpleController(model, new SVGAnimatorView(actualOutput));
    c.startController();
  }

  //Animation without y
  @Test
  public void animationWithoutY() {
    Animator model = new BasicAnimator();
    model.setCanvas(0,0, 3, 4);
    model.createSymbol("R", SymbolType.RECTANGLE);
    model.lockKeyframe("R", 1,
        new Point(2, 3),
        new Dimension(4, 5),
        new Color(6, 7, 8));
    model.lockKeyframe("R", 21,
        new Point(22, 3),
        new Dimension(24, 25),
        new Color(26, 27, 28));
    model.setSpeed(1);

    expectedOutput.append("<svg width=\"3\" height=\"4\" version=\"1.1\" "
        + "xmlns=\"http://www.w3.org/2000/svg\">\n"
        + "<rect id=\"R\" x=\"2\" y=\"3\" width=\"4\" height=\"5\" fill=\"rgb(6,7,8)\" "
        + "visibility=\"visible\" >\n"
        + "    <animate attributeType=\"xml\" begin=\"1000ms\" dur=\"20000ms\" "
        + "attributeName=\"x\" from=\"2\" to=\"22\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"1000ms\" dur=\"20000ms\" "
        + "attributeName=\"width\" from=\"4\" to=\"24\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"1000ms\" dur=\"20000ms\" "
        + "attributeName=\"height\" from=\"5\" to=\"25\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"1000ms\" dur=\"20000ms\" "
        + "attributeName=\"fill\" from=\"rgb(6,7,8)\" to=\"rgb(26,27,28)\" fill=\"freeze\" />\n"
        + "</rect>\n"
        + "</svg>");

    SimpleController c = new SimpleController(model, new SVGAnimatorView(actualOutput));
    c.startController();
  }

  //Animation without width
  @Test
  public void animationWithoutWidth() {
    Animator model = new BasicAnimator();
    model.setCanvas(0,0, 3, 4);
    model.createSymbol("R", SymbolType.RECTANGLE);
    model.lockKeyframe("R", 1,
        new Point(2, 3),
        new Dimension(4, 5),
        new Color(6, 7, 8));
    model.lockKeyframe("R", 21,
        new Point(22, 23),
        new Dimension(4, 25),
        new Color(26, 27, 28));
    model.setSpeed(1);

    expectedOutput.append("<svg width=\"3\" height=\"4\" version=\"1.1\" "
        + "xmlns=\"http://www.w3.org/2000/svg\">\n"
        + "<rect id=\"R\" x=\"2\" y=\"3\" width=\"4\" height=\"5\" fill=\"rgb(6,7,8)\" "
        + "visibility=\"visible\" >\n"
        + "    <animate attributeType=\"xml\" begin=\"1000ms\" dur=\"20000ms\" "
        + "attributeName=\"x\" from=\"2\" to=\"22\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"1000ms\" dur=\"20000ms\" "
        + "attributeName=\"y\" from=\"3\" to=\"23\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"1000ms\" dur=\"20000ms\" "
        + "attributeName=\"height\" from=\"5\" to=\"25\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"1000ms\" dur=\"20000ms\" "
        + "attributeName=\"fill\" from=\"rgb(6,7,8)\" to=\"rgb(26,27,28)\" fill=\"freeze\" />\n"
        + "</rect>\n"
        + "</svg>");

    SimpleController c = new SimpleController(model, new SVGAnimatorView(actualOutput));
    c.startController();
  }

  //Animation without height
  @Test
  public void animationWithoutHeight() {
    Animator model = new BasicAnimator();
    model.setCanvas(0,0, 3, 4);
    model.createSymbol("R", SymbolType.RECTANGLE);
    model.lockKeyframe("R", 1,
        new Point(2, 3),
        new Dimension(4, 5),
        new Color(6, 7, 8));
    model.lockKeyframe("R", 21,
        new Point(22, 23),
        new Dimension(24, 5),
        new Color(26, 27, 28));
    model.setSpeed(1);

    expectedOutput.append("<svg width=\"3\" height=\"4\" version=\"1.1\" "
        + "xmlns=\"http://www.w3.org/2000/svg\">\n"
        + "<rect id=\"R\" x=\"2\" y=\"3\" width=\"4\" height=\"5\" fill=\"rgb(6,7,8)\" "
        + "visibility=\"visible\" >\n"
        + "    <animate attributeType=\"xml\" begin=\"1000ms\" dur=\"20000ms\" "
        + "attributeName=\"x\" from=\"2\" to=\"22\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"1000ms\" dur=\"20000ms\" "
        + "attributeName=\"y\" from=\"3\" to=\"23\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"1000ms\" dur=\"20000ms\" "
        + "attributeName=\"width\" from=\"4\" to=\"24\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"1000ms\" dur=\"20000ms\" "
        + "attributeName=\"fill\" from=\"rgb(6,7,8)\" to=\"rgb(26,27,28)\" fill=\"freeze\" />\n"
        + "</rect>\n"
        + "</svg>");

    SimpleController c = new SimpleController(model, new SVGAnimatorView(actualOutput));
    c.startController();
  }

  //Animation without color
  @Test
  public void animationWithoutColor() {
    Animator model = new BasicAnimator();
    model.setCanvas(0,0, 3, 4);
    model.createSymbol("R", SymbolType.RECTANGLE);
    model.lockKeyframe("R", 1,
        new Point(2, 3),
        new Dimension(4, 5),
        new Color(6, 7, 8));
    model.lockKeyframe("R", 21,
        new Point(22, 23),
        new Dimension(24, 25),
        new Color(6, 7, 8));
    model.setSpeed(1);

    expectedOutput.append("<svg width=\"3\" height=\"4\" version=\"1.1\" "
        + "xmlns=\"http://www.w3.org/2000/svg\">\n"
        + "<rect id=\"R\" x=\"2\" y=\"3\" width=\"4\" height=\"5\" fill=\"rgb(6,7,8)\" "
        + "visibility=\"visible\" >\n"
        + "    <animate attributeType=\"xml\" begin=\"1000ms\" dur=\"20000ms\" "
        + "attributeName=\"x\" from=\"2\" to=\"22\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"1000ms\" dur=\"20000ms\" "
        + "attributeName=\"y\" from=\"3\" to=\"23\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"1000ms\" dur=\"20000ms\" "
        + "attributeName=\"width\" from=\"4\" to=\"24\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"1000ms\" dur=\"20000ms\" "
        + "attributeName=\"height\" from=\"5\" to=\"25\" fill=\"freeze\" />\n"
        + "</rect>\n"
        + "</svg>");

    SimpleController c = new SimpleController(model, new SVGAnimatorView(actualOutput));
    c.startController();
  }

  //Shape without animation
  @Test
  public void shapeVisibility() {
    Animator model = new BasicAnimator();
    model.setCanvas(0,0, 3, 4);
    model.createSymbol("R", SymbolType.RECTANGLE);
    model.lockKeyframe("R", 5,
        new Point(2, 3),
        new Dimension(4, 5),
        new Color(6, 7, 8));
    model.lockKeyframe("R", 6,
        new Point(2, 3),
        new Dimension(4, 5),
        new Color(6, 7, 8));
    model.setSpeed(1);

    expectedOutput.append("<svg width=\"3\" height=\"4\" version=\"1.1\" "
        + "xmlns=\"http://www.w3.org/2000/svg\">\n"
        + "<rect id=\"R\" x=\"2\" y=\"3\" width=\"4\" height=\"5\" fill=\"rgb(6,7,8)\" "
        + "visibility=\"hidden\" >\n"
        + "    <set attributeType=\"xml\" begin=\"5000ms\" dur=\"0ms\" "
        + "attributeName=\"visibility\" to=\"visible\" fill=\"freeze\" />\n"
        + "</rect>\n"
        + "</svg>");

    SimpleController c = new SimpleController(model, new SVGAnimatorView(actualOutput));
    c.startController();
  }

  //Shape without keyframes
  @Test
  public void shapeWithoutKeyframes() {
    Animator model = new BasicAnimator();
    model.setCanvas(0,0, 3, 4);
    model.createSymbol("R", SymbolType.RECTANGLE);
    model.setSpeed(1);

    expectedOutput.append("<svg width=\"3\" height=\"4\" version=\"1.1\" "
        + "xmlns=\"http://www.w3.org/2000/svg\">\n"
        + "</svg>");

    SimpleController c = new SimpleController(model, new SVGAnimatorView(actualOutput));
    c.startController();
  }

  @Test (expected = IllegalStateException.class)
  public void animationWithoutShapes() {
    Animator model = new BasicAnimator();
    model.setCanvas(1,2, 3, 4);
    SimpleController c = new SimpleController(model, new SVGAnimatorView(actualOutput));
    c.startController();
    model.setSpeed(1);
  }

  // Null appendable
  @Test (expected = IllegalArgumentException.class)
  public void nullAppendable() {
    new SVGAnimatorView(null);
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

    SimpleController c = new SimpleController(model, new SVGAnimatorView(actualOutput));
    c.startController();
    model.setSpeed(1);
  }

  @Test
  public void printRectTestWithOffset() {
    Animator model = new BasicAnimator();
    model.setCanvas(-100,-200, 3, 4);
    model.createSymbol("R", SymbolType.RECTANGLE);
    model.lockKeyframe("R", 1,
        new Point(2, 3),
        new Dimension(4, 5),
        new Color(6, 7, 8));
    model.lockKeyframe("R", 21,
        new Point(22, 23),
        new Dimension(24, 25),
        new Color(26, 27, 28));
    model.setSpeed(1);

    expectedOutput.append("<svg width=\"3\" height=\"4\" version=\"1.1\" "
        + "xmlns=\"http://www.w3.org/2000/svg\">\n"
        + "<rect id=\"R\" x=\"102\" y=\"203\" width=\"4\" height=\"5\" fill=\"rgb(6,7,8)\" "
        + "visibility=\"visible\" >\n"
        + "    <animate attributeType=\"xml\" begin=\"1000ms\" dur=\"20000ms\" "
        + "attributeName=\"x\" from=\"102\" to=\"122\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"1000ms\" dur=\"20000ms\" "
        + "attributeName=\"y\" from=\"203\" to=\"223\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"1000ms\" dur=\"20000ms\" "
        + "attributeName=\"width\" from=\"4\" to=\"24\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"1000ms\" dur=\"20000ms\" "
        + "attributeName=\"height\" from=\"5\" to=\"25\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"1000ms\" dur=\"20000ms\" "
        + "attributeName=\"fill\" from=\"rgb(6,7,8)\" to=\"rgb(26,27,28)\" fill=\"freeze\" />\n"
        + "</rect>\n"
        + "</svg>");

    SimpleController c = new SimpleController(model, new SVGAnimatorView(actualOutput));
    c.startController();
  }
}