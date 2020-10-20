# CI583: Binary search trees

This exercise is about working with *binary search trees* (BSTs). A binary tree is one in which
all nodes have at most two children, and a search tree is one in which the label of parent
nodes is always *less than* the label of the left-hand child, and *greater than* the label of
the right-hand child (if these children exist). The labels of the nodes can be
any data for which "less than" and "greater than" makes sense. 
Refer to the slides from Week 3 for a
refreshere on how trees work and the terminology around them.

Our BST has `int` data in the labels of nodes. The implementation uses *inheritance* and
*recursion*. In the package `ci583.trees` is the *superclass* of all tree nodes, `BST`. This
class is defined as an `abstract` class, which means that we will have to make *subclasses*
of it in order to construct trees. These subclasses are `Branch` and `Leaf`, the two types
of node that make up our trees. `BST` declares the methods that every tree node must 
implement, but the actual code for the methods goes into the subclasses. This is so that 
`Branch` and `Leaf` can each provide their own implementation, as it will mean something
different to, for instance, count the nodes in a branch node than in a leaf.  
 
 Test  your  work  by running the unit tests  in  the package `ci583.test`. 
         
1.  Implement the `insert` methods. Note that you have to do this twice, once for 
 branch nodes and once for leaf nodes. Each `insert` method should return a new `BST`. 
 
     As the first case, consider the case of inserting to a leaf node.
      There are no duplicates in our trees -- if the new data is equal to the label of 
     the leaf, just return the current leaf object (`this`). Otherwise, you will need to 
     construct and return a new branch node. If the new
 data to be inserted is greater than the label of the existing leaf, make the new data
 the label of a new branch node and the existing leaf its left-hand child. The 
 right-hand child will be `null`. If the label of the leaf is greater than the new data
 to be inserted, make the label of the current leaf (`this.label`) the label of the new 
 branch and put the inserted data into a new `Leaf` object that will become the left-hand 
 child.
  
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
      you need to "go left". 
      If there
      is no left-hand child to follow return a new branch node with the 
      same label as the current one and the same right-hand child, but where the left-hand child
      is replaced with a new leaf node containing the data to be inserted. If there
      *is* a left-hand child to follow, return a new branch node with the same label and
      right-hand child but where the left-hand child is the tree that results from
      calling `left.insert(e)`. 
      - If the new data to be inserted is greater than
      the label of the current branch node, you need to "go right", with 
      the same sort of logic as the left-hand case.
   
2. Implement the `search` method -- again, this needs to be done in both the `Leaf` and
  `Branch` classes. The case for the leaf node is simple -- if the value you are searching for 
  is equal to the label, return `true`; otherwise, the value is not in this tree so 
  return `false`.
  
      For the branch case, if the value you are searching for is equal to the label, 
      return
      `true`. If the value is *less than* the label, then you need to go left: here there are two 
      possibilities: the left-hand child is `null`, so the value is definitely not in the
        the tree and you should return `false`, or the left-hand child exists. In this case the value might be 
        down there somewhere, so your method should return the result of calling `left.search(e)`. If the
        value is *greater than* the label, the logic is the same except you need to go right.
         
3.  Implement `countNodes` and `height`.  Counting nodes is easy – calling it on a leaf 
  should give 1, and calling it on a branch gives 1 plus the number of nodes in the left-hand
  child (if it exists) plus the number of nodes in the right-hand child (if it exists).
    
      Similarly, the height of a leaf node is 0, and the height 
  of a branch is 1 plus the maximum of the heights of the 
  left and right branches (if they exist). Use `Math.max` to find the largest of two 
  numbers.
 
4. Implement `merge`.  When `merge` is called on a leaf node it should first compare
the label of the tree to be merged with the current label. If the labels are equal 
then simply return `this`. If the current label is
greater, return a new branch node with the same label as the current node and
with the tree to be merged as the left-hand child. If the current label is less than 
the label of the tree to be merged, make `that` the right-hand child.

    When `merge` is called on a branch node, you should again begin by comparing the
labels.  If they are equal, return `this`. If the label of `this` is 
greater return a new branch node with the same label and right-hand child as `this` but
where the left-hand child is the tree that results from calling `merge` on 
`this.left`. If there is no left-hand child, simply supply `that`. If the label of 
`this` is less than the label of `that`, merge `that`
with the right-hand child in the same way. Note that this method makes no attempt to keep our 
trees balanced.

5. Finally, implement `remove`. On branch nodes there are again several possibilities: 
        
    - the first is that the label of this node is equal to the value that needs to be 
removed, in which case return either the tree that is the result of calling 
`left.merge(right)` if `left` is not `null`, or simply `right` if `left` is 
`null`. 
    - secondly, the label of this node may be greater than the value
to be removed, in which case you want to "go left". If you can't go left because 
there is no left-hand child then the value to be removed isn't in this tree and you can 
and you can return `this`. If you can go left, return a new branch with 
the same label and right-hand child as the current one but where the 
left-hand child is the tree that results from the recursive call to `remove` 
on the current left-hand child; 
    - finally, the label of this node may be less than the value to be removed, 
        in which case "go right".
 
