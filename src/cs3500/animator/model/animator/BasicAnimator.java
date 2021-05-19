package cs3500.animator.model.animator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import cs3500.animator.model.symbol.symbol.shapes.Ellipse;
import cs3500.animator.model.symbol.symbol.shapes.Rectangle;
import cs3500.animator.model.symbol.symbol.Symbol;
import cs3500.animator.model.symbol.SymbolType;
import cs3500.animator.model.symbol.frame.Frame;

/**
 * BasicAnimator is a implementation of the Animator class.
 * It stores {@link Symbol} in a {@link LinkedHashMap}
 * which preserves order of additions when iterated.
 * Invariants:
 * The {@link Frame} in the symbols cannot be changed
 *     other than through the lockKeyFrame method
 * Symbols can only be added to the map using the createSymbol method.
 * Each Symbol name belongs to a single symbol.
 */
public class BasicAnimator implements Animator {

  private Map<String, Symbol> symbolMap;
  private Canvas canvas;
  private float fps;
  private boolean looping;

  public BasicAnimator() {
    symbolMap = new LinkedHashMap<>();
    canvas = new CanvasImpl(0, 0, 100, 100);
    this.fps = 26;
  }

  @Override
  public void setCanvas(int x, int y, int width, int height) {
    canvas = new CanvasImpl(x, y, width, height);
  }

  @Override
  public void createSymbol(String name, SymbolType type) throws IllegalArgumentException {
    if (type == null || name == null) {
      throw new IllegalArgumentException("Inputs cannot be null");
    }

    if (symbolMap.containsKey(name)) {
      throw (new IllegalArgumentException("Cannot create. Named symbol is already in animation!"));
    }

    // Can be abstracted to a symbol factory in the future
    switch (type) {
      case ELLIPSE:
        symbolMap.put(name, new Ellipse());
        break;
      case  RECTANGLE:
        symbolMap.put(name, new Rectangle());
        break;
      default:
        throw(new IllegalArgumentException("symbol Not Supported!"));
    }
  }

  @Override
  public void lockKeyframe(String name, int tick, Point p, Dimension d, Color c)
      throws IllegalArgumentException {
    if (name == null || p == null || d == null || c == null) {
      throw (new IllegalArgumentException("All inputs must be non null"));
    }

    if (!symbolMap.containsKey(name)) {
      throw (new IllegalArgumentException("symbol with that name not created!"));
    }

    Symbol s = symbolMap.get(name);
    s.moveTo(p);
    s.setSize(d);
    s.setColor(c);
    s.lockKeyframe(tick);

  }

  @Override
  public String motionDescription() throws IllegalStateException {
    if (symbolMap.size() == 0) {
      throw new IllegalStateException("Does not contain any ");
    }

    StringBuilder strB = new StringBuilder();
    String name;
    Symbol s;

    int entryItr = 0;
    for (Map.Entry<String, Symbol> entry : symbolMap.entrySet()) {

      name = entry.getKey();
      s = entry.getValue();

      if (entryItr != 0) {
        strB.append("\n");
      }

      strB.append("shape ").append(name).append(" ").append(s.getType());

      int[] keyFrames = s.getKeyFrames();

      if (s.getKeyFrames() == null) {
        entryItr++;
        continue;
      }
      // Do not try to print motion if the number of keyframes is less than 2
      if (s.getKeyFrames().length < 2) {
        entryItr++;
        continue;
      }

      strB.append("\n");

      int startKeyFrame;
      int endKeyFrame;

      for (int i = 0; i < s.getKeyFrames().length - 1; i++) {
        strB.append("motion ").append(name).append(" ");

        startKeyFrame = keyFrames[i];
        endKeyFrame = keyFrames[i + 1];

        strB.append(keyFrameMotionDescription(s, startKeyFrame));
        strB.append("    ");
        strB.append(keyFrameMotionDescription(s, endKeyFrame));

        if (i < s.getKeyFrames().length - 2 && entryItr != symbolMap.size()) {
          strB.append("\n");
        }
      }
      entryItr++;
    }
    return strB.toString();
  }

  @Override
  public Map<String, Symbol> getSymbolMap() {
    return new LinkedHashMap<>(symbolMap);
  }

  @Override
  public Canvas getCanvas() {
    return this.canvas;
  }


  /**
   * KeyFrameMotionDescription creates the motion
   * description for a single {@link Frame}.
   * ie. t x y w h r g b.
   * t is the key time
   * x is the x position
   * y is the y position.
   * w is the width h is the height
   * r is the red value
   * g is the green value
   * b is the blue value.
   * Note this method mutates symbol by calling goTO
   * @return String with the motion description for a single key KeyFrame
   */
  private String keyFrameMotionDescription(Symbol s, int k) {
    StringBuilder strB = new StringBuilder();
    s.goTo(k);
    strB.append(Integer.toString(k)).append(" ");
    strB.append(Integer.toString(s.getPoint().x)).append(" ");
    strB.append(Integer.toString(s.getPoint().y)).append(" ");
    strB.append(Integer.toString(s.getSize().width)).append(" ");
    strB.append(Integer.toString(s.getSize().height)).append(" ");
    strB.append(Integer.toString(s.getColor().getRed())).append(" ");
    strB.append(Integer.toString(s.getColor().getGreen())).append(" ");
    strB.append(Integer.toString(s.getColor().getBlue()));
    return strB.toString();
  }

  @Override
  public void setSpeed(float fps) {
    this.fps = fps;
  }

  @Override
  public float getSpeed() {
    return this.fps;
  }

  @Override
  public void unlockKeyframe(String name, int frame) {
    if (!symbolMap.containsKey(name)) {
      throw (new IllegalArgumentException("symbol with that name not found!"));
    }
    symbolMap.get(name).unlockKeyframe(frame);
  }

  @Override
  public void removeSymbol(String name) throws IllegalArgumentException {
    if (!symbolMap.containsKey(name)) {
      throw (new IllegalArgumentException("symbol with that name not found!"));
    }
    symbolMap.remove(name);
  }

  @Override
  public void setLooping(Boolean looping) {
    this.looping = looping;
  }

  @Override
  public boolean getLooping() {
    return this.looping;
  }

  @Override
  public int lastTick() {
    int lastTick = 0;
    Collection<Symbol> symbols = getSymbolMap().values();
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
}
