package cs3500.animator;

import cs3500.animator.animationbuilder.AnimatorBuilder;
import cs3500.animator.controller.AnimatorController;
import cs3500.animator.controller.ControlBuilder;
import cs3500.animator.controller.ControlBuilderImpl;
import java.io.FileReader;
import javax.swing.JOptionPane;
import cs3500.animator.view.util.AnimationReader;

public final class Excellence {

  /**
   * The main method for the cs3500.animator.Excellence Animator.
   * in "name-of-animation-file"
   * view "type-of-view"
   * out"where-output-show-startView"
   * speed "integer-ticks-per-second"
   * @param args the args.
   */
  public static void main(String[] args) {

    ControlBuilder vb = new ControlBuilderImpl();

    // Build the controller
    try {
      if (args.length == 0) {
        args = RunConfigs.getRandom().getRunArgs();
      }
      for (int i = 0; i < args.length; i++) {
        switch (args[i]) {
          case ("-in"):
            vb.setModel(
                //Set the model to an animation reader which reads in the file name
                AnimationReader.parseFile(new FileReader(args[i + 1]), new AnimatorBuilder()));
            i++;
            break;
          case ("-view"):
            vb.setType(args[i + 1]);
            i++;
            break;

          case ("-out"):
            vb.setOutputFile(args[i + 1]);
            i++;
            break;

          case ("-speed"):
            vb.setSpeed(Integer.parseInt(args[i + 1]));
            i++;
            break;

          default:
            throw (new IllegalArgumentException(
                String.format("Could not parse args: %s", args[i])));
        }
      }
    } catch (Exception e) {
      //Throw exceptions to a dialog box.
      JOptionPane.showMessageDialog(null,
          e.getMessage(),
          e.getClass().toString(),
          JOptionPane.ERROR_MESSAGE);
      //Exit with error.
      System.exit(-1);
    }
    AnimatorController c =  vb.build();
    c.startController();
  }
}