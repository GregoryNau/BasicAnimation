package cs3500.animator.view.visual.views.visual;

import cs3500.animator.view.visual.IteratorAnimation;
import cs3500.animator.view.visual.views.AbstractVisualView;
import cs3500.animator.view.visual.window.BasicWindow;
import java.awt.Dimension;

/**
 * Represents an entire visual view of an animation.
 */
// TODO don't need IteratorAnimation, ScheduledRunner.
public abstract class AbstractVisualAnimator<A extends BasicWindow> extends AbstractVisualView<A>
    implements VisualAnimator {

  protected IteratorAnimation animation;

  @Override
  public AbstractVisualAnimator setIteratorAnimation(IteratorAnimation iteratorAnimation,
      Dimension size) throws IllegalStateException {
    if (this.animation != null) {
      throw new IllegalStateException("View cannot be have an IteratorAnimation set twice.");
    }
    this.animation = iteratorAnimation;
    this.artist = this.buildArtist(size.width, size.height);
    return this;
  }

  protected void check() {
    if (this.animation == null) {
      throw new IllegalStateException("View has not been properly constructed yet.\n"
          + "Please call setIteratorAnimation method.");
    }
  }

  @Override
  public void startView() {
    this.check();
    this.artist.build();
  } // TODO

  // TODO
  public AbstractVisualAnimator<A> update() {
    this.check();
    this.artist.discardShapes();
    while (this.animation.hasNextShape()) {
      this.artist.stageShape(this.animation.nextShape());
    }
    this.artist.draw();
    return this;
  }
}
