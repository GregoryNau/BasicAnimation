package tools;

import java.util.List;
import java.util.Vector;
import java.util.function.Function;

/**
 * Static methods for convenient use on Lists.
 */
public interface ListTools {

  /**
   * Reverses the order of a given {@link List}.
   * @param a the given list.
   * @param <A> the type of Object in given list.
   * @return a List of the same type with the same objects, but in reversed order.
   */
  static <A> List<A> reverse(List<A> a) {
    List<A> r = new Vector<>();
    for (int i = a.size() - 1; i >= 0; i--) {
      r.add(a.get(i));
    }
    return r;
  }

  /**
   * Transforms a {@link List} of Type {@link A}, into a List of Type {@link B}.
   * @param a a List of Type A.
   * @param f a {@link Function} that will be applied to each element in given list.
   * @param <A> The Object type in the current list.
   * @param <B> The Object type in the new list.
   * @return A new list of the results of given Function on each element in given list.
   * <i>Will be in the same order as supplied.</i>
   */
  static <A, B> List<B> transform(List<A> a, Function<A, B> f) {
    List<B> b = new Vector<>();
    for (A e : a) {
      b.add(f.apply(e));
    }
    return b;
  }
}
