package cs3500.animator.view.visual;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import cs3500.animator.model.symbol.SymbolType;

/**
 * Represents a state of a shape for this view.
 */
public interface Snapshot {

  /**
   * Returns the Posn representing the position of this shape.
   * @return the position of this shape.
   */
  Point point();

  /**
   * Returns the Dimension representing the dimension of this shape.
   * @return the dimension of this shape.
   */
  Dimension size();

  /**
   * Returns the Color representing the color of this shape.
   * @return the color of this shape.
   */
  Color color();

  /**
   * Returns the SymbolType representing the type of shape this is.
   * @return the type of shape this is.
   */
  SymbolType type();

}
