package tools.equality;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import org.jetbrains.annotations.NotNull;

/**
 * Provides tools for testing equality with objects.
 * <p>Uses a Map of keys and values to test for equality.</p>
 * <p>The key and value must be value equals, but not reference equals.</p>
 * <p>Call the testValEquals method to test for assertEquals
 * on each {@link Entry} in {@link Map}.</p>
 * <p>Call the testRefEquals method to test for assertNotEquals
 * on each {@link Entry} in {@link Map}.</p>
 */
public abstract class EqualityTools {

  /**
   * An enumeration representing the two types of equality we may test.
   */
  private enum Equality { VALUE, REFERENCE }

  private static void assertNotEquals(Object a, Object b) {
    assertThat(a, is(not(b)));
  }

  /**
   * Runs all necessary equality tests on a Map, given the type of equality to test.
   * @param list a Map to test equality with.
   * @param type Equality representing the type of equality to test.
   * @param <A> the key type in Map.
   * @param <B> the value type in Map.
   */
  private static <A, B> void testEquals(@NotNull Map<A, B> list, @NotNull Equality type) {
    testUnique(list);
    testEqualsSelf(list);
    switch (type) {
      case VALUE:
        testValueEquality(list);
        break;
      case REFERENCE:
        testReferenceEquality(list);
        break;
      default:
        throw new IllegalArgumentException("Not a recognized type of Equality.");
    }
  }

  /**
   * Tests value equality on a Map.
   * <p>Key and Value should be value equals to each other but not aliasing each other.</p>
   * <p>Will treat {@link A}'s and {@link B}'s equals method to pass when they are not an alias.</p>
   * @param list Map of values to be tested for equality.
   * @param <A> the key type in Map.
   * @param <B> the value type in Map.
   */
  public static <A, B> void testValEquals(Map<A, B> list) {
    testEquals(list, Equality.VALUE);
  }

  /**
   * Tests reference equality on a Map.
   * <p>Key and Value should be value equals to each other but not aliasing each other.</p>
   * <p>Will treat {@link A}'s and {@link B}'s equals method as only passing if two objects
   * are an alias of each other.</p>
   * @param list Map of values to be tested for equality.
   * @param <A> the key type in Map.
   * @param <B> the value type in Map.
   */
  public static <A, B> void testRefEquals(Map<A, B> list) {
    testEquals(list, Equality.REFERENCE);
  }

  /**
   * A {@link Consumer} object that is constructed with a {@link BiConsumer}.
   * @param <A> the key type in {@link Entry}.
   * @param <B> the value type in {@link Entry}.
   */
  private static class MyConsumer<A, B> implements Consumer<Entry<A, B>> {

    /**
     * The test that will be done on the {@link A} and {@link B} in {@link Entry}.
     */
    private final BiConsumer<A, B> test;

    /**
     * Constructs this {@link MyConsumer}.
     * @param test the test that will be done on the {@link A} and {@link B} in {@link Entry}.
     */
    private MyConsumer(BiConsumer<A, B> test) {
      this.test = test;
    }

    @Override
    public void accept(Entry<A, B> entry) {
      A key = entry.getKey();
      B value = entry.getValue();
      test.accept(key, value);
    }
  }

  /**
   * Tests value equality from each part of each {@link Entry} in Map.
   * <p>Tests that they both equal each other but aren't aliasing each other.</p>
   * @param list the list of values to test.
   * @param <A> the key type in Map.
   * @param <B> the value type in Map.
   */
  private static <A, B> void testValueEquality(Map<A, B> list) {
    BiConsumer<A, B> test = (key, value) -> {
      assertNotSame(key, value);
      assertEquals(key, value);
      assertEquals(key.hashCode(), value.hashCode());
    };
    testHelp(list, new MyConsumer<>(test));
  }

  /**
   * Tests that each {@link A} doesn't equal its {@link B} in the Map.
   * <p>Tests that they also aren't aliasing each other.</p>
   * @param list the Map of {@link A} and {@link B} to test.
   * @param <A> the key type in Map.
   * @param <B> the value type in Map.
   */
  private static <A, B> void testReferenceEquality(Map<A, B> list) {
    BiConsumer<A, B> test = (key, value) -> {
      assertNotSame(key, value);
      assertNotEquals(key, value);
      assertNotEquals(key.hashCode(), value.hashCode());
    };
    testHelp(list, new MyConsumer<>(test));
  }

  /**
   * Tests that all values ({@link B}) in Map do not equal any other.
   * <p>Tests that all keys ({@link A}) in Map do not equal any other.</p>
   * @param list the Map to test.
   * @param <A> the key type in Map.
   * @param <B> the value type in Map.
   */
  private static <A, B> void testUnique(Map<A, B> list) {
    Object[] allA = list.keySet().toArray();
    Object[] allB = list.values().toArray();
    for (int a = 0; a < allA.length - 1; a++) {
      for (int aa = a + 1; aa < allA.length; aa++) {
        assertNotEquals(allA[a], allA[aa]);
      }
    }
    for (int b = 0; b < allB.length - 1; b++) {
      for (int bb = b + 1; bb < allB.length; bb++) {
        assertNotEquals(allB[b], allB[bb]);
      }
    }
  }

  /**
   * Tests that each {@link A} in Map equals itself.
   * <p>Tests that each {@link B} in Map equals itself.</p>
   * @param list a Map of to test equality.
   * @param <A> the key type in Map.
   * @param <B> the value type in Map.
   */
  private static <A, B> void testEqualsSelf(Map<A, B> list) {
    BiConsumer<A, B> test = (key, value) -> {
      assertEquals(key, key);
      assertEquals(value, value);
      assertEquals(key.hashCode(), key.hashCode());
      assertEquals(value.hashCode(), value.hashCode());
    };
    testHelp(list, new MyConsumer<>(test));
  }

  /**
   * Does the for each loop for Map.
   * @param list the Map of type {@link A} and {@link B}.
   * @param test the Consumer to apply to Map.
   * @param <A> the key type in Map.
   * @param <B> the value type in Map.
   */
  private static <A, B> void testHelp(Map<A, B> list, Consumer<Entry<A, B>> test) {
    Iterator<Entry<A, B>> entries = list.entrySet().iterator();
    entries.forEachRemaining(test);
  }

  /**
   * Creates a {@link Map} of given varargs of type {@link A}.
   * <p>Must be an even number of arguments.</p>
   * <p>The first argument is the key to the second argument in the Map.</p>
   * <p>This pattern continues until completion.</p>
   * @param a all {@link A} to compare equality with.
   * @param <A> the type of {@link Object}s being passed.
   * @return the created Map.
   * @throws IllegalArgumentException if a.length is not even.
   */
  @SafeVarargs
  public static <A> Map<A, A> makeMap(A ... a)
      throws IllegalArgumentException {
    if (a == null) {
      throw new IllegalArgumentException("EqualityTools.makeMap cannot take a null argument.");
    } else if (a.length % 2 != 0) {
      throw new IllegalArgumentException("EqualityTools.makeMap must take an even number of args");
    }
    Map<A, A> map = new HashMap<>();
    for (int i = 0; i < a.length; i++) {
      map.put(a[i], a[i + 1]);
      i++;
    }
    return map;
  }
}
