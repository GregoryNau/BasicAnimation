package cs3500.animator.model.symbol;

import cs3500.animator.model.symbol.symbol.Symbol;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * An enum of the possible {@link Symbol}s that can be created.
 * <p>More can be added if more are implemented in the future.</p>
 */
public enum SymbolType {
  RECTANGLE, ELLIPSE;

  /**
   * Returns a {@link SymbolType} representing the given string.
   * @param type the string representing a desired shape. Not null.
   * @return a SymbolType representing the desired shape.
   * @throws IllegalArgumentException the string does not map to a known SymbolType.
   */
  @Contract(pure = true)
  public static SymbolType make(@NotNull String type) throws IllegalArgumentException {
    switch (type.toLowerCase()) { // lets me ignore capitalization
      case "rect": return RECTANGLE;
      case "rectangle": return RECTANGLE;
      case "ellipse" : return ELLIPSE;
      default: throw new IllegalArgumentException("Invalid Symbol type: " + type);
    }
  }

  @Override
  public String toString() {
    switch (this) {
      case RECTANGLE: return "rectangle";
      case ELLIPSE: return "ellipse";
      default: return "symbol"; // todo: decide to do this, throw exception, or default to ellipse.
    }
  }
}
