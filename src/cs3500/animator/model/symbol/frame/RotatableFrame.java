package cs3500.animator.model.symbol.frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

/**
 * Represents a {@link Frame} with the ability to rotate.
 * <p>Negative rotation means counter clockwise rotational movement.</p>
 * <p>rotation > 360 means multiple spins of rotational movement.</p>
 */
public class RotatableFrame extends Frame {

  /**
   * The degree value of the rotation.
   * <p>Negative means counter clockwise rotational movement.</p>
   * <p>Over 360 means multiple spins of rotational movement.</p>
   */
  protected final int rotation;

  /**
   * Constructs a {@link RotatableFrame} at given values.
   * @param point the position.
   * @param dimension the size.
   * @param color the color.
   * @param rotation the degree of rotation.
   */
  public RotatableFrame(Point point, Dimension dimension, Color color, int rotation) {
    super(point, dimension, color);
    this.rotation = rotation;
  }

  /**
   * Constructs a {@link RotatableFrame} at given values.
   * @param point the position.
   * @param color the color.
   * @param dimension the size.
   * @param rotation the degree of rotation.
   */
  public RotatableFrame(Point point, Color color, Dimension dimension, int rotation) {
    this(point, dimension, color, rotation);
  }

  /**
   * Constructs a {@link RotatableFrame} at given values.
   * @param x x coordinate.
   * @param y y coordinate.
   * @param w width.
   * @param h height.
   * @param r red component of a rgb value.
   * @param g green component of a rgb value.
   * @param b blue component of a rgb value.
   * @param d degree of rotation.
   */
  public RotatableFrame(int x, int y, int w, int h, int r, int g, int b, int d) {
    super(x, y, w, h, r, g, b);
    this.rotation = d;
  }

  /**
   * Returns the rotation of this {@link RotatableFrame}.
   * <p>Negative means counter clockwise rotational movement.</p>
   * <p>Over 360 means multiple spins of rotational movement.</p>
   * @return the rotation of this Frame.
   */
  public int rotation() {
    return this.rotation;
  }

  @Override
  public boolean equals(Object o) {
    return super.equals(o) && ((RotatableFrame)o).rotation == this.rotation;
  }

  @Override
  public int hashCode() {
    return super.hashCode(this.rotation);
  }

}
