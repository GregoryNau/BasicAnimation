package cs3500.animator.model.animator;

import java.awt.Dimension;
import java.awt.Point;

/**
 * Implementation of the canvas class.
 * Stores position and dimension data.
 */
public class CanvasImpl implements Canvas {

  private final Point p;
  private final Dimension d;

  /**
   * Constructor for the ConvasImpl class.
   * Stores position and dimension data.
   * @param p the position.
   * @param d the dimension.
   */
  public CanvasImpl(Point p, Dimension d) {
    this.p = p;
    this.d = d;
  }

  /**
   * Constructor for the ConvasImpl class.
   * @param x the x position of the canvas.
   * @param y the y position of the canvas.
   * @param width the width of the canvas.
   * @param height the height of the canvas.
   */
  public CanvasImpl(int x, int y, int width, int height) {
    this.p = new Point(x, y);
    this.d = new Dimension(width, height);
  }


  @Override
  public Point getPoint() {
    return p;
  }

  @Override
  public Dimension getDimension() {
    return d;
  }

  @Override
  public String toString() {
    return "canvas " + String.format("%d %d %d %d", p.x, p.y, d.width, d.height);
  }
}
