package cs3500.animator.model.symbol.published;

import java.awt.Point;
import java.util.TreeMap;
import cs3500.animator.model.symbol.SymbolType;
import cs3500.animator.model.symbol.frame.Frame;
import cs3500.animator.model.symbol.symbol.Symbol;
import cs3500.animator.model.symbol.tween.LinearTween;
import cs3500.animator.model.symbol.tween.Tween;

/**
 * Represents an immutable {@link Symbol}.
 * <p>Optimized for speed of {@link #getFrame(int)}.</p>
 */
public class PublishedSymbol {

  /**
   * A representation of this motion using {@link TreeMap}, {@link Integer}, and {@link Tween}.
   * <p>The Integer represents the first tick its paired Tween starts on.</p>
   * <p>The Tween is considered to apply to every tick until the next Integer key.</p>
   * <p>The Tween represents a partial motion.</p>
   */
  protected final TreeMap<Integer, Tween> tweens;

  /**
   * A representation of this motion using {@link TreeMap}, {@link Integer}, and {@link Frame}.
   * <p>The Integer represents the tick its paired Frame is on.</p>
   * <p>The Frame represents a single instance of a Symbol in motion at an unknown time.</p>
   */
  protected final TreeMap<Integer, Frame> keyframes;

  /**
   * the shape this Symbol is.
   */
  protected final SymbolType type;

  /**
   * Constructs this {@link PublishedSymbol} to represent the same motion as {@link Symbol} s.
   * @param s the Symbol representing a motion this Published Symbol will copy.
   */
  public PublishedSymbol(Symbol s) {
    this(s, 0, 0);
  }

  /**
   * Constructs this {@link PublishedSymbol} to represent the same motion as {@link Symbol} s.
   * <p>Will transform the position of the given Symbol by x, y values at every keyframe.</p>
   * <p>Will not mutate given Symbol.</p>
   * @param s The Symbol which represents a motion that this Published Symbol will mimic.
   * @param x the x axis transformation. Additive.
   * @param y the y axis transformation. Additive.
   */
  public PublishedSymbol(Symbol s, int x, int y) {
    Symbol sym = s;
    if (x != 0 || y != 0) { // if there is a transformation
      sym = s.clone();
      int[] keys = sym.getKeyFrames();
      for (int i = 0; i < keys.length; i++) {
        sym.goTo(keys[i]);
        Point p = sym.getPoint();
        sym.moveTo(p.x + x, p.y + y);
        sym.lockKeyframe(keys[i]);
      }
    }
    this.keyframes = makeKeyframes(sym);
    this.tweens = makeTweens(sym);
    this.type = SymbolType.make(sym.getType());
  }

  /**
   * Creates a representation of a motion using tweens.
   * <p>Uses a {@link TreeMap} of {@link Integer} and {@link Tween}.</p>
   * <p>The Integer represents the first frame a Tween is active.</p>
   * <p>The Tween will represent the motion itself.</p>
   * <p>The <b>next</b> key value represents the last frame the <b>previous</b> Tween is active.</p>
   * @param s The {@link Symbol} representing the desired motion.
   * @return A Tween representation of motion s.
   */
  protected static TreeMap<Integer, Tween> makeTweens(Symbol s) {
    TreeMap<Integer, Tween> tweens = new TreeMap<>();
    Frame start;
    Frame end;
    int[] frames = s.getKeyFrames();
    s.goTo(frames[0]);
    end = toFrame(s);
    for (int i = 1; i < frames.length; i++) {
      start = end;
      s.goTo(frames[i]);
      end = toFrame(s);
      tweens.put(frames[i - 1], toTween(start, frames[i - 1], end, frames[i]));
    }
    return tweens;
  }

  /**
   * Creates a representation of a motion using keyframes.
   * <p>Uses a {@link TreeMap} of {@link Integer} and {@link Frame}.</p>
   * <p>The Integer represent the tick number of the Frame.</p>
   * <p>The Frame is a snapshot of a {@link Symbol} in a moment of time.</p>
   * @param s The Symbol representing the desired motion.
   * @return A keyframe representation of motion s.
   */
  protected static TreeMap<Integer, Frame> makeKeyframes(Symbol s) {
    TreeMap<Integer, Frame> keyframes = new TreeMap<>();
    int[] frames = s.getKeyFrames();
    if (frames == null) {
      return null;
    }
    for (int i : frames) {
      s.goTo(i);
      keyframes.put(i, toFrame(s));
    }
    return keyframes;
  }

  /**
   * Takes a Symbol and turns its current state into a {@link Frame}.
   * <p>Override this method to define what a {@link Frame} is for
   * {@link #makeKeyframes(Symbol)} and {@link #makeTweens(Symbol)}.</p>
   * @param s the Symbol.
   * @return the current state of the Symbol as a Frame.
   */
  protected static Frame toFrame(Symbol s) {
    return new Frame(s.getPoint(), s.getSize(), s.getColor());
  }

  /**
   * Takes a set of values to create a {@link Tween} representing that motion.
   * <p>Override this method to change which Tween {@link #makeTweens(Symbol)} creates. </p>
   * @param a The first frame in the motion.
   * @param y The tick the first frame is on.
   * @param b The last frame in the motion.
   * @param z The tick the last frame is on.
   * @return a Tween representing that motion.
   */
  protected static Tween toTween(Frame a, int y, Frame b, int z) {
    return new LinearTween(a, y, b, z);
  }

  /**
   * Returns the state of this {@link PublishedSymbol} at a given tick.
   * If the keyframe is before the first keyframe, return empty frame.
   * @param tick the tick.
   * @return the {@link Frame} representing the state of this at given tick.
   */
  public Frame getFrame(int tick) {
    if (this.keyframes.containsKey(tick)) {
      // this frame is a keyframe, and is thus already computed.
      return this.keyframes.get(tick);
    } else if (tick < this.keyframes.firstKey()) {
      // this frame is before the first keyframe, and is thus equal to the first keyframe.
      return new Frame(0, 0, 0, 0, 0, 0, 0);
    } else if (tick > this.keyframes.lastKey()) {
      // this frame is after the last keyframe, and is thus equal to the last keyframe.
      return new Frame(0, 0, 0, 0, 0, 0, 0);
    } else {
      // this frame is between two existing keyframes.
      return this.tweens.get(this.tweens.floorKey(tick)).getFrame(tick);
    }
  }

  /**
   * Returns the {@link SymbolType} of this {@link PublishedSymbol}.
   * @return the type of shape this Symbol is.
   */
  public SymbolType getType() {
    return this.type;
  }
}
