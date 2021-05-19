BasicAnimation
CS3500 Homework 6
By: Gregory Nau and Eric Fernandez


/src

+cs3500.animator.model

Animator Interface
- Animator is an interface for creating an animation.
- It takes in symbols which have been set with keyframes
- The Animator returns a string of the motion description of all shapes added

*Added from HW5
ImmAnimator
- An Immutable Animator class, for use in the view.
- This implementations of this interface should mutate keyframes or symbols.
- Use Animator for full functionality.


BasicAnimator Class
- BasicAnimator is an implementation of the Animator class
- Stores the added symbols in a map symbolMap.
- Prints the motion description

BasicImmAnimator
- This class is an implementation of the ImmAnimator interface.
- This implements the interface using composition, storing an Animator.
- This class does not mutate the symbols or keyframes in the stored Animator.

Canvas Interface
- An interface to position and dimension information on a canvas.

CanvasImpl
- Implementation of the canvas class.
- Stores position and dimension data.


+Model.Color *REMOVED* Replaced by using the built java color

+Model.Dimension *REMOVED* Replaced by using the built in java Dimension

+Model.Posn *REMOVED* Replaced by using the built in java Posn

+model.symbol.frame

Frame *Added*
- Represents a standard frame: a snapshot of a motion.

RotatableFrame *Added*
- Represents a {@link Frame} with the ability to rotate.
- Negative rotation means counter clockwise rotational movement.
- rotation > 360 means multiple spins of rotational movement.

+model.symbol.published *Added*

PublishedSymbol
- Represents an immutable Symbol
- Optimized for speed of getFrame

+model.symbol.shapes

Ellipse
-Represents an Elliptical shape.

Rectangle
- Represents a Rectangular shape.

+model.symbol
KeyframeList
- Represents a type of Symbol that will use a TreeMap of
- RotatableFrame to keep track of its motion.

Symbol Interface
- Represents a symbol (a single unit that moves together) in an animation.
- A Symbol will assume continuous movement between keyframes. When constructed
  there are no keyframes. If there are no keyframes, Symbol will stay at its default
  values and not move. If there is a single keyframe, the Symbol will stay at its
  keyframe value and not move. Two keyframes are required to start change.
  If the Symbol has a keyframe that represents the symbol at Posn(0, 0) for tick 1.
  And the Symbol has a keyframe that represents the Symbol at Posn(10, 10) for tick 3.
  goTo(2) will transform this Symbol so that its position is at Posn(5, 5).</p>

+model.symbol.tween *ADDED*
LinearTween
-Represents a motion which has a constant rate of change.

TweenInterface
-Represents a motion.

+view.visual.animationpanel
AnimationPanel
- Represents the {@link JPanel} that the animation will play on.
- Overrides removeAll, and paintComponent, to make animating
  AbstractComponents much more efficient.
- Will still support other Components just like JPanel, but will
  deffer to JPanel to handel those.

+view.visual.components
AbstractComponent
- Exists to paint a specific shape or set of shapes onto Graphics.

EllipseComponent
- Represents a ellipse shape.

RectangleComponent
- Represents a rectangle shape.

+view.visual
IteratorAnimation
- Represents an Animation to work similarly to an Iterator.
 * Invariants:
  - Frame will not change unless nextFrame is called. Then it will only iterate up once.
  - Will not skip any Shapes. They are given back-most first, front-most last.

Snapshot Interface
- Represents a state of a shape for this view.

SnapshotDrawer
- Takes Snapshots to draw a scene on its own canvas.

VisualAnimatorView
- Represents an entire visual view of an animation.

+view
AnimatorView Interface
- The interface for the animator view.
- This is the most generic view, and can be subclassed to add more functionality.

SVGAnimatorView
- This Animation view generates an SVG file.
- SVGAnimatorView is an implementation of AnimatorView.
- Creates an svg file with rects and ellipses.
- Sets the origin of the svg to the top left corner by shifting by the model's canvas posn.

TextAnimatorView
- This is an implementation of the AnimatorView for printing the animation to text.
- Prints the location and dimension of the canvas plus the motion description of the imm model.

ViewBuilder
- ViewBuilder builds a AnimatorView according to the set params.
- Type and Model must be set before the animator should be built.
- Otherwise an error will be thrown because not enough args were set.

ViewBuilderImpl
- Implements ViewBuilder, works with the text, svg and visual views.


+cs3500.animator

Excellence
- The main file.


/test

+Model.Animator

BasicAnimatorTest
- Test of the BasicAnimator class
- Tests adding symbols to the animator, and returning the motion description
- Integration test of the Rectangle and Ellipse classes to print excactly the desired output
  from the assignment
- Verifies invariants

SymbolDummy Class
- SymbolDummy is a dummy implementation of Symbol which generates simple data to test.

+Model.Color
ColorRGBTest
- Tests that it is throwing errors when constructed with invalid arguments.
- Tests that the get/set methods don't return/save the reference to passed object.
- Tests that equals and hashCode work as expected.
- Tests blend.

+Model.Dimension
DimensionWHTest
- Tests that it is throwing errors when constructed with invalid arguments.
- Tests that the get/set methods don't return/save the reference to passed object.
- Tests that equals and hashCode work as expected.

+Model.Posn
Posn2DTest
- Tests that the get/set methods don't return/save the reference to passed object.
- Tests that equals and hashCode work as expected.

+Model.Symbol
EllipseTest
- Tests that the copy method returns a Symbol that describes the same motion, but is not
  referring to any of the same objects and is not an alias of itself.
- Tests that getType returns a string representing the type of shape an Ellipse is.

KeyframeListTest
- Tests all the functionality of a Symbol.
- Tests that the constructor doesn't save a reference of passed objects.
- Tests that the get methods don't return a reference of saved objects.
- Tests the goTo when landing on a keyFrame
- Tests the goTo when landing before the first keyFrame and after the last keyFrame
- Tests that goTo extrapolates a linear motion between two keyFrames.
- Tests that copy returns a Symbol that describes the same motion.
- Tests that copy returns a Symbol with the correct instance type.

KeyframeTest
- Tests that Keyframe returns an equal value to what it was constructed with.

RectangleTest
- Tests that the copy method returns a Symbol that describes the same motion.
- Tests that the copy method returns a Symbol that is not aliasing the original Symbol.
- Tests that getType returns a string representing the type of shape a Rectangle is.