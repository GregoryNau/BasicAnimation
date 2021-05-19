package cs3500.animator.controller.iterator;

import cs3500.animator.model.animator.ImmAnimator;
import cs3500.animator.model.symbol.frame.Frame;
import cs3500.animator.model.symbol.published.PublishedSymbol;
import cs3500.animator.model.symbol.symbol.Symbol;
import cs3500.animator.view.visual.IteratorAnimation;
import cs3500.animator.view.visual.Snapshot;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import tools.ListTools;

/**
 * An {@link IteratorAnimation} that only moves forward.
 */
public class BasicIteratorAnimation implements ControllableIteratorAnimation {

  private final List<PublishedSymbol> shapes;
  private int index;
  private int tick;

  /**
   * Constructs an {@link BasicIteratorAnimation} using given {@link ImmAnimator}.
   * <p>Will transform the motion to make it as if the top left corner is
   * located at a point (ImmAnimators Canvas's getPoint) represented by int x and int y.</p>
   *
   * @param animator the animator that stores the data describing the desired motion.
   */
  public BasicIteratorAnimation(ImmAnimator animator) {
    this.tick = 0;
    this.index = -1;
    this.shapes = publish(animator,
        -animator.getCanvas().getPoint().x,
        -animator.getCanvas().getPoint().y);
  }

  /**
   * A {@link Constructor} will take a {@link Symbol} and return a {@link PublishedSymbol} that
   * represents the same motion.
   */
  private static class Constructor implements Function<Symbol, PublishedSymbol> {

    private final int x;
    private final int y;

    /**
     * Creates a {@link Constructor} with no additional transformations on motion.
     */
    private Constructor() {
      this(0, 0);
    }

    /**
     * Creates a {@link Constructor} with a positional transformation on motion.
     *
     * @param x the value the x coordinate should increase by for all points in a motion.
     * @param y the value the y coordinate should increase by for all points in a motion.
     */
    private Constructor(int x, int y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public PublishedSymbol apply(Symbol symbol) {
      return new PublishedSymbol(symbol, this.x, this.y);
    }
  }

  /**
   * Turns an {@link ImmAnimator} into a {@link List} or {@link PublishedSymbol}.
   * <p>This List will be in sequential order to draw: back to front.</p>
   *
   * @param animator the animator that represents the animation.
   * @return a List of PublishedSymbols representing the animation.
   */
  private static List<PublishedSymbol> publish(ImmAnimator animator, int x, int y) {
    return ListTools.transform(new ArrayList<>(animator.getSymbolMap().values()),
        new Constructor(x, y));
  }



  @Override
  public boolean hasNextShape() {
    // System.out.print("shape:" + this.index + ":" + this.shapes.size() + "\n");
    return this.index < this.shapes.size() - 1;
  }

  @Override
  public Snapshot nextShape() {
    index++;
    PublishedSymbol symbol = this.shapes.get(this.index);
    Frame frame = symbol.getFrame(this.tick);
    /*
    System.out.print("tick: " + tick);
    System.out.print(":" + frame.point().x + ":" + frame.point().y
        + ":" + frame.dimension().width + ":" + frame.dimension().height
        + ":" + frame.color().getRed() + ":" + frame.color().getGreen()
        + ":" + frame.color().getBlue());
    System.out.print("\n");
    */
    return new SnapshotImpl(frame.point(), frame.color(),
        frame.dimension(), (symbol.getType()));
  }

  @Override
  public BasicIteratorAnimation nextFrame() {
    this.tick++;
    this.index = -1;
    return this;
  }

}
