package cs3500.animator.view.visual.views.visual;

import cs3500.animator.view.AnimatorView;
import cs3500.animator.view.visual.IteratorAnimation;
import java.awt.Dimension;

/**
 * Marker Interface for a {@link AnimatorView} that has a standalone visual representation.
 */
public interface VisualAnimator extends AnimatorView {

  // TODO
  VisualAnimator setIteratorAnimation(IteratorAnimation iteratorAnimation, Dimension size)
      throws IllegalStateException;

  VisualAnimator update(); // TODO

}
