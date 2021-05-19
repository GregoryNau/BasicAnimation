package cs3500.animator.controller;

import cs3500.animator.model.animator.Animator;
import cs3500.animator.model.animator.BasicImmAnimator;
import cs3500.animator.view.ImmAnimatorView;

/**
 * Represents a controller for a {@link ImmAnimatorView} and {@link Animator} model.
 */
public final class SimpleController implements AnimatorController {

  private final Animator a;
  private final ImmAnimatorView v;

  /**
   * Constructs this controller.
   * @param a the model of the animation.
   * @param v the desired view.
   */
  public SimpleController(Animator a, ImmAnimatorView v) {
    this.a = a;
    this.v = v;
  }

  @Override
  public void startController() {
    v.setImmAnimator(new BasicImmAnimator(a));
    v.startView();
  }
}
