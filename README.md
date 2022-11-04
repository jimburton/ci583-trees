# CI583: Generic binary search trees with `Optional` children

This exercise revisits a problem you've (hopefully) already attempted. It is about programming 
recursively with binary search trees, but the implementation is improved in various ways. The 
first difference is that this implementation uses Java *generics* to make a tree that can hold any 
kind of comparable data in its nodes. Instead of constructing a tree with the type `BST` that can 
only hold `int` data, we construct one of type `BST<T>` where `T` is any class that extends `Comparable`. 
To achieve this the definition of the tree classes, `BST`, `Branch` and `Leaf`, are changed to include a 
*type parameter*. They now look like this:

```java
public abstract class BST <T extends Comparable<T>> {
     // etc..    
public class Branch<T extends Comparable<T>> extends BST<T> {
     // etc...
public class Leaf <T extends Comparable<T>> extends BST<T> {
     // etc
```
Whenever we had a reference to  labels in the previous implementation, the
type of the labels is changed to `T`. The `Comparable` class is the class that implements the 
`compareTo` method, allowing us to order its values, saying that one is *less than*, *equal to* or 
*greater than* another. Almost all standard classes in Java extend `Comparable`. Now when we want to 
compare labels, we need to use the `equals` and `compareTo` methods instead of `==`, `<` and `>`. Calling 
`o1.compareTo(o2)` returns 0 if `o1` and `o2` are equal, less than 0 if `o1` is less than `o2` and more 
than 0 if `o1` is greater than `o2`.

Secondly, this implementation uses the [`Optional`](https://docs.oracle.com/javase/8/docs/api/java/util/Optional.html)
class to represent values that may or may not be present. In the original exercise there were 
many places where you as the programmer had to return `null` from a method or write a lot of checks
to see whether a tree you were trying to do something with was in fact `null`. This was necessary 
because there are some cases where there really is no meaningful value you can return. Consider the 
case of removing a node from a tree and returning the result tree -- if the original tree consists 
of one leaf node, what should you return? In the previous exercise, the answer was `null`.

That design requires us to test for null pointers all over the place and at some point we,
or someone else who is using our code, is going to forget to do that and get a
`NullPointerException`. The best practice for representing a value of type `T` that may or
may not exist is not to use `null` but to use `Optional<T>`, where the `Optional` type is a
wrapper around a value of type `T` or nothing. We can put a value, `x`, "inside" an optional 
using `myOpt = Optional.of(x)`, so long as `x` has the right type.  We can access that value 
"inside" the optional using `myOpt.get()`. However, when we want to access an optional value we 
should check whether it really exists: for this we can use `myOpt.isEmpty()` and `myOpt.isPresent()`. 

We often want to do something with the value inside 
an optional *or* use a default value. If we just want to *access* the value or use a default we
can use the `or` method:

```java
Optional<String> opt1 = Optional.of("Hello world!");
Optional<String> opt2 = Optional.empty();

System.out.println(opt1.or("Howdy")); // prints "Hello world!"
System.out.println(opt2.or("Howdy")); // prints "Howdy"
```

If we want to manipulate an optional value rather than just accessing it, *and* use a default
value if it doesn't exist, we can do this by combining two methods: `map` and `orElse`:

```java
Optional<BST<Character>> t1 = Optional.of(new Leaf('*'));
Optional<BST<Integer>> t2 = Optional.empty();
// count the nodes in the tree or zero if there isn't one there
System.out.println(t1.map(t -> t.countNodes()).orElse(0)); // prints 1
System.out.println(t2.map(t -> t.countNodes()).orElse(0)); // prints 0
```

How this works is that `map` takes a lambda argument and applies it to the value inside
the optional, if one exists. If it doesn't exist, the call to `orElse` returns a value with
the same type as the result of the lambda. This kind of code is neater and safer than lots of
`if` statements checking whether values are `null`. 

Start by reading the new versions of the `BST`, `Branch` and `Leaf` classes. Note the use of the 
generic type `T`. In the `Branch` class the left and right children have the type `Optional<BST<T>>`, 
as there may or may not be a child in either position. In the `BST` class two methods are altered to 
now return an `Optional<BST<T>>` or require one as a parameter: `remove` and `merge`.
 
Test  your  work  by running the unit tests  in  the package `ci583.test`. My solutions are in the 
branch called `optional-children-solution`.
         
1. Implement the `insert` methods. Note that you have to do this twice, once for 
   branch nodes and once for leaf nodes. Each `insert` method should return a new `BST`. 
 
   As the first case, consider the case of inserting to a leaf node.
   There are no duplicates in our trees -- if the new data is equal to the label of 
   the leaf, just return the current leaf object (`this`). Otherwise, you will need to 
   construct and return a new branch node. If the new
   data to be inserted is less than the label of the existing leaf, make a Branch node with 
   the same label as the current node and with a new leaf containing the new data as its left-hand child. The 
   right-hand child will be `Optional.empty()`. If the new data is greater than the label, make a Branch node
   with the same label as the current leaf and put the inserted data into a new `Leaf<T>` object that will become 
   the right-hand child.
  
   Inserting to a branch node is where the recursion comes in. The logic
   is that you need to 
   recursively follow the left or right branches 
   of the tree until you reach the right place to put the new value. 
   You will have reached that place when there is no branch to follow (i.e. 
   either you have reached a leaf, the case that was dealt with above, or 
   you are in a branch but the child you want to follow is `null`). 
   These are the cases you need to consider:  
      
   - If the new data to be inserted is
     equal to the label of the current branch node, just return `this` (no duplicates).
   - If the new data is less than the label of the current node, 
     you need to "go left". If there
     is no left-hand child to follow (`left.isEmpty()` is true), return a new branch node with the 
     same label as the current one and the same right-hand child, but where the left-hand child
     is replaced with a new leaf node containing the data to be inserted. If there
     *is* a left-hand child to follow, return a new branch node with the same label and
     right-hand child but where the left-hand child has `e` inserted to it. 
   - If the new data to be inserted is greater than
     the label of the current branch node, you need to "go right", with 
     the same sort of logic as the left-hand case.

   This is where we can make use of the `map ... orElse` pattern. If we want to insert the
   new data on the left we can do so without needing to check whether the left-hand optional
   is empty with this code:

   ```java
   Optional<BST<T>> newLeft = this.getLeft().map(l -> l.insert(e)).orElse(new Leaf<T>(e));
   ```
   
3. Implement the `search` method -- again, this needs to be done in both the `Leaf` and
  `Branch` classes. The case for the leaf node is simple: if the value you are searching for 
   is equal to the label, return `true`; otherwise, the value is not in this tree so 
   return `false`.
  
   For the branch case, if the value you are searching for is equal to the label, 
   return
   `true`. Otherwise, you need to keep searching in either the left or right branch. Use
   the `map ... orElse` pattern to make a recursive call to search if there *is* a child 
   to search in, or return false if not. 
         
4. Implement `countNodes` and `height`.  Counting nodes is easy – calling it on a leaf 
   should give 1, and calling it on a branch gives 1 plus the number of nodes in the left-hand
   child (if it exists) plus the number of nodes in the right-hand child (if it exists).
    
   Similarly, the height of a leaf node is 0, and the height 
   of a branch is 1 plus the maximum of the heights of the 
   left and right branches (if they exist). Use `Math.max` to find the largest of two 
   numbers.
 
5. Implement `merge`.  When merge is called with an argument that is `null`, just return the current tree. 
   When `merge` is called on a leaf node it should first compare
   the label of the tree to be merged with the current label. If the labels are equal 
   then simply return `this`. If the current label is
   greater, return a new branch node with the same label as the current node and
   with the tree to be merged as the left-hand child. If the current label is less than 
   the label of the tree to be merged, make `that` the right-hand child.

   When `merge` is called on a branch node, you need to know whether the tree to be merged
   is a branch or a leaf. Do this using `instanceof`, e.g. `if(that instanceof Leaf)...`. 
   If the tree to be merged is a leaf node, the case is very similar to the previous one of merging 
   into a leaf. If not, consider these three cases: 

   + The labels are equal: merge the children of the tree to be merged and merge that new tree with the current one.
   + The label of `this` is greater: return a new branch node with the same label and right-hand child as `this` but
     where the left-hand child is the tree that results from calling `merge` on 
     `this.left`, or if there is no left-hand child in the current tree, simply supply `that`. 
   + The label of`this` is less than the label of `that`: merge `that`
     with the right-hand child in the same way. 
   
   Note that this method makes no attempt to keep our 
   trees balanced.

6. Implement `remove`. On branch nodes there are again several possibilities: 
        
   - the first is that the label of this node is equal to the value that needs to be 
     removed, in which case return either the tree that is the result of calling 
     `left.merge(right)` if `left` is not empty, or simply `right` if `left` is 
     empty. 
   - secondly, the label of this node may be greater than the value
     to be removed, in which case you want to "go left". If you can't go left because 
     there is no left-hand child then the value to be removed isn't in this tree and you can 
     return `this`. If you can go left, return a new branch with 
     the same label and right-hand child as the current one but where the 
     left-hand child is the tree that results from the recursive call to `remove` 
     on the current left-hand child; 
   - finally, the label of this node may be less than the value to be removed, 
     in which case "go right".

7. There are quite a few places in your code where you will have coded an 
   `if ... else if ... else` structure something like this:

   ```java
   if(p) {
     // evaluate block A
   } else if (q) {
     // evaluate block B
   } else {
     // evaluate block C
   }
   ```
   
   For instance, you will have done this when implementing `search`. Look for other 
   examples in your code.

   Whenever we see this kind of repetition it's reasonable to ask if we can reduce it by
   introducing a new abstraction. Create a function in the `BST` class with this signature:

   ```java
   public static <T> T ifElseIfElse(boolean p, boolean q, T ifP, T ifQ, T elseR)
   ```
   
   and make the body of the function implement the logic described above. Throughout your `Branch` 
   and `Leaf` classes, replace code that has this three-way structure with calls to `ifElseIfElse`.
