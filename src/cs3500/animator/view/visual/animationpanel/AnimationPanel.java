package cs3500.animator.view.visual.animationpanel;

import cs3500.animator.view.visual.Snapshot;
import cs3500.animator.view.visual.components.AbstractComponent;
import cs3500.animator.view.visual.components.EllipseComponent;
import cs3500.animator.view.visual.components.RectangleComponent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;

/**
 * Represents the {@link JPanel} that the animation will play on.
 * <p>Overrides removeAll, and paintComponent, to make animating
 * {@link AbstractComponent}s much more efficient.</p>
 * <p>Will still support other Components just like JPanel, but will
 * deffer to JPanel to handel those.</p>
 */
public class AnimationPanel extends JPanel {

  /**
   * The list of shapes in the animation.
   */
  private Collection<AbstractComponent> shapes;

  /**
   * Constructs this {@link AnimationPanel}.
   * <p>{@link JPanel#JPanel()}</p>
   */
  public AnimationPanel(int xSize, int ySize) {
    super();
    this.shapes = new ArrayList<>();
    this.setLayout(new OverlayLayout(this));
    this.setBackground(Color.WHITE);
    this.setPreferredSize(new Dimension(xSize, ySize));
  }

  /**
   * Adds a custom component that is a shape to this {@link AnimationPanel}.
   * @param c a {@link AbstractComponent} representing a shape to be drawn.
   * @return c
   */
  public AbstractComponent add(AbstractComponent c) {
    this.shapes.add(c);
    return c;
  }

  @Override
  public void removeAll() {
    super.removeAll();
    this.shapes = new ArrayList<>();
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    try {
      this.shapes.forEach(c -> c.paintSelf(g));
    } catch (ConcurrentModificationException e) {
      //System.out.println(e); // Swing's buffering system causes this on occasion.
      // not fatal.
    }
  }

  /**
   * Adds a shape to be drawn next. Will be placed onTop of existing shapes if they overlap.
   * @param snapshot a shape to be drawn.
   */
  public void stageShape(Snapshot snapshot) {
    AbstractComponent shape;
    int x = snapshot.point().x;
    int y = snapshot.point().y;
    int w = snapshot.size().width;
    int h = snapshot.size().height;
    int r = snapshot.color().getRed();
    int g = snapshot.color().getGreen();
    int b = snapshot.color().getBlue();

    switch (snapshot.type()) {
      case RECTANGLE:
        shape = new RectangleComponent(x, y, w, h, r, g, b);
        break;
      case ELLIPSE:
        shape = new EllipseComponent(x, y, w, h, r, g, b);
        break;
      default:
        throw new IllegalArgumentException("Not a valid shape type for this view.");
    }
    this.add(shape);
  }

  /**
   * Displays the current set of shapes as a picture.
   * Removes the current set of shapes.
   */
  public void draw() {
    this.revalidate();
    this.repaint();
  }

}
