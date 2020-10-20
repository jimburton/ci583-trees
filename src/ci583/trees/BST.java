package ci583.trees;

/*
 * This is an ADT for a Binary Search Tree which contains ints as data. Implement concrete versions of the methods
 * below in the two subclasses of BST, which are Branch and Leaf.
 */
public abstract class BST {
    protected int label;

    /* Getter for the label */
    public int getLabel() {
        return label;
    }

    /*
     * Return the number of nodes in the BST.
     */
    abstract public int countNodes();
    /*
     * Return the height of the BST.
     */
    abstract public int height();
    /*
     * Return a String representation of the BST. This should a String containing the labels
     * in order. Use an inorder traversal to generate the string.
     */
    abstract public String toString();
    /*
     * Return a new BST which is the result of inserting the element e to the current BST.
     * You need to maintain the BST conditions by inserting the element at the right location.
     */
    abstract public BST insert(int e);
    /*
     * Return a BST which is the result of removing the element e from the current BST.
     */
    abstract public BST remove(int e);

    abstract protected BST merge(BST that);
}

