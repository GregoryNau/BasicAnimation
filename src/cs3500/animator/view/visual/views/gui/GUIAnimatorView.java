package cs3500.animator.view.visual.views.gui;

import cs3500.animator.view.visual.window.BasicLayoutWindow;

public class GUIAnimatorView extends AbstractGUIAnimator<BasicLayoutWindow> {

  @Override
  protected BasicLayoutWindow buildArtist(int width, int height) {
    return new BasicLayoutWindow(width, height);
    // return Factory.layoutWindow(width, height);
  }
}
