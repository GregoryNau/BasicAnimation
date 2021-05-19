package cs3500.animator.controller;

import static org.junit.Assert.assertEquals;

import cs3500.animator.model.animator.Animator;
import cs3500.animator.model.animator.BasicAnimator;
import cs3500.animator.model.symbol.SymbolType;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.LinkedList;
import java.util.List;
import org.junit.Test;

public class AnimatorControllerImplTest {
      //case "DeleteKeyframe":
      @Test
      public void deleteKeyframe() {
        Animator ba = new BasicAnimator();

        Animator exp = new BasicAnimator();

        List<String> l = new LinkedList<>();
        l.add("DeleteKeyframe");

        DummyGUIAnimatorImpl gui = new DummyGUIAnimatorImpl(l);

        int frame = 10;
        Point p = new Point(1, 2);
        Color color = new Color(1, 2, 3, 4);
        Dimension d = new Dimension(1, 2);

        gui.name = "R";
        gui.frame = frame;
        gui.point = p;
        gui.color = color;
        gui.dimension = d;
        gui.symbolType = SymbolType.RECTANGLE;

        ba.createSymbol("R", SymbolType.RECTANGLE);
        ba.lockKeyframe("R",
            5,
            new Point(4, 5),
            new Dimension(4, 6),
            color);
        ba.lockKeyframe("R", frame, p, d, color);

        exp.createSymbol("R", SymbolType.RECTANGLE);
        exp.lockKeyframe("R",
            5,
            new Point(4, 5),
            new Dimension(4, 6),
            color);
        exp.lockKeyframe("R", frame, p, d, color);
        exp.unlockKeyframe("R", frame);

        AnimatorController c = new AnimatorControllerImpl(gui, ba);
        c.startController();

        assertEquals(exp.motionDescription(), ba.motionDescription());
      }


      //case "Pause":

      //case "AddKeyframe"
      @Test
      public void addKeyframe() {
        Animator ba = new BasicAnimator();

        Animator exp = new BasicAnimator();

        List<String> l = new LinkedList<>();
        l.add("AddKeyframe");

        DummyGUIAnimatorImpl gui = new DummyGUIAnimatorImpl(l);

        int frame = 10;
        Point p = new Point(1, 2);
        Color color = new Color(1, 2, 3, 4);
        Dimension d = new Dimension(1, 2);

        gui.name = "R";
        gui.frame = frame;
        gui.point = p;
        gui.color = color;
        gui.dimension = d;
        gui.symbolType = SymbolType.RECTANGLE;

        ba.createSymbol("R", SymbolType.RECTANGLE);
        ba.lockKeyframe("R",
            10,
            new Point(4, 5),
            new Dimension(4, 6),
            color);

        exp.createSymbol("R", SymbolType.RECTANGLE);
        exp.lockKeyframe("R",
            10,
            new Point(4, 5),
            new Dimension(4, 6),
            color);
        exp.lockKeyframe("R", frame, p, d, color);

        AnimatorController c = new AnimatorControllerImpl(gui, ba);
        c.startController();

        assertEquals(exp.motionDescription(), ba.motionDescription());
      }

      //case "ModifyKeyframe"
      @Test
      public void modifyKeyframe() {
        Animator ba = new BasicAnimator();

        Animator exp = new BasicAnimator();

        List<String> l = new LinkedList<>();
        l.add("ModifyKeyframe");

        DummyGUIAnimatorImpl gui = new DummyGUIAnimatorImpl(l);

        int frame = 10;
        Point p = new Point(1, 2);
        Color color = new Color(1, 2, 3, 4);
        Dimension d = new Dimension(1, 2);

        gui.name = "R";
        gui.frame = frame;
        gui.point = p;
        gui.color = color;
        gui.dimension = d;
        gui.symbolType = SymbolType.RECTANGLE;

        ba.createSymbol("R", SymbolType.RECTANGLE);
        ba.lockKeyframe("R",
            frame,
            new Point(4, 5),
            new Dimension(4, 6),
            color);

        exp.createSymbol("R", SymbolType.RECTANGLE);
        exp.lockKeyframe("R",
            frame,
            new Point(4, 5),
            new Dimension(4, 6),
            color);
        exp.lockKeyframe("R", frame, p, d, color);

        AnimatorController c = new AnimatorControllerImpl(gui, ba);
        c.startController();

        assertEquals(exp.motionDescription(), ba.motionDescription());
      }

      //case "ChangePlaySpeed"

      //case "Loop"

      //case "GoTo"

      //case "Play"

      //case "Publish"

      //case "Replay"

      //case "AddSymbol"
      @Test
      public void addSymbol() {

        AnimatorControllerImpl aci;
        Animator ba = new BasicAnimator();

        Animator exp = new BasicAnimator();

        List<String> l = new LinkedList<>();
        l.add("AddSymbol");

        DummyGUIAnimatorImpl gui = new DummyGUIAnimatorImpl(l);

        int frame = 10;
        Point p = new Point(1, 2);
        Color color = new Color(1, 2, 3, 4);
        Dimension d = new Dimension(1, 2);

        gui.name = "R";
        gui.frame = frame;
        gui.point = p;
        gui.color = color;
        gui.dimension = d;
        gui.symbolType = SymbolType.RECTANGLE;

        AnimatorController c = new AnimatorControllerImpl(gui, ba);
        exp.createSymbol("R", SymbolType.RECTANGLE);

        c.startController();

        assertEquals(exp.motionDescription(), ba.motionDescription());
      }

      //case "DeleteSymbol"
      @Test
      public void deleteSymbol() {

        AnimatorControllerImpl aci;
        Animator ba = new BasicAnimator();
        Animator exp = new BasicAnimator();

        List<String> l = new LinkedList<>();
        l.add("DeleteSymbol");

        DummyGUIAnimatorImpl gui = new DummyGUIAnimatorImpl(l);

        int frame = 10;
        Point p = new Point(1, 2);
        Color color = new Color(1, 2, 3, 4);
        Dimension d = new Dimension(1, 2);

        gui.name = "R";
        gui.frame = frame;
        gui.point = p;
        gui.color = color;
        gui.dimension = d;
        gui.symbolType = SymbolType.RECTANGLE;

        AnimatorController c = new AnimatorControllerImpl(gui, ba);
        exp.createSymbol("R", SymbolType.RECTANGLE);
        exp.lockKeyframe("R", frame, p, d, color);
        exp.createSymbol("R2", SymbolType.RECTANGLE);
        exp.lockKeyframe("R2", frame, p, d, color);
        exp.removeSymbol("R");

        ba.createSymbol("R", SymbolType.RECTANGLE);
        ba.lockKeyframe("R", frame, p, d, color);
        ba.createSymbol("R2", SymbolType.RECTANGLE);
        ba.lockKeyframe("R2", frame, p, d, color);


        c.startController();

        assertEquals(exp.motionDescription(), ba.motionDescription());
      }



}