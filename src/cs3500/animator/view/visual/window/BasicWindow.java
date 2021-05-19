package cs3500.animator.view.visual.window;

import cs3500.animator.view.visual.RequiresBuild;
import cs3500.animator.view.visual.Snapshot;
import cs3500.animator.view.visual.animationpanel.AnimationPanel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * Represents a window that only has a {@link AnimationPanel} in it.
 */
public class BasicWindow extends JPanel implements RequiresBuild {

  private final JFrame window;
  protected final JScrollPane animationContainer;
  protected final AnimationPanel animation;

  /**
   * Builds a BasicWindow.
   * @param xSize x dimension of the canvas.
   * @param ySize y dimension of the canvas.
   * @throws IllegalArgumentException if xSize or ySize negative
   */
  public BasicWindow(int xSize, int ySize) throws IllegalArgumentException {
    super();
    if (xSize < 0 || ySize < 0) {
      throw new IllegalArgumentException("Canvas dimension cannot be negative.");
    }
    this.window = new JFrame();
    this.window.setTitle("Animation");
    this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.animation = new AnimationPanel(xSize, ySize);
    this.animationContainer = new JScrollPane(this.animation);
    //this.setVisible(true);
    //this.pack();
  }

  @Override
  public void build() {
    this.add(this.animationContainer);
    this.window.add(this);
    this.window.setVisible(true);
    this.window.pack();
  }

  /**
   * Displays the current set of shapes as a picture.
   * Removes the current set of shapes.
   */
  public void draw() {
    this.animation.draw();
  }

  /**
   * Refreshes the animation to be blank.
   */
  public void discardShapes() {
    this.animation.removeAll();
  }

  /**
   * Stages the next shape to be drawn in the animation.
   * @param snapshot the next shape to be drawn.
   */
  public void stageShape(Snapshot snapshot) {
    this.animation.stageShape(snapshot);
  }
}
