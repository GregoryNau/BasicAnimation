package cs3500.animator.view.visual.form_controls.controls;

import cs3500.animator.view.visual.views.gui.AbstractGUIAnimator.ControlListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.JTextField;

public final class NumericalInputControl extends JPanel implements KeyListener {

  private final JTextField textField;
  private final ControlListener listener;

  public NumericalInputControl(ControlListener listener, int initial) {
    super();
    this.listener = listener;
    this.textField = new JTextField(Integer.toString(initial));
    this.add(this.textField, CENTER_ALIGNMENT);
    this.textField.addKeyListener(this);

    // text box
    this.textField.setMinimumSize(new Dimension(20, 20));
    this.textField.setPreferredSize(new Dimension(50, 25));
    this.textField.setMaximumSize(new Dimension(100, 30));

    // font
    this.textField.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 20));

    // outline
    this.setBackground(Color.BLACK);
  }

  public int speed() {
    return new Integer(this.textField.getText());
  }


  @Override
  public void keyTyped(KeyEvent e) {
    if (!Character.toString(e.getKeyChar()).matches("^[0-9]$")
        && e.getKeyChar() != KeyEvent.VK_DELETE
        && e.getKeyChar() != KeyEvent.VK_BACK_SPACE
        && e.getKeyChar() != KeyEvent.VK_ENTER
        && e.getKeyChar() != KeyEvent.VK_ESCAPE) {
      e.consume();
    }
  }

  @Override
  public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
      this.listener.trigger();
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    // not interested in responding.
  }
}
