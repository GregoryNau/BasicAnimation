package cs3500.animator.model.symbol.symbol.shapes;

import cs3500.animator.model.symbol.symbol.KeyframeList;

/**
 * Represents an Elliptical shape.
 */
public class Ellipse extends KeyframeList {

  @Override
  public String getType() {
    return "ellipse";
  }

  @Override
  public Ellipse clone() {
    Ellipse copy = new Ellipse();
    this.copy(copy);
    return copy;
  }
}
