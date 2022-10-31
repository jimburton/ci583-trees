package ci583.trees;

import java.io.PrintStream;
import java.util.Optional;

/*
 * This is an ADT for a Binary Search Tree which contains ints as data.
 */
public abstract class BST {
    protected final int label;

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
    abstract public Optional<BST> remove(int e);

    /* Merge this tree with another */
    abstract public BST merge(Optional<BST> that);

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
            String pointerLeft = (((Branch) root).getRight().isPresent()) ? "├──" : "└──";

            traverseNodes(sb, "", pointerLeft, ((Branch) root).getLeft(), ((Branch) root).getRight() != null);
            traverseNodes(sb, "", pointerRight, ((Branch) root).getRight(), false);
        }
        sb.append("\n");
        return sb.toString();
    }

    public static void traverseNodes(StringBuilder sb, String padding, String pointer, Optional<BST> optNode,
                              boolean hasRightSibling) {
        if (!optNode.isEmpty()) {
            BST node = optNode.get();
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
        if ((this instanceof Leaf && !(o instanceof Leaf))
             || (this instanceof Branch && !(o instanceof Branch))) {
            return false;
        }
        if (this.getLabel() != that.getLabel()) {
            return false;
        }
        if (this instanceof Branch && that instanceof Branch) {
            Branch b = (Branch) this;
            Branch thatBranch = (Branch) that;
            if (b.getLeft().isPresent()
                    && thatBranch.getLeft().isPresent()
                    && !b.getLeft().equals(thatBranch.getLeft())) {
                return false;
            }
            if (b.getRight().isPresent()
                    && thatBranch.getRight().isPresent()
                    && !b.getRight().equals(thatBranch.getRight())) {
                return false;
            }
        }
        return true;
    }

    public static <T> T ifElseIfElse(boolean p, boolean q, T ifP, T ifQ, T elseR) {
        if(p) {
            return ifP;
        } else if(q) {
            return ifQ;
        } else {
            return elseR;
        }
    }
}