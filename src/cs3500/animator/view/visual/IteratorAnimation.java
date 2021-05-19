package cs3500.animator.view.visual;

/**
 * Represents an Animation to work similarly to an Iterator.
 * <p>Invariants:</p>
 * <ul>
 *   <li>Frame will not change unless nextFrame is called. Then it will only iterate up once.</li>
 *   <li>Will not skip any Shapes. They are given back-most first, front-most last.</li>
 * </ul>
 */
public interface IteratorAnimation {

  /**
   * Determines if there is another object not yet returned to caller in this frame.
   * @return true if there is more objects. false if not.
   */
  boolean hasNextShape();

  /**
   * Returns the next object in this animation.
   * @return a Frame representing the next object in this animation.
   */
  Snapshot nextShape();

}
