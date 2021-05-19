package cs3500.animator.view.visual.views.gui;

import cs3500.animator.model.symbol.SymbolType;
import cs3500.animator.view.AnimatorView;
import cs3500.animator.view.visual.views.visual.VisualAnimator;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionListener;

/**
 * Represents a {@link AnimatorView} which allows for User input.
 * User can change basic elements of the animation and the playback.
 */
public interface GUIAnimator extends VisualAnimator {

  /**
   * Stages {@link ActionListener} to be called whenever a pause event occurs.
   * @param listener ActionLister
   * @return this
   */
  GUIAnimator addPauseListener(ActionListener listener);

  /**
   * Stages {@link ActionListener} to be called whenever a delete keyframe event occurs.
   * @param listener ActionLister
   * @return this
   */
  GUIAnimator addDeleteKeyframeListener(ActionListener listener);

  /**
   * Stages {@link ActionListener} to be called whenever a add keyframe event occurs.
   * @param listener ActionLister
   * @return this
   */
  GUIAnimator addAddKeyframeListener(ActionListener listener);

  /**
   * Stages {@link ActionListener} to be called whenever a modify keyframe event occurs.
   * @param listener ActionLister
   * @return this
   */
  GUIAnimator addModifyKeyframeListener(ActionListener listener);

  /**
   * Stages {@link ActionListener} to be called whenever a change playback speed event occurs.
   * @param listener ActionLister
   * @return this
   */
  GUIAnimator addChangePlaySpeedListener(ActionListener listener);

  /**
   * Stages {@link ActionListener} to be called whenever a loop toggle event occurs.
   * @param listener ActionLister
   * @return this
   */
  GUIAnimator addLoopListener(ActionListener listener);

  /**
   * Stages {@link ActionListener} to be called whenever a "go to #frame in animation" event occurs.
   * @param listener ActionLister
   * @return this
   */
  GUIAnimator addGotoListener(ActionListener listener);

  /**
   * Stages {@link ActionListener} to be called whenever a play event occurs.
   * @param listener ActionLister
   * @return this
   */
  GUIAnimator addPlayListener(ActionListener listener);

  /**
   * Stages {@link ActionListener} to be called whenever a publish event occurs.
   * @param listener ActionLister
   * @return this
   */
  GUIAnimator addPublishListener(ActionListener listener);

  /**
   * Stages {@link ActionListener} to be called whenever a replay event occurs.
   * @param listener ActionLister
   * @return this
   */
  GUIAnimator addReplayListener(ActionListener listener);

  /**
   * Stages {@link ActionListener} to be called whenever a add Symbol event occurs.
   * @param listener ActionLister
   * @return this
   */
  GUIAnimator addAddSymbolListener(ActionListener listener);

  /**
   * Stages {@link ActionListener} to be called whenever a delete Symbol event occurs.
   * @param listener ActionLister
   * @return this
   */
  GUIAnimator addDeleteSymbolListener(ActionListener listener);

  // todo
  GUIAnimator addNamesListener(ActionListener listener);

  // todo
  GUIAnimator addUniversalListener(ActionListener listener);

  // todo
  //void queryName(String[] names);

  // todo
  //void queryFrame(int[][] frames);

  /**
   * Returns the current state of playback-speed.
   * <p>Returns 0 if nothing relevant is selected.</p>
   * @return the current state of playback-speed.
   */
  int getSpeed();

  /**
   * Returns the name of the currently selected Symbol.
   * <p>Returns null if nothing relevant
   * is selected.</p>
   * @return the name of the currently selected Symbol.
   */
  String getSymbolName();

  /**
   * Returns the type of the currently selected Symbol.
   * <p>Returns null if nothing relevant
   * is selected.</p>
   * @return the type of the currently selected Symbol.
   */
  SymbolType getSymbolType();

  /**
   * Returns the position of the currently selected Symbol.
   * <p>Returns null if nothing relevant
   * is selected.</p>
   * @return the position of the currently selected Symbol.
   */
  Point getFramePoint();

  /**
   * Returns the size of the currently selected Symbol.
   * <p>Returns null if nothing relevant
   * is selected.</p>
   * @return the size of the currently selected Symbol.
   */
  Dimension getFrameDimension();

  /**
   * Returns the color of the currently selected Symbol.
   * <p>Returns null if nothing relevant
   * is selected.</p>
   * @return the color of the currently selected Symbol.
   */
  Color getFrameColor();

  /**
   * Returns the frame number (tick) of the currently selected Symbol.
   * <p>Returns negative if nothing relevant
   * is selected.</p>
   * @return the frame number (tick) of the currently selected Symbol.♦
   */
  int getFrameNum();

  /**
   * Returns the frame number (tick) of the animation.
   * <p>Returns negative if nothing relevant is selected.</p>
   * @return the frame number (tick) of the animation.
   */
  int getGotoFrame();

}
