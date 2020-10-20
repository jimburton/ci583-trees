# Binary search trees


CI284Data Structures and AlgorithmsLab Session 5Download the fileci284-lab5.zipand extract
 the Eclipse project it con-tains.   Load  the  project  into  Eclipse  
 usingFile / Import / Import ExistingProject into Workspace.
   In the packageci284.treesare several classes.
     Theabstract classBSTis the abstract data type for a binary 
     search tree.  Note theuse of generics in the definition ofBST
     :public  abstract  class BST <T extends  Comparable <T>>
     This means that ourBSTcan be used to create trees of any
      type,T, so longasTextendsComparable.  This makes sense
       since the only kind of data we canstore in a binary 
       search tree is that which can be compared and ordered.
       Complete  a  functional  implementation  of  the  BST  
       by  implementing  themethods  in  
       theBranchNodeandLeafNodeclasses.   Test  your  work  by  
       run-ning  the  unit  test  in  the  
       packageci284.trees.test.   To  test  equality,  
       usecompareTo– this methods returns a negative integer 
       if the first object is lessthan the second, 0 if they
        are equal, and a positive integer if the first object
         isgreater than the second.
         
   1.  Implement theinsertmethods.  These need to maintain the 
   conditionsof the BST, which are that, for each branch 
   nodenwith labelk, the labelof the root of the left 
   child ofnis less thankand the label of the root ofthe 
   right child ofnis greater thank.  To insert a new value,v, 
   into a BST,you start at the root and compare its label 
   tov.  Ifvis less than the label,follow the left branch.  
   Otherwise, follow the right branch.  In either case,if 
   the branch you want to follow doesn’t exist, create a new
    leaf withvasthe label and set that as the branch.  
    If you drill down as far as a leaf,l,in this way, 
    return a new branch node.  The label of the new branch 
    canbevor the label ofland the remaining value should be 
    used to create aleaf node which will become the left or 
    right child of the new branch.
  
  2.  Implement thecountNodesandheightmethods.  Counting 
  nodes is easy– calling it on a leaf gives 1, and calling 
  it on a branch gives 1 plus thenumber of nodes in the left 
  branch, plus the number of nodes in the rightbranch.  
  Similarly, the height of a leaf node is 0, and the height 
  of a branchis 1, plus the maximum of the heights of the 
  left and right branches.
  
3.  Implement thetoStringmethods so thay they produce a String
 whichcontains all labels from the tree in ascending order, 
 separated by spaces.You can do this using aninorder 
 traversal.  You need to do the followingat each node,
 n:(a)  traverse the left branch ofn,(b)  
 visitnitself, and(c)  traverse the right branch ofn.
 
 4.  Implement theremoveandmergemethods.  This is the trickiest 
 part ofthe implementation.  Removing an elementefrom a leaf 
 node will eitherreturnnull(ifeis  equal  to  the  label  
 of  the  node),  or  return  the  nodeunchanged.  If we are 
 removingefrom a branch node andeis not equal tothe label of 
 the branch, we return a new branch node with the same 
 labelas the current node and with left and right children 
 that are the resultof callingremove(e)recursively.  
 Ifeis equal to the label of the node,n,there are several 
 cases:(a)nhas one branch:  return the branch which is not 
 null.(b)nhas two branches:  return the result ofmergingthe 
 left and rightbranches.  To merge two leaf nodes, create a 
 new branch node.  Tomerge a leaf node with a branch node, 
 use yourinsertmethod toinsert  the  label  of  the  leaf  
 into  the  branch.   To  merge  one  branchnode,b1, with 
 another,b2, insert the label ofb1intob2then mergethe left 
 and right children ofb1, givingb3.  Finally, mergeb3withb2.