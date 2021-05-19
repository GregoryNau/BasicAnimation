package cs3500.animator.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;
import cs3500.animator.model.animator.Canvas;
import cs3500.animator.model.animator.ImmAnimator;
import cs3500.animator.model.symbol.symbol.Symbol;

/**
 * This Animation view generates an SVG file.
 * SVGAnimatorView is an implementation of AnimatorView.
 * Creates an svg file with rects and ellipses.
 * Sets the origin of the svg to the top left corner by shifting by the model's canvas posn.
 */
public class SVGAnimatorView implements ImmAnimatorView {

  private float tps;
  private ImmAnimator animator;
  private final Writer ap;

  /**
   * This constructor takes in a writer to make the view.
   * @param ap the writer.
   * @throws IllegalArgumentException if the params are null.
   */
  public SVGAnimatorView(Writer ap) throws IllegalArgumentException {
    if (ap == null) {
      throw ( new IllegalArgumentException("Invalid inputs, must be non null and positive"));
    }
    this.ap = ap;
  }

  @Override
  public void setImmAnimator(ImmAnimator a) {
    this.animator = a;
    this.tps = a.getSpeed();
  }

  @Override
  public void startView() {
    int yOff;
    int xOff;

    xOff = animator.getCanvas().getPoint().x;
    yOff = animator.getCanvas().getPoint().y;

    Canvas c = animator.getCanvas();
    Map<String, Symbol> m = animator.getSymbolMap();

    if (m.size() == 0) {
      throw new IllegalStateException("Does not contain anything");
    }

    append(String.format("<svg width=\"%d\" "
            + "height=\"%d\" "
            + "version=\"1.1\" "
            + "xmlns=\"http://www.w3.org/2000/svg\">\n",
        c.getDimension().width,
        c.getDimension().height));
    String name;
    String shape_tag;
    String attr_x;
    String attr_y;
    String attr_width;
    String attr_height;
    String visibility;
    Boolean setVisible;
    Symbol s;

    int entryItr = 0;
    for (Map.Entry<String, Symbol> entry : m.entrySet()) {

      name = entry.getKey();
      s = entry.getValue();

      //TODO: Change to Enum or something.
      switch (s.getType()) {
        case "rectangle":
          shape_tag = "rect";
          attr_x = "x";
          attr_y = "y";
          attr_width = "width";
          attr_height = "height";
          break;
        case "ellipse":
          shape_tag = "ellipse";
          attr_x = "cx";
          attr_y = "cy";
          attr_width = "rx";
          attr_height = "ry";
          break;
        default:
          //TODO: Test this exception
          throw( new IllegalArgumentException(
              String.format("Could not find shape %s", s.getType())));
      }

      int[] keyFrames = s.getKeyFrames();

      if (s.getKeyFrames() == null) {
        entryItr++;
        continue;
      }

      visibility = "hidden";
      setVisible = true;
      for (int keyFrame : keyFrames) {
        if (keyFrame == 1) {
          visibility = "visible";
          setVisible = false;
        }
      }



      s.goTo(s.getKeyFrames()[0]);
      append(String.format("<%s id=\"%s\" "
          + "%s=\"%d\" "
          + "%s=\"%d\" "
          + "%s=\"%d\" "
          + "%s=\"%d\" "
          + "fill=\"rgb(%d,%d,%d)\" "
          + "visibility=\"%s\" "
          + ">\n",
          shape_tag,
          name,
          attr_x,
          s.getPoint().x - xOff,
          attr_y,
          s.getPoint().y - yOff,
          attr_width,
          s.getSize().width,
          attr_height,
          s.getSize().height,
          s.getColor().getRed(),
          s.getColor().getGreen(),
          s.getColor().getBlue(),
          visibility));

      // Do not try to print motion if the number of keyframes is less than 2
      if (s.getKeyFrames().length < 2) {
        append(String.format("</%s>\n", shape_tag));
        entryItr++;
        continue;
      }

      int startKeyFrame;
      int endKeyFrame;
      int begin;
      int dur;
      Point startP;
      Point endP;
      Dimension startSize;
      Dimension endSize;
      Color startColor;
      Color endColor;

      for (int i = 0; i < s.getKeyFrames().length - 1; i++) {
        startKeyFrame = keyFrames[i];
        endKeyFrame = keyFrames[i + 1];

        s.goTo(startKeyFrame);

        begin = tickToMS(startKeyFrame);
        dur = tickToMS(endKeyFrame - startKeyFrame);

        startP = s.getPoint();
        startSize = s.getSize();
        startColor = s.getColor();

        s.goTo(endKeyFrame);

        endP = s.getPoint();
        endSize = s.getSize();
        endColor = s.getColor();

        // make the shape visible if it was originally hidden.
        if (setVisible) {
          appendAttributeVisible(begin, 0);
          setVisible = false;
        }

        // cx
        if (startP.x != endP.x ) {
          appendAttribute(attr_x, begin, dur, startP.x - xOff, endP.x - xOff);
        }

        // cy
        if (startP.y != endP.y ) {
          appendAttribute(attr_y, begin, dur, startP.y - yOff, endP.y - yOff);
        }

        // rx
        if (startSize.width != endSize.width ) {
          appendAttribute(attr_width, begin, dur, startSize.width, endSize.width);
        }

        // ry
        if (startSize.height != endSize.height ) {
          appendAttribute(attr_height, begin, dur, startSize.height, endSize.height);
        }

        // Fill
        if (!startColor.equals(endColor)) {
          appendAttributeFill(begin, dur, startColor, endColor);
        }
      }
      append(String.format("</%s>\n", shape_tag));
      entryItr++;
    }
    append("</svg>");

    try {
      ap.close();
    } catch (IOException e) {
      throw (new IllegalStateException("Could not close writer!"));
    }
  }

  /**
   * Writes to appendable while handling IO excpetion.
   * @param s the string to write.
   * @throws IllegalStateException if cannot append.
   */
  private void append(String s) throws IllegalStateException {
    try {
      ap.append(s);
    }
    catch (IOException e) {
      throw(new IllegalStateException("Could not append to appendable."));
    }
  }

  /**
   * Converts tick to milliseconds.
   * @param tick the tick to convert.
   */
  private int tickToMS(int tick) {
    return Math.round((tick / tps) * 1000);
  }

  /**
   * Appends an attribute animation.
   * @param attribute the attribute to animate.
   * @param begin the starting time in ms.
   * @param dur the duration in ms.
   * @param from the starting value.
   * @param to the ending value.
   */
  private void appendAttribute(String attribute, int begin, int dur, int from, int to) {
    append(String.format("    <animate attributeType=\"xml\" "
            + "begin=\"%dms\" "
            + "dur=\"%dms\" "
            + "attributeName=\"%s\" "
            + "from=\"%d\" "
            + "to=\"%d\" "
            + "fill=\"freeze\" />\n",
        begin,
        dur,
        attribute,
        from,
        to));
  }

  /**
   * Appends a fill or color animation.
   * @param begin the start time in ms.
   * @param dur the end time in ms.
   * @param from the start color.
   * @param to the end color.
   */
  private void appendAttributeFill(int begin, int dur, Color from, Color to) {
    append(String.format("    <animate attributeType=\"xml\" "
            + "begin=\"%dms\" "
            + "dur=\"%dms\" "
            + "attributeName=\"fill\" "
            + "from=\"rgb(%d,%d,%d)\" "
            + "to=\"rgb(%d,%d,%d)\" "
            + "fill=\"freeze\" />\n",
        begin,
        dur,
        from.getRed(), from.getGreen(), from.getBlue(),
        to.getRed(), to.getGreen(), to.getBlue()));
  }

  /**
   * Appends a visible attribute animation.
   * @param begin the start time in ms.
   * @param dur the end time in ms.
   */
  private void appendAttributeVisible(int begin, int dur) {
    append(String.format("    <set attributeType=\"xml\" "
            + "begin=\"%dms\" "
            + "dur=\"%dms\" "
            + "attributeName=\"visibility\" "
            + "to=\"visible\" "
            + "fill=\"freeze\" />\n",
        begin,
        dur));
  }
}
