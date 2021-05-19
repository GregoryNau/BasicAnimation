package tools;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import org.jetbrains.annotations.NotNull;

public interface IteratorTools {

  /**
   * Reverses all the elements in an Iterator and returns an Iterator of the reverse.
   * @param a the initial Iterator.
   * @param <A> the type in Iterator.
   * @return A reversed Iterator.
   */
  @NotNull
  static <A> Iterator<A> reverse(@NotNull Iterator<A> a) {
    Stack<A> rev = new Stack<>();
    while (a.hasNext()) {
      rev.push(a.next());
    }
    return rev.iterator();
  }

  /**
   * Returns a List of all the elements in the passed Iterator.
   * @param a the Iterator.
   * @param <A> the param type of Iterator.
   * @return a List with each element in order from Iterator.
   */
  static <A> List<A> makeList(@NotNull Iterator<A> a) {
    List list = new ArrayList();
    while (a.hasNext()) {
      list.add(a.next());
    }
    return list;
  }
}
