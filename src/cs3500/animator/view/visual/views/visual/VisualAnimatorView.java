package cs3500.animator.view.visual.views.visual;

import cs3500.animator.view.visual.window.BasicWindow;
import tools.Factory;

public class VisualAnimatorView extends AbstractVisualAnimator<BasicWindow> {

  @Override
  protected BasicWindow buildArtist(int width, int height) {
    return Factory.basicWindow(width, height);
  }
}
