package cs3500.animator.model.animator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.Map;
import cs3500.animator.model.symbol.symbol.Symbol;
import cs3500.animator.model.symbol.SymbolType;

/**
 * Animator is an interface for creating an animation.
 * <p>It creates {@link Symbol}s which represent items in the animation.</p>
 * <p>It can lock keyframes for each symbol, describing set points in symbol's characteristics.</p>
 * <p>Motion is defined as the path between two keyframes.</p>
 * <p>Two keyframes are necessary to make a motion.</p>
 * <p>The Animator returns a string of the motion description of all symbols, and their motions.</p>
 */
public interface Animator {

  /**
   * Specify the bounding box to be used for the animation.
   * @param x The leftmost x value
   * @param y The topmost y value
   * @param width The width of the bounding box
   * @param height The height of the bounding box
   */
  void setCanvas(int x, int y, int width, int height);

  /**
   * Creates a {@link Symbol} for the animator with a specific name and type.
   * <p>Will not overwrite symbols with the same name, but rather throw an error.</p>
   * @param name Name of a symbol.
   * @param type The symbol type.
   * @throws IllegalArgumentException if already exists, null values, or cannot create
   */
  void createSymbol(String name, SymbolType type) throws IllegalArgumentException;

  /**
   * Creates a keyframe for the specified {@link Symbol} with the specified parameters.
   * @param name The name of the symbol.
   * @param tick The tick time which the keyframe should take place
   * @param p The color at the keyframe position.
   * @param d The dimension at the keyframe.
   * @param c The color at the keyframe.
   * @throws IllegalArgumentException if the keyframe cannot be created or if null values passed.
   */
  void lockKeyframe(String name, int tick, Point p, Dimension d, Color c)
      throws IllegalArgumentException;


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
   * Sets the speed this animation is intended to play at.
   * @param fps the frames per second this animation is intended to play at.
   */
  void setSpeed(float fps);

  /**
   * Returns the speed this animation is intended to play at.
   * @return the frames per second this animation is intended to play at.
   */
  float getSpeed();


  /**
   * Removes a keyframe from a shape.
   * @param name The name of the shape.
   * @param frame The keyframe number.
   * @throws IllegalArgumentException if the shape does not have the keyframe.
   */
  void unlockKeyframe(String name, int frame) throws IllegalArgumentException;

  /**
   * Removes symbol from the model.
   * @throws IllegalArgumentException if the name is not found.
   */
  void removeSymbol(String name) throws IllegalArgumentException;

  /**
   * Sets if the animation is looping or not.
   * True is the animation should loop.
   * False is the animation should not loop.
   * @param looping if the animation should loop or not.
   */
  void setLooping(Boolean looping);

  /**
   * Gets if the animation is looping or not.
   * @returns if the animation should loop or not.
   */
  boolean getLooping();

  /**
   * Finds and returns the last tick of motion in this animation.
   * @return the last tick of motion in this animation.
   */
  int lastTick();

}
