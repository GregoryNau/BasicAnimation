package cs3500.animator.view.visual.views.gui;

import cs3500.animator.model.symbol.SymbolType;
import cs3500.animator.view.visual.views.visual.AbstractVisualAnimator;
import cs3500.animator.view.visual.window.BasicLayoutWindow;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Represents an {@link AbstractVisualAnimator} while also implementing {@link GUIAnimator}.
 * <p>A gui view which allows for {@link ActionListener}
 * that are interested in making/modifying/saving an animation.</p>
 * <p>Treats last clickable component that was clicked as "selected".</p>
 * */
public abstract class AbstractGUIAnimator<A extends BasicLayoutWindow> extends AbstractVisualAnimator<A>
    implements GUIAnimator {

  /**
   * An enumeration of each type of higher order event this view supports.
   */
  public enum ControlListener implements ChangeListener, ActionListener {
    Pause("Pause"), DeleteKeyframe("DeleteKeyframe"), AddKeyframe("AddKeyframe"),
    ModifyKeyframe("ModifyKeyframe"), Speed("ChangePlaySpeed"), Loop("Loop"),
    Play("Play"), GoTo("GoTo"), Publish("Publish"), Replay("Replay"),
    AddSymbol("AddSymbol"), DeleteSymbol("DeleteSymbol"), Names("Names");

    /**
     * A {@link Set} of all {@link ActionListener} that are interested in this
     * {@link ControlListener} and the higher order event it represents.
     * <p><i>Enums are initialized only once, and then each enumeration is constructed.
     * Thus any mutation on the object referenced by this field will be global.</i></p>
     */
    private final Set<ActionListener> listeners;

    /**
     * The command string for this higher order event.
     */
    private final String command;

    /**
     * Instantiates {@link ControlListener#listeners} as a {@link HashSet}.
     */
    ControlListener(String command) {
      this.listeners = new HashSet<>();
      this.command = command;
    }

    /**
     * Adds a {@link ActionListener} to this {@link ControlListener}.
     * <p>Protected so only classes that implement this can call addListener.
     * This prevents random listeners from being added by everyone who comes in contact
     * with this enum.</p>
     * @param listener an ActionListener which is interested in a higher order event.
     */
    protected void addListener(ActionListener listener) {
      this.listeners.add(listener);
    }

    /**
     * Triggers {@link ActionListener#actionPerformed(ActionEvent)} for each {@link ActionListener}
     * in this {@link ControlListener}.
     * @param event the {@link ActionEvent} to be used as a parameter for actionPerformed.
     */
    public void trigger(ActionEvent event) {
      for (ActionListener listener : listeners) {
        listener.actionPerformed(event);
      }
    }

    /**
     * Triggers {@link ActionListener#actionPerformed(ActionEvent)} for each {@link ActionListener}
     * in this {@link ControlListener}.
     * <p>Defaults {@link ActionEvent#source} to this enum and {@link ActionEvent#id}
     * to {@link ActionEvent#ACTION_PERFORMED}.</p>
     * @param command the String representing the command string in a {@link ActionEvent}.
     */
    public void trigger(String command) {
      this.trigger(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, command));
    }

    /**
     * Triggers {@link ActionListener#actionPerformed(ActionEvent)} for each {@link ActionListener}
     * in this {@link ControlListener}.
     * <p>Defaults {@link ActionEvent#source} to this enum and {@link ActionEvent#id}
     * to {@link ActionEvent#ACTION_PERFORMED} and {@link ActionEvent#actionCommand}
     * to {@link ControlListener#command}.</p>
     */
    public void trigger() {
      this.trigger(this.command());
    }

    /**
     * Returns the command string for this higher order event.
     * @return the command string.
     */
    public String command() {
      return this.command;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
      this.trigger();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      this.trigger();
    }
  }

  @Override
  public GUIAnimator addPauseListener(ActionListener listener) {
    ControlListener.Pause.addListener(listener);
    return this;
  }

  @Override
  public GUIAnimator addDeleteKeyframeListener(ActionListener listener) {
    ControlListener.DeleteKeyframe.addListener(listener);
    return this;
  }

  @Override
  public GUIAnimator addAddKeyframeListener(ActionListener listener) {
    ControlListener.AddKeyframe.addListener(listener);
    return this;
  }

  @Override
  public GUIAnimator addModifyKeyframeListener(ActionListener listener) {
    ControlListener.ModifyKeyframe.addListener(listener);
    return this;
  }

  @Override
  public GUIAnimator addChangePlaySpeedListener(ActionListener listener) {
    ControlListener.Speed.addListener(listener);
    return this;
  }

  @Override
  public GUIAnimator addLoopListener(ActionListener listener) {
    ControlListener.Loop.addListener(listener);
    return this;
  }

  @Override
  public GUIAnimator addGotoListener(ActionListener listener) {
    ControlListener.GoTo.addListener(listener);
    return this;
  }

  @Override
  public GUIAnimator addPlayListener(ActionListener listener) {
    ControlListener.Play.addListener(listener);
    return this;
  }

  @Override
  public GUIAnimator addPublishListener(ActionListener listener) {
    ControlListener.Publish.addListener(listener);
    return this;
  }

  @Override
  public GUIAnimator addReplayListener(ActionListener listener) {
    ControlListener.Replay.addListener(listener);
    return this;
  }

  @Override
  public GUIAnimator addAddSymbolListener(ActionListener listener) {
    ControlListener.AddSymbol.addListener(listener);
    return this;
  }

  @Override
  public GUIAnimator addDeleteSymbolListener(ActionListener listener) {
    ControlListener.DeleteSymbol.addListener(listener);
    return this;
  }

  @Override
  public GUIAnimator addNamesListener(ActionListener listener) {
    ControlListener.Names.addListener(listener);
    return this;
  }

  @Override
  public GUIAnimator addUniversalListener(ActionListener listener) {
    for (ControlListener c : ControlListener.values()) {
      c.addListener(listener);
    }
    return this;
  }

  @Override
  public int getSpeed() {
    this.check();
    return this.artist.getSpeed();
  }

  @Override
  public String getSymbolName() {
    this.check();
    return this.artist.getSymbolName();
  }

  @Override
  public SymbolType getSymbolType() {
    this.check();
    return this.artist.getSymbolType();
  }

  @Override
  public Point getFramePoint() {
    this.check();
    return this.artist.getFramePoint();
  }

  @Override
  public Dimension getFrameDimension() {
    this.check();
    return this.artist.getFrameDimension();
  }

  @Override
  public Color getFrameColor() {
    this.check();
    return this.artist.getFrameColor();
  }

  @Override
  public int getFrameNum() {
    this.check();
    return this.artist.getFrameNum();
  }

  @Override
  public int getGotoFrame() {
    this.check();
    return this.artist.getGotoFrame();
  }
}
