package cs3500.animator.model.symbol.frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.Objects;

/**
 * Represents a standard frame: a snapshot of a motion.
 */
public class Frame {

  private static final int fieldNum = 3;
  protected final Point point;
  protected final Dimension dimension;
  protected final Color color;

  /**
   * Creates a {@link Frame} using given {@link Point}, {@link Dimension}, {@link Color}.
   * @param point the position.
   * @param dimension the size.
   * @param color the color.
   */
  public Frame(Point point, Dimension dimension, Color color) {
    this.point = (Point)point.clone();
    this.dimension = (Dimension)dimension.clone();
    this.color = color;
  }

  /**
   * Creates a {@link Frame} using given params.
   * <ul>
   *   <li>x, y coordinates</li>
   *   <li>width, height for size</li>
   *   <li>red, green, blue for color</li>
   * </ul>
   * @param x the x coordinate.
   * @param y the y coordinate.
   * @param w the width.
   * @param h the height.
   * @param r the red component of a rgb value.
   * @param g the green component of a rgb value.
   * @param b the blue component of a rgb value.
   */
  public Frame(int x, int y, int w, int h, int r, int g, int b) {
    this.point = new Point(x, y);
    this.dimension = new Dimension(w, h);
    this.color = new Color(r, g, b);
  }

  /**
   * Returns the position of this {@link Frame}.
   * @return the position of this Frame.
   */
  public Point point() {
    return (Point)this.point.clone();
  }

  /**
   * Returns the size of this {@link Frame}.
   * @return the size of this Frame.
   */
  public Dimension dimension() {
    return (Dimension)this.dimension.clone();
  }

  /**
   * Returns the color of this {@link Frame}.
   * @return the color of this Frame.
   */
  public Color color() {
    return this.color;
  }

  /**
   * Determines if a given Object is equal to this one.
   * <p>They are only equals to another Object of Keyframe that represents
   * the same state of a Symbol.</p>
   * @param o the other Object.
   * @return True if equals. False if not equals.
   */
  @Override
  public boolean equals(Object o) {
    if (o instanceof Frame) {
      Frame other = (Frame) o;
      return this.point.equals(other.point)
          && this.dimension.equals(other.dimension)
          && this.color.equals(other.color);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.point, this.color, this.dimension);
  }


  /**
   * A convenience method for any subclasses to easily hash their new fields
   * without rewriting the superclasses hashCode method.
   * @param args the new fields in a subclass.
   * @return the hash value of this.
   */
  protected int hashCode(Object ... args) {
    Object[] o = new Object[fieldNum + args.length];
    o[0] = this.point;
    o[1] = this.dimension;
    o[2] = this.color;
    for (int i = 1; i < args.length; i++) {
      o[fieldNum + i - 1] = args[i];
    }
    return Objects.hash(o);
  }

}
