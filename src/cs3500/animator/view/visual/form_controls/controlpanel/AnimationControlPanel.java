package cs3500.animator.view.visual.form_controls.controlpanel;

import cs3500.animator.model.symbol.SymbolType;
import cs3500.animator.view.visual.RequiresBuild;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;

public class AnimationControlPanel extends JPanel implements RequiresBuild, ActionListener {

  private final Object[] lables;
  private final Object[][] data;
  private final JTable info;
  private final JButton add;
  private final JButton delete;
  private final JButton modify;

  public AnimationControlPanel() {
    super();
    this.setPreferredSize(new Dimension(300, 500));
    this.setBackground(Color.blue);
    this.lables = new Object[]{"name", "x", "y", "width", "height", "red", "green", "blue"};
    //this.data = new Object[15][8];
    this.data = this.build(new Object[15][8]);
    this.info = new JTable(this.data, this.lables) {
      @Override
      public boolean isCellEditable(int row, int column) {
        // all cells are not editable
        return false;
      }
    };
    this.add = new JButton("add");
    this.delete = new JButton("delete");
    this.modify = new JButton("change");
    this.add.addActionListener(this);
    this.delete.addActionListener(this);
    this.modify.addActionListener(this);
  }

  private Object[][] build(Object[][] a) {
    for (int i = 0; i < a.length; i++) {
      for (int u = 0; u < a[i].length; u++) {
        a[i][u] = Integer.toString(i) + " " + Integer.toString(u);
      }
    }
    return a;
  }

  @Override
  public void build() {
    GroupLayout layout = new GroupLayout(this);
    this.setLayout(layout);
    this.setHorizontal(layout);
    this.setVertical(layout);
  }

  private void setHorizontal(GroupLayout layout) {
    layout.setHorizontalGroup(layout.createParallelGroup()
        .addComponent(this.info.getTableHeader()).addComponent(this.info)
        .addGroup(layout.createSequentialGroup().addComponent(this.add)
            .addComponent(this.delete).addComponent(this.modify)));
  }

  private void setVertical(GroupLayout layout) {
    layout.setVerticalGroup(layout.createSequentialGroup()
        .addComponent(this.info.getTableHeader()).addComponent(this.info)
        .addGroup(layout.createParallelGroup().addComponent(this.add)
            .addComponent(this.delete).addComponent(this.modify)));
  }


  public String getSymbolName() {
    return null; // todo
  }


  public SymbolType getSymbolType() {
    return null; // TODO
  }


  public Point getFramePoint() {
    return null; //TODO
  }


  public Dimension getFrameDimension() {
    return null; // todo
  }


  public Color getFrameColor() {
    return null; // todo
  }


  public int getFrameNum() {
    return -1; // todo
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == this.add) {
      // todo
      System.out.println("add");
    } else if (e.getSource() == this.delete) {
      System.out.println("delete");
    } else if (e.getSource() == this.modify) {
      System.out.println("modify");
    }
  }
}
