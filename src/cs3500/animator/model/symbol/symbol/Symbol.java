package cs3500.animator.model.symbol.symbol;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

/**
 * Represents a symbol (a single unit that moves together) in an animation.
 *
 * <p>A symbol will assume continuous movement between keyframes. When constructed
 * there are no keyframes. If there are no keyframes, symbol will stay at its default
 * values and not move. If there is a single keyframe, the symbol will stay at its
 * keyframe value and not move. Two keyframes are required to start change.</p>
 *
 * <p>If the symbol has a keyframe that represents the symbol at {@link Point}(0, 0) for tick 1.
 * And the symbol has a keyframe that represents the symbol at point(10, 10) for tick 3.
 * goTo(2) will transform this symbol so that its position is at point(5, 5).</p>
 */
public interface Symbol extends Cloneable {

  /**
   * Returns the type of symbol. (ex: Rectangle, Ellipse, ...)
   * @return the type of symbol.
   */
  String getType();

  /**
   * Locks the current state of this symbol as a keyframe at given tick.
   * <p>If Keyframe already exists at that frame, the new state will overwrite it.</p>
   * @param frame the frame the symbol is expected to be at this state.
   */
  void lockKeyframe(int frame);

  /**
   * Removes a from the frame at given tick.
   * @param frame the frame the Keyframe is on that should be removed.
   * @throws IllegalArgumentException if tick does not have a Keyframe.
   */
  void unlockKeyframe(int frame) throws IllegalArgumentException;

  /**
   * Returns an array containing the frame index of all the keyframes of this Symbol.
   * <p>Returns null if there are no keyframes.</p>
   * <p>Array can be expected to be in ascending order.</p>
   * <p>Array will have no duplicates.</p>
   * <p>The length of the Array will be the number of keyframes this Symbol currently has.</p>
   * @return an array containing the frame index of all the Keyframes of this Symbol.
   */
  int[] getKeyFrames();

  /**
   * Transforms this Symbol into the state it should be in at given frame.
   * <p>If frame is not a keyframe, it will extrapolate it assuming continuous movement
   * between keyframes.</p>
   * @param frame the desired frame this symbol will now change state to.
   */
  void goTo(int frame);

  /**
   * Transforms this Symbol so that it is now at given Posn.
   * <p>Will not change any existing Keyframes.</p>
   * @param point The Point this symbol should now be at.
   * @throws IllegalArgumentException if point is null
   */
  void moveTo(Point point) throws IllegalArgumentException;

  /**
   * Transforms this Symbol so that it is now at given coordinates getX, getY.
   * @param x the getX coordinate.
   * @param y the getY coordinate.
   */
  void moveTo(int x, int y);

  /**
   * Returns the Point this Symbol is at currently.
   * @return The Point this Symbol is now at.
   */
  Point getPoint();

  /**
   * Transforms this Symbol so that it is now a given Color.
   * <p>Will not change any existing keyframes.</p>
   * @param colour The Color this Symbol should now be.
   * @throws IllegalArgumentException if color is null
   */
  void setColor(Color colour) throws IllegalArgumentException;

  /**
   * Transforms this Symbol so that it is now a Color
   * of given values of red, green, blue, and alpha.
   * @param red the red value of Color (0-255)
   * @param green the green value of Color (0-255)
   * @param blue the blue value of Color (0-255)
   * @param alpha the alpha value of Color (0-255)
   * @throws IllegalArgumentException if parameters cannot construct a viable {@link Color}.
   */
  void setColor(int red, int green, int blue, int alpha) throws IllegalArgumentException;

  /**
   * Transforms this Symbol so that it is now a Color
   * of given values of red, green, and blue.
   * @param red the red value of Color (0-255)
   * @param green the green value of Color (0-255)
   * @param blue the blue value of Color (0-255)
   * @throws IllegalArgumentException if parameters cannot construct a viable {@link Color}.
   */
  void setColor(int red, int green, int blue) throws IllegalArgumentException;

  /**
   * Returns the current Color of this Symbol.
   * @return the current Color of this Symbol.
   */
  Color getColor();

  /**
   * Transforms this Symbol so that it is now a given Dimension.
   * <p>Will not change any existing keyframes.</p>
   * @param dimension the Dimension this Symbol should now be reflecting.
   * @throws IllegalArgumentException if Dimension is null
   */
  void setSize(Dimension dimension) throws IllegalArgumentException;

  /**
   * Transforms this Symbol so that it is now a given dimension.
   * @param width the width of the dimension. (>=0)
   * @param height the height of the dimension. (>=0)
   * @throws IllegalArgumentException if the arguments cannot construct a viable {@link Dimension}.
   */
  void setSize(int width, int height) throws IllegalArgumentException;

  /**
   * Returns the current Dimension of this Symbol.
   * @return the current Dimension of this Symbol.
   */
  Dimension getSize();

  /**
   * Transforms this Symbol so that it is now at a given degree value.
   * <p>Will not change any existing keyframes.</p>
   *
   * <p>Rotation is represented in degrees. Over 360 degrees will mean this symbol will
   * spin more than once. A negative value means this symbol rotated counter-clockwise.</p>
   * @param degree the rotation in degrees.
   */
  void setRotation(int degree);

  /**
   * Returns the current degree of rotation.
   * <p>Rotation is represented in degrees. Over 360 degrees will mean this symbol will
   * spin more than once. A negative value means this symbol rotated counter-clockwise.</p>
   * @return the current degree of rotation.
   */
  int getRotation();

  /**
   * Returns a Symbol which describes the exact same motion and shape.
   * @return a Symbol which describes the exact same motion and shape.
   */
  Symbol clone();
}
