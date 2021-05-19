package cs3500.animator.view.visual.views;

import cs3500.animator.view.visual.views.visual.VisualAnimator;

// todo
public abstract class AbstractVisualView<A> implements VisualAnimator {

  protected A artist;

  protected abstract A buildArtist(int width, int height);

}
