package cs3500.animator.model.symbol.symbol.shapes;

import cs3500.animator.model.symbol.symbol.KeyframeList;

/**
 * Represents a Rectangular shape.
 */
public class Rectangle extends KeyframeList {

  @Override
  public String getType() {
    return "rectangle";
  }

  @Override
  public Rectangle clone() {
    Rectangle copy = new Rectangle();
    this.copy(copy);
    return copy;
  }
}
