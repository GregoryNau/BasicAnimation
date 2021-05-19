package cs3500.animator.model.animator;

import java.util.Map;
import cs3500.animator.model.symbol.symbol.Symbol;

/**
 * An Immutable Animator class, for use in the view.
 * This controls of this interface should mutate keyframes or symbols.
 * Use {@link Animator} for full functionality.
 */
public interface ImmAnimator {

  /**
   * Returns a text representation of the animation.
   * <p>Prints the starting and ending keyframes of the inserted {@link Symbol}s.</p>
   * <p>Prints in the order in which Symbols are added to the animator.</p>
   * <p>Will only print motion if there are two or more keyframes looked to the symbol.</p>
   * @return a text representation of the animation.
   * @throws IllegalStateException called without Symbols.
   */
  String motionDescription() throws IllegalStateException;

  /**
   * Returns a copy of the map of symbols.
   * @return the copy of the string.
   */
  Map<String, Symbol> getSymbolMap();

  /**
   * Returns the stored canvas.
   * The canvas indicates the upper left position of the canvas.
   * Also indicates the width and height of the canvas.
   * @return the canvas.
   */
  Canvas getCanvas();

  /**
   * Returns the speed this animation is intended to play at.
   * @return the frames per second this animation is intended to play at.
   */
  float getSpeed();

  /**
   * Finds and returns the last tick of motion in this animation.
   * @return the last tick of motion in this animation.
   */
  int lastTick();

  /**
   * Returns if the model is looping.
   * @return if looping.
   */
  boolean getLooping();
}
