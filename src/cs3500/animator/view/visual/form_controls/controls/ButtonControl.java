package cs3500.animator.view.visual.form_controls.controls;

import cs3500.animator.view.visual.views.gui.AbstractGUIAnimator.ControlListener;
import javax.swing.JButton;

public final class ButtonControl extends JButton {

  public ButtonControl(ControlListener listener, String caption) {
    super(caption);
    this.addChangeListener(listener);
  }
}
