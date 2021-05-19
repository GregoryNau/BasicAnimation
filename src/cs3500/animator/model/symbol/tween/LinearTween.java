package cs3500.animator.model.symbol.tween;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import cs3500.animator.model.symbol.frame.Frame;

/**
 * Represents a motion which has a constant rate of change.
 */
public class LinearTween implements Tween {

  /**
   * Pairs a {@link Property} value with a {@link Function}.
   * <p>The Property is the meaning behind the Function's return value.</p>
   */
  private final Map<Property, Function> map;

  /**
   * Represents the different data properties to be kept track of in this motion.
   */
  private enum Property {
    X, Y, W, H, R, G, B
  }

  /**
   * Represents a partially calculated {@link LinearTween}.
   */
  private static class Function {

    /**
     * A constant value to be added to. (y = ax + b): this is b.
     */
    private final float constant;

    /**
     * A scalar value to be multiplied to (y = ax + b): this is a.
     */
    private final float scalar;

    /**
     * Constructs a {@link Function} with given values.
     * @param a the value of a property on the first frame.
     * @param b the value of a property on the last frame.
     * @param ta the tick number of the first frame.
     * @param tb the tick number of the last frame.
     * @param den the difference between ta and tb.
     */
    private Function(int a, int b, int ta, int tb, float den) {
      this.constant = ((a * tb) - (b * ta)) / den;
      this.scalar = (b - a) / den;
    }

    /**
     * Returns the value of a property at given t.
     * @param t the frame variable. (y = ax + b): this is x.
     * @return the value of the new frame. (y = ax + b): this is y.
     */
    private int tween(int t) {
      return Math.round(this.constant + (this.scalar * t));
    }
  }

  /**
   * Constructs a {@link LinearTween} with given values.
   * @param begin the first {@link Frame} in a motion.
   * @param bframe the tick value of the first Frame.
   * @param end the last Frame in a motion.
   * @param eframe the tick value of the last Frame.
   */
  public LinearTween(Frame begin, int bframe, Frame end, int eframe) {
    this.map = new HashMap<>();
    float dif = eframe - bframe;
    Point pBegin = begin.point();
    Point pEnd = end.point();
    this.map.put(Property.X, new Function(pBegin.x, pEnd.x, bframe, eframe, dif));
    this.map.put(Property.Y, new Function(pBegin.y, pEnd.y, bframe, eframe, dif));
    Dimension dBegin = begin.dimension();
    Dimension dEnd = end.dimension();
    this.map.put(Property.W, new Function(dBegin.width, dEnd.width, bframe, eframe, dif));
    this.map.put(Property.H, new Function(dBegin.height, dEnd.height, bframe, eframe, dif));
    Color cBegin = begin.color();
    Color cEnd = end.color();
    this.map.put(Property.R, new Function(cBegin.getRed(), cEnd.getRed(), bframe, eframe, dif));
    this.map.put(Property.G, new Function(cBegin.getGreen(), cEnd.getGreen(), bframe, eframe, dif));
    this.map.put(Property.B, new Function(cBegin.getBlue(), cEnd.getBlue(), bframe, eframe, dif));
  }

  @Override
  public Frame getFrame(int frame) {
    return new Frame(this.point(frame), this.dimension(frame), this.color(frame));
  }

  /**
   * Returns the {@link Point} in this motion at a given frame.
   * @param frame the frame number or tick.
   * @return the Point the shape in this motion is at.
   */
  private Point point(int frame) {
    return new Point(this.map.get(Property.X).tween(frame),
        this.map.get(Property.Y).tween(frame));
  }

  /**
   * Returns the {@link Dimension} in this motion at a given frame.
   * @param frame the frame number or tick.
   * @return the Dimension the shape in this motion is.
   */
  private Dimension dimension(int frame) {
    return new Dimension(this.map.get(Property.W).tween(frame),
        this.map.get(Property.H).tween(frame));
  }

  /**
   * Returns the {@link Color} in this motion at a given frame.
   * @param frame the frame number or tick.
   * @return the Color the shape in this motion is.
   */
  private Color color(int frame) {
    return new Color(this.map.get(Property.R).tween(frame),
        this.map.get(Property.G).tween(frame),
        this.map.get(Property.B).tween(frame));
  }
}
