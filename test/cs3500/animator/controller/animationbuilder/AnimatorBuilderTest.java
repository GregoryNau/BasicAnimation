package cs3500.animator.controller.animationbuilder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import cs3500.animator.animationbuilder.AnimationBuilder;
import cs3500.animator.animationbuilder.AnimatorBuilder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import cs3500.animator.model.animator.Animator;
import cs3500.animator.model.animator.Canvas;
import cs3500.animator.model.symbol.symbol.Symbol;
import cs3500.animator.model.symbol.SymbolType;
import org.junit.Test;
import tools.mock.MockTools;

public class AnimatorBuilderTest {

  /**
   * Represents an {@link Animator} for the purposes of testing {@link AnimationBuilder}.
   */
  private class DummyAnimator implements Animator {

    ArrayList<String> log;

    /**
     * Constructs a dummyAnimator.
     */
    DummyAnimator() {
      this.log = new ArrayList<>();
    }

    @Override
    public void setCanvas(int x, int y, int width, int height) {
      MockTools.addToLog(this.log, "setCanvas", x, y, width, height);
    }

    @Override
    public void createSymbol(String name, SymbolType type) throws IllegalArgumentException {
      MockTools.addToLog(this.log, "createSymbol", name, type);
    }

    @Override
    public void lockKeyframe(String name, int tick, Point p, Dimension d, Color c)
        throws IllegalArgumentException {
      MockTools.addToLog(this.log, "lockKeyframe", name, tick, p, d, c);
    }

    // does not use
    @Override
    public String motionDescription() throws IllegalStateException {
      throw new IllegalAccessError("why are you calling me???");
    }

    // does not use
    @Override
    public Map<String, Symbol> getSymbolMap() {
      throw new IllegalAccessError("why was I called???");
    }

    @Override
    public Canvas getCanvas() {
      throw new IllegalAccessError("why was I called???");
    }

    @Override
    public void setSpeed(float fps) {
      throw new IllegalAccessError("don't call me...");
    }

    @Override
    public float getSpeed() { throw new IllegalAccessError("nope... don't call me."); }

    @Override
    public void unlockKeyframe(String name, int frame) throws IllegalArgumentException {

    }

    @Override
    public void removeSymbol(String name) throws IllegalArgumentException {

    }

    @Override
    public void setLooping(Boolean looping) {

    }

    @Override
    public boolean getLooping() {
      return false;
    }

    @Override
    public int lastTick() {
      return 0;
    }

    /**
     * Returns an {@link Iterator} of this log.
     * @return an Iterator of this log.
     */
    public Iterator getLog() {
      return this.log.iterator();
    }
  }

  @Test
  public void testBasic() {
    AnimatorBuilder ab = new AnimatorBuilder(new DummyAnimator());
    ab.declareShape("sirFuzzyBoots", "rectangle");
    ab.declareShape("MadamCopenhagen", "ellipse");
    ab.setBounds(5, 3, 10, 20);
    ab.addMotion("MadamCopenhagen",
        1, 5, 3, 1, 1, 255, 255, 255,
        100, 15, 23, 3, 5, 0, 50, 200);
    ab.addKeyframe("MadamCopenhagen", 50, 1, 2, 3, 4, 5, 6, 7);
    ab.addMotion("sirFuzzyBoots",
        2, 6, 2, 2, 2, 50, 25, 100,
        10, 3, 7, 9, 1, 2, 100, 50);
    Iterator log = ((DummyAnimator)ab.build()).getLog();
    assertEquals("createSymbol(sirFuzzyBoots, rectangle);", log.next());
    assertEquals("createSymbol(MadamCopenhagen, ellipse);", log.next());
    assertEquals("setCanvas(5, 3, 10, 20);", log.next());
    assertEquals("lockKeyframe(MadamCopenhagen, 1, java.awt.Point[x=5,y=3], "
        + "java.awt.Dimension[width=1,height=1], java.awt.Color[r=255,g=255,b=255]);", log.next());
    assertEquals("lockKeyframe(MadamCopenhagen, 100, java.awt.Point[x=15,y=23], "
        + "java.awt.Dimension[width=3,height=5], java.awt.Color[r=0,g=50,b=200]);", log.next());
    assertEquals("lockKeyframe(MadamCopenhagen, 50, java.awt.Point[x=1,y=2], "
        + "java.awt.Dimension[width=3,height=4], java.awt.Color[r=5,g=6,b=7]);", log.next());
    assertEquals("lockKeyframe(sirFuzzyBoots, 2, java.awt.Point[x=6,y=2], "
        + "java.awt.Dimension[width=2,height=2], java.awt.Color[r=50,g=25,b=100]);", log.next());
    assertEquals("lockKeyframe(sirFuzzyBoots, 10, java.awt.Point[x=3,y=7], "
        + "java.awt.Dimension[width=9,height=1], java.awt.Color[r=2,g=100,b=50]);", log.next());
    assertFalse(log.hasNext());
  }
}