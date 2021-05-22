package tools;

import cs3500.animator.controller.AnimatorControllerImpl;
import cs3500.animator.controller.VisualAnimatorController;
import cs3500.animator.model.animator.Animator;
import cs3500.animator.model.animator.BasicAnimator;
import cs3500.animator.view.visual.form_controls.controlpanel.AnimationControlPanel;
import cs3500.animator.view.visual.form_controls.controlpanel.PlayControlPanel;
import cs3500.animator.view.visual.views.gui.GUIAnimator;
import cs3500.animator.view.visual.views.visual.VisualAnimator;
import cs3500.animator.view.visual.window.BasicWindow;
import cs3500.animator.view.visual.window.LayoutWindow;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;



/**
 * Exists to construct instantiations of Interfaces without needing to depend on a
 * specific implementation.
 * <p>Methods added as needed.</p>
 */
public interface Factory {

  /**
   * Constructs a functional {@link Animator}.
   * @return a functional Animator.
   */
  @NotNull
  @Contract(" -> new")
  static Animator animator() {
    return new BasicAnimator();
  }

  // TODO
  static VisualAnimatorController visualAnimatorController(VisualAnimator v, Animator m) {
    return new VisualAnimatorController<>(v, m);
  }

  // TODO
  static AnimatorControllerImpl animatorControllerImpl(GUIAnimator v, Animator m) {
    return new AnimatorControllerImpl<>(v, m);
  }

  // TODO
  static BasicWindow basicWindow(int xSize, int ySize) {
    BasicWindow window = new BasicWindow(xSize, ySize);
    window.build();
    return window;
  }

  // TODO
  static LayoutWindow layoutWindow(int xSize, int ySize) {
    LayoutWindow window = new LayoutWindow(xSize, ySize);
    window.build();
    return window;
  }

  // TODO
  static PlayControlPanel playControl() {
    PlayControlPanel control = new PlayControlPanel();
    control.build();
    return control;
  }

  // TODO
  static AnimationControlPanel animationControl() {
    AnimationControlPanel control = new AnimationControlPanel();
    control.build();
    return control;
  }
}

