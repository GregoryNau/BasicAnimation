package cs3500.animator.controller.iterator;

import cs3500.animator.view.visual.IteratorAnimation;

/**
 * Represents an {@link IteratorAnimation} that can be mutated in specific ways.
 */
public interface ControllableIteratorAnimation extends IteratorAnimation {

  /**
   * Moves the animation to the next frame.
   * @return this.
   */
  ControllableIteratorAnimation nextFrame();

}
