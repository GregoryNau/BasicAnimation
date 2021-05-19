package cs3500.animator.controller.iterator;

/**
 * Represents an Animation to work similarly to an Iterator.
 * <p>Invariants:</p>
 * <ul>
 *   <li>Can go to any frame.
 *   <li>Will not skip any Shapes. They are given back-most first, front-most last.</li>
 * </ul>
 */
public interface IndexableIteratorAnimation extends ControllableIteratorAnimation {

  /**
   * Goes to tic of animation.
   * @param tic is the frame to go to.
   * @return this.
   * @throws IllegalArgumentException if the frame does not exist.
   */
  IndexableIteratorAnimation goToTic(int tic) throws IllegalArgumentException;

  /**
   * Gets the current tic of the animation.
   * @retuns this.
   */
  int getTic();

  /**
   * True if there is a next frame, false otherwise.
   * @return if there is a next frame.
   */
  boolean hasNextFrame();


  /**
   * Updates the Iterator with the proper last frames, shapes etc.
   */
  void update();

  /**
   * Toggles if the indexable iterator animation is looping
   */
  void toggleLooping();

  /**
   * Sets if the iterator is looping.
   * @param looping sets if the itterator is looping.
   */
  void setLooping(boolean looping);

  /**
   * Sets if the iterator is looping.
   * @returns if the animator is looping.
   */
  boolean getLooping();

}
