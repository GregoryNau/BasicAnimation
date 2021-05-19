package cs3500.animator.model.animator;

import java.awt.Dimension;
import java.awt.Point;

/**
 * An interface to position and dimension information on a canvas.
 */
public interface Canvas {

  /**
   * Returns the stored position.
   * @return the posn.
   */
  Point getPoint();

  /**
   * Returns the stored dimension.
   * @return the dimension.
   */
  Dimension getDimension();

  /**
   * Returns string of the canvas.
   * @return the string.
   */
  String toString();
}
