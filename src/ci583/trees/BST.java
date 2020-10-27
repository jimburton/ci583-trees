package ci583.trees;

import java.io.PrintStream;

/*
 * This is an ADT for a Binary Search Tree which contains ints as data.
 */
public abstract class BST {
    protected int label;

    public BST(int label) {
        this.label = label;
    }

    /* Getter for the label */
    public int getLabel() {
        return label;
    }

    /*
     * Return a new BST which is the result of inserting the element e to the current BST.
     * You need to maintain the BST conditions by inserting the element at the right location.
     */
    abstract public BST insert(int e);
    /*
    Find an element in the tree -- return true if it is found, false otherwise
     */
    abstract public boolean search(int e);
    /*
     * Return the number of nodes in the BST.
     */
    abstract public int countNodes();
    /*
     * Return the height of the BST.
     */
    abstract public int height();
    /*
     * Return a BST which is the result of removing the element e from the current BST.
     */
    abstract public BST remove(int e);

    /* Merge this tree with another */
    abstract public BST merge(BST that);

    /*
    Helper methods for printing trees from https://www.baeldung.com/java-print-binary-tree-diagram
     */
    public static String traversePreOrder(BST root) {

        if (root == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        sb.append(root.getLabel());

        if (root instanceof Branch) {
            String pointerRight = "└──";
            String pointerLeft = (((Branch) root).getRight() != null) ? "├──" : "└──";

            traverseNodes(sb, "", pointerLeft, ((Branch) root).getLeft(), ((Branch) root).getRight() != null);
            traverseNodes(sb, "", pointerRight, ((Branch) root).getRight(), false);
        }
        sb.append("\n");
        return sb.toString();
    }

    public static void traverseNodes(StringBuilder sb, String padding, String pointer, BST node,
                              boolean hasRightSibling) {
        if (node != null) {
            sb.append("\n");
            sb.append(padding);
            sb.append(pointer);
            sb.append(node.getLabel());

            StringBuilder paddingBuilder = new StringBuilder(padding);
            if (hasRightSibling) {
                paddingBuilder.append("│  ");
            } else {
                paddingBuilder.append("   ");
            }

            String paddingForBoth = paddingBuilder.toString();
            String pointerRight = "└──";
            boolean hasRight = !(node instanceof Leaf) && ((Branch) node).getRight() != null;
            String pointerLeft = hasRight ? "├──" : "└──";

            if (!(node instanceof Leaf)) {
                traverseNodes(sb, paddingForBoth, pointerLeft, ((Branch) node).getLeft(), ((Branch) node).getRight() != null);
                traverseNodes(sb, paddingForBoth, pointerRight, ((Branch) node).getRight(), false);
            }
        }
    }

    public static void printTree(PrintStream os, BST tree) {
        os.print(traversePreOrder(tree));
    }

    /*
    Two BST trees are equal if they have the same contents in the same structure.
     */
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof BST)) {
            return false;
        }
        BST that = (BST) o;
        if (this.getLabel() != that.getLabel()) {
            return false;
        }
        if (this instanceof Branch && that instanceof Branch) {
            if (((Branch) this).getLeft() != null && !((Branch) this).getLeft().equals(((Branch) that).getLeft())) {
                return false;
            }
            if (((Branch) this).getRight() != null && !((Branch) this).getRight().equals(((Branch) that).getRight())) {
                return false;
            }
        }
        return true;
    }
}