package cs3500.animator.view.visual.components;


import java.awt.Color;
import java.awt.Graphics;

/**
 * Represents a rectangle shape.
 * <p>{@link AbstractComponent}</p>
 */
public class RectangleComponent extends AbstractComponent {

  /**
   * Constructs a {@link RectangleComponent}.
   * <p>{@link AbstractComponent#AbstractComponent(int, int, int, int, int, int, int)}</p>
   */
  public RectangleComponent(int x, int y, int width, int height, int red, int green, int blue) {
    super(x, y, width, height, red, green, blue);
  }

  @Override
  public void paintSelf(Graphics g) {
    g.setColor(new Color(this.red, this.green, this.blue));
    g.fillRect(this.x, this.y, this.width, this.height);
  }
}
