package cs3500.animator.view.visual.components;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JComponent;

/**
 * Exists to paint a specific shape or set of shapes onto {@link Graphics}.
 */
public abstract class AbstractComponent extends JComponent {

  protected final int x;
  protected final int y;
  protected final int width;
  protected final int height;
  protected final int red;
  protected final int green;
  protected final int blue;

  /**
   * Constructs an {@link AbstractComponent}.
   * @param x the x coordinate of the position.
   * @param y the y coordinate of the position.
   * @param width the width of its bounds box.
   * @param height the height of its bounds box.
   * @param red the red component of a rgb value.
   * @param green the green component of a rgb value.
   * @param blue the blue component of a rgb value.
   */
  protected AbstractComponent(int x, int y, int width, int height, int red, int green, int blue) {
    super();
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  /**
   * Paints this {@link AbstractComponent} on {@link Graphics}.
   * Goes ontop of all current components painted onto it.
   * @param g the Graphics to paint onto.
   */
  public abstract void paintSelf(Graphics g);
  /*
  Making this abstract does force some duplicate code. The setting of the Color for example is
  easily put into this class and removed as duplicate with a super call. However, it is much
  more important to force all subclasses to override this method. Thus forcing one line of
  duplicate code for this enforcement is worthwhile.
   */

  @Override // prevents subclass from overriding. forces a super call.
  public final void paintComponent(Graphics g) {
    super.paintComponent(g);
    this.paintSelf(g);
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(this.width, this.height);
  }
}
