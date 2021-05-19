package cs3500.animator.controller;

import cs3500.animator.controller.AnimatorController;
import cs3500.animator.model.animator.Animator;

/**
 * ControlBuilder builds a {@link AnimatorController} according to the set params.
 * Type and Model must be set before the animator should be built.
 * Otherwise an error will be thrown because not enough args were set.
 */
public interface ControlBuilder {

  /**
   * Sets the type of view to use.
   * Types must be "text", "svg", "visual"
   * @param type the type string.
   * @throws IllegalArgumentException if null or not valid type.
   */
  void setType(String type) throws IllegalArgumentException;

  /**
   * Sets the animator cs3500.animator.model to use.
   * @param animator the animator to use.
   * @throws IllegalArgumentException If the animator is null.
   */
  void setModel(Animator animator) throws IllegalArgumentException;

  /**
   * The string indicating the output file.
   * @param outputFile the output file string.
   * @throws IllegalArgumentException if the output file is null.
   */
  void setOutputFile(String outputFile) throws IllegalArgumentException;

  /**
   * Sets the speed of the view.
   * @param speed the speed.
   */
  void setSpeed(int speed);

  /**
   * Returns an {@link AnimatorController} built according to these params.
   * @return the controller that runs the animation.
   * @throws IllegalStateException if not all the required params were set.
   */
  AnimatorController build() throws IllegalStateException;
}
