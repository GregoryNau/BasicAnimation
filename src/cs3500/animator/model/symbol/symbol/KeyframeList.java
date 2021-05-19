package cs3500.animator.model.symbol.symbol;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.NoSuchElementException;
import java.util.TreeMap;
import cs3500.animator.model.symbol.frame.RotatableFrame;

/**
 * Represents a type of Symbol that will use a {@link TreeMap} of
 * {@link RotatableFrame}s to keep track of its motion.
 *
 * <p>Will not change its motion description unless lockKeyframe is called.</p>
 * <p>Will never call lockKeyframe on itself.</p>
 * <p>Will not mutate any objects passed to its public methods.</p>
 * <p>Will not store references of objects passed to it, thus motion can only be
 *      changed through method calls to this.</p>
 */
public abstract class KeyframeList implements Symbol {

  /**
   * Represents the position this Symbol is currently at.
   * <p>Not necessarily a position in the animation.</p>
   */
  protected Point point;

  /**
   * Represents the color this Symbol is currently.
   * <p>Not necessarily a part of the actual animation.</p>
   */
  protected Color color;

  /**
   * Represents the dimension of this Symbol currently.
   * <p>Not necessarily a part of the animation.</p>
   */
  protected Dimension size;

  /**
   * Represents the degree of rotation of this Symbol currently.
   * <p>Not necessarily a part of the animation.</p>
   */
  protected int degree;

  /**
   * A map of Keyframes which describe the motion this Symbol takes in an animation.
   * <p>The key value is an integer representing the frame number of the Keyframe.</p>
   */
  protected TreeMap<Integer, RotatableFrame> keyframes;

  /**
   * Constructs a given Symbol with given value objects.
   * @param point the coordinate
   * @param color the color
   * @param size the dimension
   * @param degree the degree of rotation
   */
  protected KeyframeList(Point point, Color color, Dimension size, int degree) {
    this.point = point;
    this.color = color;
    this.size = size;
    this.degree = degree;
    // TreeMap should stay sorted by Integer.compareTo
    this.keyframes = new TreeMap<>();
  }

  /**
   * Constructs a Symbol with given values.
   * @param x coordinate
   * @param y coordinate
   * @param red value (0-255)
   * @param green value (0-255)
   * @param blue value (0-255)
   * @param alpha value (0-255)
   * @param width value (>=0)
   * @param height value (>=0)
   * @param degree of rotation
   */
  protected KeyframeList(int x, int y,
      int red, int green, int blue, int alpha,
      int width, int height,
      int degree) {
    this(new Point(x, y),
        new Color(red, green, blue, alpha),
        new Dimension(width, height),
        degree);
  }

  /**
   * Constructs a Symbol with default values.
   * <p>Point(0, 0)</p>
   * <p>Dimension(0, 0)</p>
   * <p>Color(0, 0, 0, 255)</p>
   * <p>degree = 0</p>
   */
  public KeyframeList() {
    this(0, 0, 0, 0, 0, 255, 0, 0, 0);
  }

  /**
   * Turns the current state into a Keyframe object.
   * @return the current state as a Keyframe object.
   */
  protected RotatableFrame makeKeyframe() {
    return new RotatableFrame(this.point, this.size, this.color, this.degree);
  }

  @Override
  public void lockKeyframe(int frame) {
    try {
      unlockKeyframe(frame);
    } catch (IllegalArgumentException e) {
      // do nothing
    }
    // this.makeKeyframe will never return null.
    keyframes.put(frame, this.makeKeyframe());
  }

  @Override
  public void unlockKeyframe(int frame) throws IllegalArgumentException {
    // if null is returned, it is because there was no keyframe
    if (keyframes.remove(frame) == null) {
      throw new IllegalArgumentException("Invalid keyframe.");
    }
  }

  @Override
  public int[] getKeyFrames() {
    int size = this.keyframes.size();
    if (size == 0) {
      return null;
    }
    int[] frames =  new int[size];
    int index = 0;
    for (int key : this.keyframes.keySet()) {
      frames[index] = key;
      index++;
    }
    return frames;
  }

  @Override
  public void goTo(int frame) {
    int firstKey;
    int lastKey;
    try {
      // if there are no keyframes
      firstKey = this.keyframes.firstKey();
      lastKey = this.keyframes.lastKey();
    } catch (NoSuchElementException e) {
      // then my job is nothing!
      return;
    }
    // if this frame is a keyframe
    if (this.keyframes.containsKey(frame)) {
      // then my job is easy
      RotatableFrame key = this.keyframes.get(frame);
      this.setAll(key.point(), key.dimension(), key.color(), key.rotation());
    } else if (firstKey > frame) { // if there is no motion before
      // then my job is mostly easy
      // TODO add strategy pattern for GoTo
      // RotatableFrame key = this.keyframes.get(firstKey);
      // this.setAll(key.point(), key.dimension(), key.color(), key.rotation());
      this.setAll(0, 0, 0, 0, 0, 0, 0 , 0, 0);
    } else if (lastKey < frame) { // if there is no motion after
      // then my job is still mostly easy
      // RotatableFrame key = this.keyframes.get(lastKey);
      // this.setAll(key.point(), key.dimension(), key.color(), key.rotation());
      this.setAll(0, 0, 0, 0, 0, 0, 0, 0, 0);
    } else { // this frame is between multiple keyframes.
      // so my job is a bitch!

      // Get the index and keyframes
      int fB = this.keyframes.floorKey(frame); // frame of keyframe before target frame
      int fA = this.keyframes.ceilingKey(frame); // frame of keyframe after target frame
      RotatableFrame bK = this.keyframes.get(fB); // keyframe before target frame
      RotatableFrame aK = this.keyframes.get(fA); // keyframe after target frame

      // casts to float to avoid rounding errors
      float dB = (float)(fA - frame) / (fA - fB); // before differential
      float dA = (float)(frame - fB) / (fA - fB); // after differential

      Point bP = bK.point(); // before Posn
      Point aP = aK.point(); // after Posn
      Color bC = bK.color(); // before Color
      Color aC = aK.color(); // after Color
      Dimension bD = bK.dimension(); // before Dimension
      Dimension aD = aK.dimension(); // after Dimension


      int x = Math.round((bP.x * dB) + (aP.x * dA)); // target x value
      int y = Math.round((bP.y * dB) + (aP.y * dA)); // target y value
      int r = Math.round((bC.getRed() * dB) + (aC.getRed() * dA)); // target red value
      int g = Math.round((bC.getGreen() * dB) + (aC.getGreen() * dA)); // target green value
      int b = Math.round((bC.getBlue() * dB) + (aC.getBlue() * dA)); // target blue value
      int a = Math.round((bC.getAlpha() * dB) + (aC.getAlpha() * dA)); // target alpha value
      int w = Math.round((bD.width * dB) + (aD.width * dA)); // target width value
      int h = Math.round((bD.height * dB) + (aD.height * dA)); // target height value
      int d = Math.round((bK.rotation() * dB) + (aK.rotation() * dA)); // target degree value

      // set to target values
      this.setAll(x, y, w, h, r, g, b, a, d);
    }
  }

  /**
   * Sets all the parameters for this Symbol.
   * <p>Mainly used by goTo and copy for convenience.</p>
   * @param point the point
   * @param size the dimension
   * @param color the color
   * @param degree the degree of rotation
   */
  protected void setAll(Point point, Dimension size, Color color, int degree) {
    this.moveTo(point);
    this.setColor(color);
    this.setSize(size);
    this.setRotation(degree);
  }

  /**
   * Sets all the parameters for this Symbol.
   * <p>Mainly used by goTo and copy for convenience.</p>
   * @param x the x value of Posn
   * @param y the y value of Posn
   * @param w the width value of Color
   * @param h the height value of Color
   * @param r the red value of Color
   * @param g the green value of Color
   * @param b the blue value of Color
   * @param a the alpha value of Color
   * @param d the degree value of Color
   * @throws IllegalArgumentException if parameters are invalid.
   */
  protected void setAll(int x, int y, int w, int h, int r, int g, int b, int a, int d)
      throws IllegalArgumentException {
    // does not call other setAll to avoid creating unnecessary copies.
    this.point = new Point(x, y);
    this.color = new Color(r, g, b, a);
    this.size = new Dimension(w, h);
    this.degree = d;
  }

  @Override
  public void moveTo(Point point) throws IllegalArgumentException {
    if (point == null) {
      throw new IllegalArgumentException("Posn cannot be null.");
    }
    // doesn't break apart and call other moveTo to keep same Posn type as passed in
    this.point = (Point)point.clone();
  }

  @Override
  public void moveTo(int x, int y) {
    // doesn't call other point to avoid creating unnecessary copies.
    this.point = new Point(x, y);
  }

  @Override
  public Point getPoint() {
    return (Point)this.point.clone();
  }

  @Override
  public void setColor(Color colour) throws IllegalArgumentException {
    if (colour == null) {
      throw new IllegalArgumentException("Color cannot be null.");
    }
    // doesn't break apart and call other setColor to keep the same type and Color passed in
    this.color = colour;
  }

  @Override
  public void setColor(int red, int green, int blue, int alpha) throws IllegalArgumentException {
    // doesn't call other setColor(Color) to avoid making unnecessary copies
    this.color = new Color(red, green, blue, alpha);
  }

  @Override
  public void setColor(int red, int green, int blue) throws IllegalArgumentException {
    // doesn't call setColor(Color) to avoid making unnecessary copies.
    this.color = new Color(red, green, blue);
  }

  @Override
  public Color getColor() {
    return this.color;
  }

  @Override
  public void setSize(Dimension dimension) throws IllegalArgumentException {
    if (dimension == null) {
      throw new IllegalArgumentException("Dimension cannot be null.");
    }
    // doesn't call other setSize to keep the same type as passed Dimension.
    this.size = (Dimension)dimension.clone();
  }

  @Override
  public void setSize(int width, int height) throws IllegalArgumentException {
    // doesn't call other setSize to avoid making unnecessary copies
    this.size = new Dimension(width, height);
  }

  @Override
  public Dimension getSize() {
    return (Dimension)this.size.clone();
  }

  @Override
  public void setRotation(int degree) {
    this.degree = degree;
  }

  @Override
  public int getRotation() {
    return this.degree;
  }

  /**
   * Creates a copy of this KeyframeList which describes the same motion.
   * <p>To be used by classes which extend KeyframeList as a convenient way to copy
   * the generic motion.</p>
   * <p>Will mutate given KeyframeList, and will return the same passed KeyframeList.</p>
   * @param copy the KeyframeList to put a copy of this motion into.
   * @return the passed in KeyframeList now mutated.
   */
  protected KeyframeList copy(KeyframeList copy) {
    for (int frame : this.getKeyFrames()) {
      RotatableFrame key = this.keyframes.get(frame);
      copy.setAll(key.point(), key.dimension(), key.color(), key.rotation());
      copy.lockKeyframe(frame);
    }
    return copy;
  }

  @Override
  public abstract KeyframeList clone();

  @Override
  public boolean equals(Object o) {
    // two Symbols with the same fields and same keyframes are NOT equal
    // they can overlap with Color and produce a different picture and therefore
    // two different Symbols cannot be treated the same.
    return this == o;
    // put here to EXPLICITLY say this is how we want it.
  }

  @Override
  public int hashCode() {
    return System.identityHashCode(this);
  }
}