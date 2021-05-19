package cs3500.animator.view;

import java.io.IOException;
import cs3500.animator.model.animator.ImmAnimator;
import java.io.Writer;

/**
 * This is an implementation of the AnimatorView for printing the animation to text.
 * Prints the location and dimension of the canvas plus the motion description of the imm model.
 */
public class TextAnimatorView implements ImmAnimatorView {

  private final Writer ap;
  private ImmAnimator animator;

  /**
   * Constructor for the TextAnimatorView class.
   * @param ap the appendable to write to.
   * @throws IllegalArgumentException if null or if dimensions are less than 0.
   */
  public TextAnimatorView(Writer ap) throws IllegalArgumentException {
    if (ap == null) {
      throw ( new IllegalArgumentException("Invalid inputs, must be non null"));
    }
    this.ap = ap;
  }

  @Override
  public void setImmAnimator(ImmAnimator a) {
    this.animator = a;
  }

  @Override
  public void startView() {

    StringBuilder sb = new StringBuilder();
    sb.append(animator.getCanvas().toString()).append("\n");

    sb.append(animator.motionDescription());

    try {
      this.ap.append(sb.toString());
    } catch (IOException e) {
      throw new IllegalStateException("Could not Transmit!");
    }


    try {
      ap.close();
    } catch (IOException e) {
      throw (new IllegalStateException("Could not close!"));
    }
  }


}
