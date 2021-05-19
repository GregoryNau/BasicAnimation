package cs3500.animator.controller.iterator;

import cs3500.animator.model.symbol.SymbolType;
import cs3500.animator.view.visual.Snapshot;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

class SnapshotImpl implements Snapshot {

  // because I am creative with names.

  private final Point point;
  private final Color color;
  private final Dimension size;
  private final SymbolType type;

  /**
   * Constructs this Snapshot.
   *
   * @param point the position of the shape.
   * @param color the color of the shape.
   * @param size the dimension of the shape.
   * @param type the type of the shape.
   */
  SnapshotImpl(Point point, Color color, Dimension size, SymbolType type) {
    this.point = point;
    this.color = color;
    this.size = size;
    this.type = type;
  }

  @Override
  public Point point() {
    return this.point;
  }

  @Override
  public Color color() {
    return this.color;
  }

  @Override
  public Dimension size() {
    return this.size;
  }

  @Override
  public SymbolType type() {
    return this.type;
  }

}
