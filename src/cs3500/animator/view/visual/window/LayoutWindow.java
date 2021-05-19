package cs3500.animator.view.visual.window;

import cs3500.animator.model.symbol.SymbolType;
import cs3500.animator.view.visual.RequiresBuild;
import cs3500.animator.view.visual.form_controls.controlpanel.AnimationControlPanel;
import cs3500.animator.view.visual.form_controls.controlpanel.PlayControlPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import javax.swing.GroupLayout;
import tools.Factory;

// TODO make resize prioritize animation not controls.
// TODO documentation for class
public class LayoutWindow extends BasicWindow implements RequiresBuild {

  private final AnimationControlPanel animationControl;
  private final PlayControlPanel playControl;

  public LayoutWindow(int xSize, int ySize, AnimationControlPanel animationControl,
      PlayControlPanel playControl) {
    super(xSize, ySize);
    this.animationControl = animationControl;
    this.playControl = playControl;
  }

  public LayoutWindow(int xSize, int ySize) {
    this(xSize, ySize, Factory.animationControl(), Factory.playControl());
  }

  @Override
  public void build() {
    GroupLayout layout = new GroupLayout(this);
    this.setLayout(layout);
    this.buildHorizontal(layout);
    this.buildVertical(layout);
    super.build();
  }

  private void buildHorizontal(GroupLayout layout) {
    layout.setHorizontalGroup(
        layout.createSequentialGroup()
            // Prevent animation control from changing significantly horizontally
            .addComponent(this.animationControl, GroupLayout.DEFAULT_SIZE,
                GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createParallelGroup()
                // force absolute resizability for the animation
                .addComponent(this.animationContainer, 0, GroupLayout.PREFERRED_SIZE,
                    Short.MAX_VALUE)
                // Prevent the Play control from being shrunk.22ww
                .addComponent(this.playControl, GroupLayout.PREFERRED_SIZE,
                    GroupLayout.PREFERRED_SIZE,  Short.MAX_VALUE)));
  }

  private void buildVertical(GroupLayout layout) {
    layout.setVerticalGroup(
        layout.createParallelGroup()
            // Prevent AnimationControl from being shrunk
            .addComponent(this.animationControl, GroupLayout.DEFAULT_SIZE,
                GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                // force absolute resizability for the animation
                .addComponent(this.animationContainer, 0, GroupLayout.PREFERRED_SIZE,
                    Short.MAX_VALUE)
                // Prevent PlayControl from resizing significantly vertically
                .addComponent(this.playControl, GroupLayout.DEFAULT_SIZE,
                    GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)));
  }


  public int getSpeed() {
    return this.playControl.getSpeed();
  }


  public String getSymbolName() {
    return this.animationControl.getSymbolName();
  }


  public SymbolType getSymbolType() {
    return this.animationControl.getSymbolType();
  }


  public Point getFramePoint() {
    return this.animationControl.getFramePoint();
  }


  public Dimension getFrameDimension() {
    return this.animationControl.getFrameDimension();
  }


  public Color getFrameColor() {
    return this.animationControl.getFrameColor();
  }


  public int getFrameNum() {
    return this.animationControl.getFrameNum();
  }


  public int getGotoFrame() {
    return this.playControl.getGotoFrame();
  }
}
