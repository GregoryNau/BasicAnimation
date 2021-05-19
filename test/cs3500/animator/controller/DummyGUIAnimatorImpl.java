package cs3500.animator.controller;

import cs3500.animator.model.symbol.SymbolType;
import cs3500.animator.view.visual.IteratorAnimation;
import cs3500.animator.view.visual.views.gui.GUIAnimator;
import cs3500.animator.view.visual.views.visual.VisualAnimator;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DummyGUIAnimatorImpl implements GUIAnimator {

  ActionListener al;
  List<String> strings;
  int frame = 1;
  Color color;
  Dimension dimension;
  Point point;
  SymbolType symbolType;
  String name;
  int speed;

  public DummyGUIAnimatorImpl(List<String> strings) {
    this.strings = strings;
  }

  @Override
  public GUIAnimator addPauseListener(ActionListener listener) {
    return null;
  }

  @Override
  public GUIAnimator addDeleteKeyframeListener(ActionListener listener) {
    al = listener;
    return this;
  }

  @Override
  public GUIAnimator addAddKeyframeListener(ActionListener listener) {
    return this;
  }

  @Override
  public GUIAnimator addModifyKeyframeListener(ActionListener listener) {
    return this;
  }

  @Override
  public GUIAnimator addChangePlaySpeedListener(ActionListener listener) {
    return this;
  }

  @Override
  public GUIAnimator addLoopListener(ActionListener listener) {
    return this;
  }

  @Override
  public GUIAnimator addGotoListener(ActionListener listener) {
    return this;
  }

  @Override
  public GUIAnimator addPlayListener(ActionListener listener) {
    return this;
  }

  @Override
  public GUIAnimator addPublishListener(ActionListener listener) {
    return this;
  }

  @Override
  public GUIAnimator addReplayListener(ActionListener listener) {
    return this;
  }

  @Override
  public GUIAnimator addAddSymbolListener(ActionListener listener) {
    return this;
  }

  @Override
  public GUIAnimator addDeleteSymbolListener(ActionListener listener) {
    return this;
  }

  @Override
  public GUIAnimator addNamesListener(ActionListener listener) {
    return this;
  }

  @Override
  public GUIAnimator addUniversalListener(ActionListener listener) {
    return this;
  }

  @Override
  public int getSpeed() {
    return this.speed;
  }

  @Override
  public String getSymbolName() {
    return this.name;
  }

  @Override
  public SymbolType getSymbolType() {
    return this.symbolType;
  }

  @Override
  public Point getFramePoint() {
    return this.point;
  }

  @Override
  public Dimension getFrameDimension() {
    return this.dimension;
  }

  @Override
  public Color getFrameColor() {
    return this.color;
  }

  @Override
  public int getFrameNum() {
    return this.frame;
  }

  @Override
  public int getGotoFrame() {
    return 2;
  }

  @Override
  public void startView() {
    int i = 0;
    for (String s : strings) {
      al.actionPerformed(new ActionEvent(this, 1, s));
      i++;
    }
  }

  @Override
  public VisualAnimator setIteratorAnimation(IteratorAnimation iteratorAnimation, Dimension size)
      throws IllegalStateException {
    return null;
  }

  @Override
  public VisualAnimator update() {
    return null;
  }
}
