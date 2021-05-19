package cs3500.animator.model.animator;

import static org.junit.Assert.assertEquals;

import java.awt.Dimension;
import java.awt.Point;
import org.junit.Test;

public class CanvasImplTest {

  //TODO: Should canvas return an error if below zero?

  @Test
  public void testConstructionGetAndPrint() {
    Canvas c = new CanvasImpl(1, 2, 3, 4);
    Canvas c2 = new CanvasImpl(new Point(1, 2), new Dimension(3, 4));
    assertEquals(1, c.getPoint().x);
    assertEquals(2, c.getPoint().y);
    assertEquals(3, c.getDimension().width);
    assertEquals(4, c.getDimension().height);
    assertEquals("canvas 1 2 3 4", c.toString());


    assertEquals(1, c2.getPoint().x);
    assertEquals(2, c2.getPoint().y);
    assertEquals(3, c2.getDimension().width);
    assertEquals(4, c2.getDimension().height);
    assertEquals("canvas 1 2 3 4", c2.toString());
  }

}