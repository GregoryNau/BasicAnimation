package cs3500.animator.view.visual.components;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Represents a ellipse shape.
 * <p>{@link AbstractComponent}</p>
 */
public class EllipseComponent extends AbstractComponent {

  /**
   * Constructs a {@link EllipseComponent}.
   * <p>{@link AbstractComponent#AbstractComponent(int, int, int, int, int, int, int)}</p>
   */
  public EllipseComponent(int x, int y, int width, int height, int red, int green, int blue) {
    super(x, y, width, height, red, green, blue);
  }

  @Override
  public void paintSelf(Graphics g) {
    g.setColor(new Color(this.red, this.green, this.blue));
    g.fillOval(this.x, this.y, this.width, this.height);
  }
}
