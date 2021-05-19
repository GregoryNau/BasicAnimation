package cs3500.animator.model.symbol.tween;

import cs3500.animator.model.symbol.frame.Frame;

/**
 * Represents a motion.
 */
public interface Tween {

  /**
   * Returns a {@link Frame} which is a snapshot of a shape in motion at a specific frame (tick).
   * @param frame the tick or frame number in a motion.
   * @return the Frame at given frame or tick.
   */
  Frame getFrame(int frame);

}
