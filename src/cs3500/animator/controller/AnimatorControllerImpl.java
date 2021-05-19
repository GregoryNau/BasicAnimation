package cs3500.animator.controller;

import cs3500.animator.controller.iterator.IndexableIteratorAnimation;
import cs3500.animator.controller.iterator.IndexableIteratorAnimationImpl;
import cs3500.animator.controller.scheduler.BasicScheduler;
import cs3500.animator.model.animator.Animator;
import cs3500.animator.model.animator.BasicImmAnimator;
import cs3500.animator.view.visual.views.gui.GUIAnimator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Implementation of the AnimatorController interface.
 * Controls the Animator model and the GUIAnimator view.
 * Invariants:
 * isPlaying is true only if a Scheduler is running.
 */
public class AnimatorControllerImpl
    <V extends GUIAnimator, A extends Animator,
        I extends IndexableIteratorAnimation, E extends BasicScheduler>
    extends VisualAnimatorController<V, A, I, E>
    implements AnimatorController, ActionListener {

  private boolean isPlaying;

  protected AnimatorControllerImpl(V v, A m, I i, E e) {
    super(v, m, i, e);
    isPlaying = true;

    // Now that I have a better understanding, they do not all need their own listeners.
    v.addPauseListener(this);
    v.addDeleteKeyframeListener(this);
    v.addAddKeyframeListener(this);
    v.addModifyKeyframeListener(this);
    v.addChangePlaySpeedListener(this);
    v.addLoopListener(this);
    v.addGotoListener(this);
    v.addPlayListener(this);
    v.addPublishListener(this);
    v.addReplayListener(this);
    v.addAddSymbolListener(this);
    v.addDeleteSymbolListener(this);
  }

  /**
   * Constructor for the AnimatorControllerImpl.
   */
  public AnimatorControllerImpl(V v,  A m) {
    this(v, m, (I)new IndexableIteratorAnimationImpl(new BasicImmAnimator(m)),
        (E)new BasicScheduler(m.getSpeed()));
  }

  @Override
  public void startController() {
    super.startController();
  }

  /**
   * Callback for the action listener.
   * @param e the action event received.
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "DeleteKeyframe":
        handleDeleteKeyframe();
        break;

      case "Pause":
        handlePause();
        break;

      case "AddKeyframe":
        handleAddKeyframe();
        break;

      case "ModifyKeyframe":
        handleModifyKeyframe();
        break;

      case "ChangePlaySpeed":
        handleChangePlaySpeed();
        break;

      case "Loop":
        handleLoop();
        break;

      case "GoTo":
        handleGoto();
        break;

      case "Play":
        handlePlay();
        break;

      case "Publish":
        handlePublish();
        break;

      case "Replay":
        handleReplay();
        break;

      case "AddSymbol":
        handleAddSymbol();
        break;

      case "DeleteSymbol":
        handleDeleteSymbol();
        break;
    }
  }

  /**
   * Handles the pause command.
   */
  private void handlePause() {
    stopGoExecutor(false);
  }

  /**
   * Handles the delete keyframe command.
   */
  private void handleDeleteKeyframe() {
    m.unlockKeyframe(v.getSymbolName(), v.getFrameNum());
    modifiedModel();
  }

  /**
   * Handles the Add Keyframe Command.
   */
  private void handleAddKeyframe() {
    m.lockKeyframe(v.getSymbolName(),
        v.getFrameNum(),
        v.getFramePoint(),
        v.getFrameDimension(),
        v.getFrameColor());
    modifiedModel();
  }

  /**
   * Handles the modify keyframe command.
   */
  private void handleModifyKeyframe() {
    m.lockKeyframe(v.getSymbolName(),
        v.getFrameNum(),
        v.getFramePoint(),
        v.getFrameDimension(),
        v.getFrameColor());
    modifiedModel();
  }

  /**
   * Handles the change speed command.
   */
  private void handleChangePlaySpeed() {
    m.setSpeed((float)v.getSpeed());
    setExecutorSpeed();
    modifiedModel();
  }

  /**
   * Handles the loop command.
   */
  private void handleLoop() {
    i.toggleLooping();
    modifiedModel();
  }

  /**
   * Handles the goto command.
   */
  private void handleGoto() {
    //TODO: Change this back to getGoToFrame
    this.i.goToTic(v.getFrameNum());
    modifiedModel();
  }

  /**
   * Handles the play command.
   */
  private void handlePlay() {
    stopGoExecutor(true);
  }

  /**
   * Handles the publish symbol command.
   */
  private void handlePublish() {
    //TODO: Publish Type
    //TODO: File Name

    /*
    ControlBuilder VB = new ControlBuilderImpl();
    VB.setModel(m);
    VB.setType("text");
    VB.build();
    */

  }

  /**
   * Handles the replay command.
   */
  private void handleReplay() {
    stopGoExecutor(false);
    this.i.goToTic(1);
    modifiedModel();
    //TODO: Fix This!
    stopGoExecutor(true);
  }

  /**
   * Handles the add symbol command
   */
  private void handleAddSymbol() {
    m.createSymbol(v.getSymbolName(), v.getSymbolType());
    modifiedModel();
  }

  /**
   * Handles the delete symbol command.
   */
  private void handleDeleteSymbol() {
    m.removeSymbol(v.getSymbolName());
    modifiedModel();
  }

  /**
   * Sets the speed of the executor to the model speed
   */
  private void setExecutorSpeed() {
    this.executor.changeSpeed(this.m.getSpeed());
  }

  /**
   * Stops executor or plays executor.
   * @param play to play or stop executor.
   */
  private void stopGoExecutor(boolean play) {
    if (play && !isPlaying) {
      executor.go(this);
      isPlaying = true;
    }
    else if (!play && isPlaying) {
      executor.stop();
      isPlaying = false;
    }
  }

  /**
   * Called every time the model is modified to update the index animator.
   */
  private void modifiedModel() {
    this.i.update();
    v.update();
  }

  @Override
  public void run() {
    // TODO modify iterator
    super.run();
  }
}
