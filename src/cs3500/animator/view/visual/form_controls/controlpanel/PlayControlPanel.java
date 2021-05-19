package cs3500.animator.view.visual.form_controls.controlpanel;

import cs3500.animator.view.visual.RequiresBuild;
import cs3500.animator.view.visual.form_controls.controls.ButtonControl;
import cs3500.animator.view.visual.form_controls.controls.NumericalInputControl;
import cs3500.animator.view.visual.views.gui.AbstractGUIAnimator.ControlListener;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.GroupLayout;
import javax.swing.JPanel;

public class PlayControlPanel extends JPanel implements RequiresBuild {

  private final NumericalInputControl speed;
  private final ButtonControl play;
  private final ButtonControl replay;
  private final ButtonControl loop;
  private final ButtonControl pause;
  private final NumericalInputControl goTo;

  public PlayControlPanel() {
    super();
    this.setPreferredSize(new Dimension(500, 50));
    this.setBackground(Color.red);
    this.goTo = new NumericalInputControl(ControlListener.GoTo, 1);
    this.speed = new NumericalInputControl(ControlListener.Speed, 26);
    this.play = new ButtonControl(ControlListener.Play, "Play");
    this.replay = new ButtonControl(ControlListener.Replay, "Replay");
    this.loop = new ButtonControl(ControlListener.Loop, "Loop");
    this.pause = new ButtonControl(ControlListener.Pause, "Pause");
  }

  @Override
  public void build() {
    GroupLayout layout = new GroupLayout(this);
    this.setLayout(layout);
    this.buildHorizontal(layout);
    this.buildVertical(layout);
  }

  private void buildHorizontal(GroupLayout layout) {
    layout.setHorizontalGroup(
        layout.createSequentialGroup()
            .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(this.speed)
            .addGap(2, 5, 10)
            .addComponent(this.play)
            .addGap(2, 5, 10)
            .addComponent(this.replay)
            .addGap(2, 5, 10)
            .addComponent(this.pause)
            .addGap(2, 5, 10)
            .addComponent(this.loop)
            .addGap(2, 5, 10)
            .addComponent(this.goTo)
            .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
  }

  private void buildVertical(GroupLayout layout) {
    layout.setVerticalGroup(
        layout.createSequentialGroup()
            .addContainerGap(5, 10)
            .addGroup(layout.createParallelGroup()
                .addComponent(this.speed)
                .addComponent(this.play)
                .addComponent(this.replay)
                .addComponent(this.pause)
                .addComponent(this.loop)
                .addComponent(this.goTo))
            .addContainerGap(5, 10));
  }

  public int getSpeed() {
    return this.speed.speed();
  }

  public int getGotoFrame() {
    return 0; // TODO
  }
}
