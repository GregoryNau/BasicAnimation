package cs3500.animator.controller.iterator;

import cs3500.animator.model.animator.ImmAnimator;
import cs3500.animator.model.symbol.SymbolType;
import cs3500.animator.model.symbol.symbol.Symbol;
import cs3500.animator.view.visual.Snapshot;
import java.awt.Point;
import java.util.Collection;
import java.util.Iterator;

public class IndexableIteratorAnimationImpl implements IndexableIteratorAnimation {

  private final ImmAnimator animator;
  private int tick;
  private boolean isLooping;
  private int lastTick;
  private Point offset;
  private RestartableIterator symbols;

  public IndexableIteratorAnimationImpl(ImmAnimator model) {
    this.animator = model;
    this.tick = 0; // frames less than 1 make no sense. tick iterates before returning values;
    this.isLooping = false; // we are not .gif
    this.update();
  }

  private static class RestartableIterator implements Iterator<Symbol> {

    private final Collection<Symbol> base;
    private Iterator<Symbol> me;

    private RestartableIterator(Collection<Symbol> base) {
      this.base = base;
      this.me = base.iterator();
    }

    private void restart() {
      this.me = this.base.iterator();
    }

    @Override
    public boolean hasNext() {
      return this.me.hasNext();
    }

    @Override
    public Symbol next() {
      return this.me.next();
    }
  }

  @Override
  public IndexableIteratorAnimation goToTic(int tic) throws IllegalArgumentException {
    if (tic < 1) {
      String message = "Ticks cannot be less than 1.\n";
      message += "This view only works with Ticks in absolute terms.\n";
      message += "It does not support relational Tick values to a settable Tick.\n";
      message += "As such, retrieving frame number 0 or -1 makes no sense.\n";
      message += "An animation that has 0 frames does not play.\n";
      message += "Animations cannot be in debt with regards to frames.";
      throw new IllegalArgumentException(message);
    }
    this.tick = tic;
    return this;
  }

  @Override
  public int getTic() {
    return this.tick;
  }

  @Override
  public boolean hasNextFrame() {
    // if we are looping, we can continue to play.
    // if the animator has not run out of motions to describe, we can continue to play.
    return this.isLooping || this.tick < this.lastTick;
  }

  @Override
  public void update() {
    this.lastTick = this.animator.lastTick();
    this.offset = this.animator.getCanvas().getPoint();
    this.symbols = new RestartableIterator(this.animator.getSymbolMap().values());
  }

  @Override
  public void toggleLooping() {
    this.isLooping = !isLooping;
  }

  @Override
  public void setLooping(boolean looping) {
    isLooping = looping;
  }

  @Override
  public boolean getLooping() {
    return isLooping;
  }

  @Override
  public ControllableIteratorAnimation nextFrame() {
    if (this.tick == this.lastTick) {
      if (this.isLooping) {
        this.tick = 1;
      } else {
        throw new IllegalStateException("Next frame called. No next frame available.");
      }
    } else {
      this.tick++;
    }
    this.symbols.restart();
    return this;
  }

  @Override
  public boolean hasNextShape() {
    return this.symbols.hasNext();
  }

  @Override
  public Snapshot nextShape() {
    Symbol next = this.symbols.next();
    next.goTo(this.tick);
    return new SnapshotImpl(this.offset(next.getPoint()), next.getColor(),
        next.getSize(), SymbolType.make(next.getType()));
  }

  private Point offset(Point point) {
    return new Point(point.x - this.offset.x, point.y - this.offset.y);
  }
}
