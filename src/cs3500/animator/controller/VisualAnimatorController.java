package cs3500.animator.controller;

import cs3500.animator.controller.iterator.BasicIteratorAnimation;
import cs3500.animator.controller.iterator.ControllableIteratorAnimation;
import cs3500.animator.controller.scheduler.BasicScheduler;
import cs3500.animator.model.animator.Animator;
import cs3500.animator.model.animator.BasicImmAnimator;
import cs3500.animator.view.visual.ScheduledRunner;
import cs3500.animator.view.visual.views.visual.VisualAnimator;

/**
 * Represents a controller for any {@link VisualAnimator} view and {@link Animator} model.
 * <p></p><p><b>INSTRUCTIONS FOR SUBCLASSES</b></p>
 * <ul>
 * <li>Subclasses should only use {@link VisualAnimatorController#VisualAnimatorController(
 * VisualAnimator, Animator, ControllableIteratorAnimation, ScheduledRunner)} as their
 * super constructor.</li>
 * <li>Subclasses should override {@link VisualAnimatorController#startController()} only if
 * they call its super at the end of the method.</li>
 * <li>Subclasses should override {@link VisualAnimatorController#run()} only if they
 * call its super at the end of the method.</li>
 * <li>Subclasses can override {@link VisualAnimatorController#stageNextFrame()} to change
 * how the {@link ControllableIteratorAnimation} works for the view. This will automatically
 * show up in the run method.</li>
 * </ul>
 * @param <V> The class of the view, bounded by {@link VisualAnimator}.
 * @param <A> The class of the model, bounded by {@link Animator}.
 * @param <I> The class of the iterator, bounded by {@link ControllableIteratorAnimation}.
 * @param <E> The class of the executor, bounded by {@link ScheduledRunner}.
 */
public class VisualAnimatorController
    <V extends VisualAnimator, A extends Animator, I extends ControllableIteratorAnimation,
        E extends ScheduledRunner>
    implements AnimatorController, Runnable {

  protected V v;
  protected A m;
  protected I i;
  protected E executor;

  /**
   * Sets this todo
   * @param v
   * @param m
   * @param i
   * @param s
   */
  protected VisualAnimatorController(V v, A m, I i, E s) {
    this.v = v;
    this.m = m;
    this.i = i;
    this.executor = s;
  }

  public VisualAnimatorController(V v, A m) {
    // todo generic instance of check
    this(v, m, (I)new BasicIteratorAnimation(new BasicImmAnimator(m)), // cast will always work
        (E)new BasicScheduler(m.getSpeed()));      // BasicScheduler implements ScheduledRunner
  }

  @Override
  public void startController() {
    v.setIteratorAnimation(this.i, this.m.getCanvas().getDimension());
    v.startView();
    this.executor.go(this);
  }

  @Override
  public void run() {
    this.stageNextFrame();
    this.v.update();
  }

  protected void stageNextFrame() {
    this.i.nextFrame();
  }
}
