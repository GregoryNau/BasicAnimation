package cs3500.animator.view.visual.window;

import cs3500.animator.model.symbol.SymbolType;
import cs3500.animator.view.visual.RequiresBuild;
import cs3500.animator.view.visual.form_controls.controlpanel.BasicControlPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import javax.swing.GroupLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

public class BasicLayoutWindow extends BasicWindow implements RequiresBuild {

  private final BasicControlPanel controlPanel;
  private JSplitPane split;
  private JPanel top;
  private JPanel bottom;
  private JPanel one;
  private JScrollPane scroll;

  public BasicLayoutWindow(int x, int y) {
    super(x, y);
    this.controlPanel = new BasicControlPanel();
  }

  @Override
  public void build() {
    //this.setLayout(new GroupLayout(this.getContentPane()));
    this.setLayout(new BorderLayout());
    this.add(this.animationContainer, BorderLayout.NORTH);
    this.add(this.controlPanel, BorderLayout.SOUTH);
    super.build();
  }

  private void setLayout(GroupLayout layout) {
    layout.setAutoCreateContainerGaps(true);
    layout.setAutoCreateGaps(true);
    this.setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup()
            .addGroup(layout.createSequentialGroup()
                .addComponent(this.animationContainer)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(this.controlPanel));
    layout.setVerticalGroup(
        layout.createSequentialGroup()
            .addComponent(this.animationContainer)
            .addGap(0, 0, Short.MAX_VALUE)
            .addComponent(this.controlPanel));
  }

  public int getSpeed() {
    return this.controlPanel.getSpeed();
  }


  public String getSymbolName() {
    return this.controlPanel.getSymbolName();
  }


  public SymbolType getSymbolType() {
    return this.controlPanel.getSymbolType();
  }


  public Point getFramePoint() {
    return this.controlPanel.getFramePoint();
  }


  public Dimension getFrameDimension() {
    return this.controlPanel.getFrameDimension();
  }


  public Color getFrameColor() {
    return this.controlPanel.getFrameColor();
  }


  public int getFrameNum() {
    return this.controlPanel.getFrameNum();
  }


  public int getGotoFrame() {
    return this.controlPanel.getGotoFrame();
  }

}
