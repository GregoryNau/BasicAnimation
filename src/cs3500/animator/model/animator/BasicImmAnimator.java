package cs3500.animator.model.animator;

import java.util.Collection;
import java.util.Map;
import cs3500.animator.model.symbol.symbol.Symbol;

/**
 * This class is an implementation of the ImmAnimator interface.
 * This implements the interface using composition, storing an Animator.
 * This class does not mutate the symbols or keyframes in the stored Animator.
 */
public final class BasicImmAnimator implements ImmAnimator {

  private final Animator a;

  /**
   * Constructor for the immutable animator.
   * @param a the animation.
   */
  public BasicImmAnimator(Animator a) {
    this.a = a;
  }

  @Override
  public String motionDescription() throws IllegalStateException {
    return a.motionDescription();
  }

  @Override
  public Map<String, Symbol> getSymbolMap() {
    return a.getSymbolMap();
  }

  @Override
  public Canvas getCanvas() {
    return a.getCanvas();
  }

  @Override
  public int lastTick() {
    int lastTick = 0;
    Collection<Symbol> symbols = this.a.getSymbolMap().values();
    for (Symbol s : symbols) {
      // s.getKeyFrames returns in ascending order. Last most value in the array is the largest.
      if (s.getKeyFrames() == null) {
      }
      else if (s.getKeyFrames().length == 1) {
        lastTick = Math.max(lastTick, s.getKeyFrames()[s.getKeyFrames().length - 1]);
      }
      else {
        lastTick = Math.max(lastTick, s.getKeyFrames()[s.getKeyFrames().length - 1]);
      }
    }
    return lastTick;
  }

  @Override
  public float getSpeed() {
    return this.a.getSpeed();
  }

  @Override
  public boolean getLooping() {return this.a.getLooping();}
}
