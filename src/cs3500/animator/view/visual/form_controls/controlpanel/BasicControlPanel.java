package cs3500.animator.view.visual.form_controls.controlpanel;

import cs3500.animator.model.symbol.SymbolType;
import cs3500.animator.view.visual.views.gui.AbstractGUIAnimator.ControlListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BasicControlPanel extends JPanel {

  private final JTextField name;
  private final JTextField type;
  private final JTextField frame;
  private final JTextField x;
  private final JTextField y;
  private final JTextField w;
  private final JTextField h;
  private final JTextField r;
  private final JTextField g;
  private final JTextField b;
  private final JTextField speedBox;

  private final JButton addFrame;
  private final JButton deleteFrame;
  private final JButton modifyFrame;
  private final JButton addSymbol;
  private final JButton deleteSymbol;

  private final JButton pause;
  private final JButton play;
  private final JButton loop;
  private final JButton replay;
  private final JButton setSpeed;
  private final JButton goTo;

  public BasicControlPanel() {
    this.name = new JTextField("Name");
    this.type = new JTextField("Type");
    this.frame = new JTextField("Keyframe");
    this.x = new JTextField("x");
    this.y = new JTextField("y");
    this.w = new JTextField("width");
    this.h = new JTextField("height");
    this.r = new JTextField("red");
    this.g = new JTextField("green");
    this.b = new JTextField("blue");

    this.addFrame = new JButton("add keyframe");
    this.deleteFrame = new JButton("delete keyframe");
    this.modifyFrame = new JButton("change keyframe");
    this.addSymbol = new JButton("add shape");
    this.deleteSymbol = new JButton("delete shape");

    this.pause = new JButton("pause");
    this.play = new JButton("play");
    this.loop = new JButton("loop");
    this.replay = new JButton("replay");
    this.setSpeed = new JButton("set speed");
    this.speedBox = new JTextField("speed");
    this.goTo = new JButton("GoTo Frame");

    this.addFrame.addActionListener(ControlListener.AddKeyframe);
    this.deleteFrame.addActionListener(ControlListener.DeleteKeyframe);
    this.modifyFrame.addActionListener(ControlListener.ModifyKeyframe);
    this.addSymbol.addActionListener(ControlListener.AddSymbol);
    this.deleteSymbol.addActionListener(ControlListener.DeleteSymbol);
    this.pause.addActionListener(ControlListener.Pause);
    this.play.addActionListener(ControlListener.Play);
    this.loop.addActionListener(ControlListener.Loop);
    this.replay.addActionListener(ControlListener.Replay);
    this.setSpeed.addActionListener(ControlListener.Speed);
    this.goTo.addActionListener(ControlListener.GoTo);

    this.add(this.name);
    this.add(this.type);
    this.add(this.frame);
    this.add(this.x);
    this.add(this.y);
    this.add(this.w);
    this.add(this.h);
    this.add(this.r);
    this.add(this.g);
    this.add(this.b);

    this.add(this.addFrame);
    this.add(this.deleteFrame);
    this.add(this.modifyFrame);
    this.add(this.addSymbol);
    this.add(this.deleteSymbol);

    this.add(this.pause);
    this.add(this.play);
    this.add(this.loop);
    this.add(this.replay);

    this.add(this.goTo);
    this.add(this.setSpeed);
    this.add(this.speedBox);

    GroupLayout layout = new GroupLayout(this);
    this.setLayout(layout);

    layout.setHorizontalGroup(
        layout.createParallelGroup()
            .addGroup(layout.createSequentialGroup()
                .addComponent(this.loop)
                .addComponent(this.play)
                .addComponent(this.pause)
                .addComponent(this.replay)
                //.addComponent(this.frame)
                .addComponent(this.goTo)
                .addComponent(this.speedBox)
                .addComponent(this.setSpeed))
            .addGroup(layout.createSequentialGroup()
                .addComponent(this.name)
                .addComponent(this.type)
                .addComponent(this.frame)
                .addComponent(this.x)
                .addComponent(this.y)
                .addComponent(this.w)
                .addComponent(this.h)
                .addComponent(this.r)
                .addComponent(this.g)
                .addComponent(this.b))
            .addGroup(layout.createSequentialGroup()
                .addComponent(this.addSymbol)
                .addComponent(this.deleteSymbol)
                .addComponent(this.addFrame)
                .addComponent(this.modifyFrame)
                .addComponent(this.deleteFrame)));

    layout.setVerticalGroup(
        layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup()
                .addComponent(this.loop)
                .addComponent(this.play)
                .addComponent(this.pause)
                .addComponent(this.replay)
                //.addComponent(this.frame)
                .addComponent(this.goTo)
                .addComponent(this.speedBox)
                .addComponent(this.setSpeed))
            .addGroup(layout.createParallelGroup()
                .addComponent(this.name)
                .addComponent(this.type)
                .addComponent(this.frame)
                .addComponent(this.x)
                .addComponent(this.y)
                .addComponent(this.w)
                .addComponent(this.h)
                .addComponent(this.r)
                .addComponent(this.g)
                .addComponent(this.b))
            .addGroup(layout.createParallelGroup()
                .addComponent(this.addSymbol)
                .addComponent(this.deleteSymbol)
                .addComponent(this.addFrame)
                .addComponent(this.modifyFrame)
                .addComponent(this.deleteFrame)));

    layout.setAutoCreateGaps(true);
    layout.setAutoCreateContainerGaps(true);

    this.setVisible(true);
  }

  public int getSpeed() throws NumberFormatException {
    try {
      return Integer.valueOf(this.speedBox.getText());
    } catch (IllegalArgumentException e) {
      return 0;
    }
  }


  public String getSymbolName() {
    return this.name.getText();
  }


  public SymbolType getSymbolType() {
    try {
      return SymbolType.make(this.type.getText());
    } catch (IllegalArgumentException e) {
      return null;
    }
  }


  public Point getFramePoint() {
    try {
      return new Point(Integer.valueOf(this.x.getText()), Integer.valueOf(this.y.getText()));
    } catch (IllegalArgumentException e) {
      return null;
    }
  }


  public Dimension getFrameDimension() {
    try {
      return new Dimension(Integer.valueOf(this.w.getText()), Integer.valueOf(this.h.getText()));
    } catch (IllegalArgumentException e) {
      return null;
    }
  }


  public Color getFrameColor() {
    try {
      return new Color(Integer.valueOf(this.r.getText()), Integer.valueOf(this.g.getText()),
          Integer.valueOf(this.b.getText()));
    } catch (IllegalArgumentException e) {
      return null;
    }
  }


  public int getFrameNum() {
    try {
      return Integer.valueOf(this.frame.getText());
    } catch (IllegalArgumentException e) {
      return 0;
    }
  }


  public int getGotoFrame() {
    try {
      return Integer.valueOf(this.goTo.getText());
    } catch (IllegalArgumentException e) {
      return 0;
    }
  }
}
