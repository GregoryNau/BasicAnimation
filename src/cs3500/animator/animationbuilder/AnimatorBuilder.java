package cs3500.animator.animationbuilder;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import cs3500.animator.model.animator.Animator;
import tools.Factory;
import cs3500.animator.model.symbol.SymbolType;

/**
 * Builds an animation as a {@link Animator}.
 */
public final class AnimatorBuilder implements AnimationBuilder<Animator> {

  private final Animator animator;

  /**
   * Constructs an Animator Builder.
   */
  public AnimatorBuilder() {
    this.animator = Factory.animator();
  }

  /**
   * Constructs an Animator Builder using a given Animator.
   */
  public AnimatorBuilder(Animator animator) {
    this.animator = animator;
  }

  @Override
  public Animator build() {
    return this.animator;
  }

  @Override
  public AnimationBuilder<Animator> setBounds(int x, int y, int width, int height) {
    this.animator.setCanvas(x, y, width, height);
    return this;
  }

  @Override
  public AnimationBuilder<Animator> declareShape(String name, String type) {
    this.animator.createSymbol(name, SymbolType.make(type));
    return this;
  }

  @Override
  public AnimationBuilder<Animator> addMotion(String name, int t1, int x1, int y1, int w1, int h1,
      int r1, int g1, int b1, int t2, int x2, int y2, int w2, int h2, int r2, int g2, int b2) {
    this.addKeyframe(name, t1, x1, y1, w1, h1, r1, g1, b1);
    this.addKeyframe(name, t2, x2, y2, w2, h2, r2, g2, b2);
    return this;
  }

  @Override
  public AnimationBuilder<Animator> addKeyframe(String name, int t, int x, int y, int w, int h,
      int r, int g, int b) {
    try {
      this.animator.lockKeyframe(name, t,
          new Point(x, y),
          new Dimension(w, h),
          new Color(r, g, b));
    } catch (Exception e) {
      //System.out.println(e);
    }
    return this;
  }
}
