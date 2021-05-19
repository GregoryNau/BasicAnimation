package cs3500.animator.controller;

import cs3500.animator.model.animator.Animator;
import cs3500.animator.view.AnimatorView;
import cs3500.animator.view.SVGAnimatorView;
import cs3500.animator.view.TextAnimatorView;
import cs3500.animator.view.ViewType;
import cs3500.animator.view.visual.views.gui.GUIAnimator;
import cs3500.animator.view.visual.views.gui.GUIAnimatorView;
import cs3500.animator.view.visual.views.visual.VisualAnimator;
import cs3500.animator.view.visual.views.visual.VisualAnimatorView;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import tools.Factory;


/**
 * Implements ControlBuilder, works with the text, svg and visual views.
 */
public class ControlBuilderImpl implements ControlBuilder {
  private ViewType type;
  private Animator a;
  private Writer ap = new BufferedWriter(new OutputStreamWriter(System.out));
  private int speed = 1;

  private boolean typeSet = false;
  private boolean animatorSet = false;

  @Override
  public void setType(String type) throws IllegalArgumentException {
    switch (type) {
      case "text":
        this.type = ViewType.TEXT;
        break;
      case "svg":
        this.type = ViewType.SVG;
        break;
      case "visual":
        this.type = ViewType.VISUAL;
        break;
      case "edit":
        this.type = ViewType.GUI;
        break;
      default:
        throw (new IllegalArgumentException(String.format("Unsupported type %s", type)));
    }
    typeSet = true;
  }

  @Override
  public void setModel(Animator animator) throws IllegalArgumentException {
    if (animator == null) {
      throw (new IllegalArgumentException("Model must be non null"));
    }
    this.a = animator;
    animatorSet = true;
  }

  @Override
  public void setOutputFile(String outputFile) throws IllegalArgumentException {
    if (outputFile == null) {
      throw (new IllegalArgumentException("outputFile must be non null!"));
    }
    try {
      ap = new FileWriter(outputFile, false);
    } catch (IOException e) {
      throw new IllegalArgumentException("Could not create desired file writer!");
    }
  }

  @Override
  public void setSpeed(int speed) {
    this.speed = speed;
  }

  @Override
  public AnimatorController build() throws IllegalStateException {
    AnimatorView v;
    AnimatorController c;

    if (!animatorSet) {
      throw (new IllegalArgumentException("Did not set animator!"));
    }
    if (!typeSet) {
      throw (new IllegalArgumentException("Did not set type!"));
    }

    switch (type) {
      case TEXT:
        v = new TextAnimatorView(ap);
        c = new SimpleController(this.a, (TextAnimatorView)v);
        break;
      case VISUAL:
        v = new VisualAnimatorView();
        c = Factory.visualAnimatorController((VisualAnimator)v, this.a);
        break;
      case SVG:
        v = new SVGAnimatorView(ap);
        c = new SimpleController(this.a, (SVGAnimatorView)v);
        break;
      case GUI:
        v = new GUIAnimatorView();
        c = Factory.animatorControllerImpl((GUIAnimator)v, this.a);
        break;
      default:
        throw (new IllegalStateException("Why did I get to the default in a enum switch?"));
    }
    return c;
  }
}
