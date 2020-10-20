package ci583.trees;

public class Branch extends BST {
    private BST left;
    private BST right;

    public Branch(int label, BST left, BST right) {
        super(label);
        this.left = left;
        this.right = right;
    }

    public BST getLeft() {
        return left;
    }

    public BST getRight() {
        return right;
    }

    // Exercises
    @Override
    public BST insert(int e) {
        if (e == label) {
            return this;
        } else if (e > label) {
            if(right == null) {
                return new Branch(label, left, new Leaf(e));
            } else {
                return new Branch(label, left, right.insert(e));
            }
        } else {
            if(left == null) {
                return new Branch(label, new Leaf(e), right);
            } else {
                return new Branch(label, left.insert(e), right);
            }
        }
    }

    @Override
    public boolean search(int e) {
        if (e == label) {
            return true;
        } else if (e > label) {
            return (right == null) ? false : right.search(e);
        } else {
            return (left == null) ? false : left.search(e);
        }
    }

    @Override
    public String toString() {
        String l = (left == null) ? "" : left.toString() + " ";
        String r = (right == null) ? "" : " " + right.toString();
        return l + label + r;
    }

    @Override
    public int countNodes() {
        return 1 + left.countNodes() + right.countNodes();
    }

    @Override
    public int height() {
        int lh = (left == null) ? 0 : left.height();
        int rh = (right == null) ? 0 : right.height();
        return 1 + Math.max(lh, rh);
    }

    @Override
    public BST remove(int e) {
        if(e == label) {
            // tertiary if-statement -- the expression before the ? is the test, then the statements separated by
            // a comma are the two options.
            return (left == null) ? right : left.merge(right);
        } else if (e < label) {
            BST l = null;
            if (left != null) {
                l = left.remove(e);
            }
            return new Branch (label, l, right);
        } else {
            BST r = null;
            if (right != null) {
                r = right.remove(e);
            }
            return new Branch (label, left, r);
        }
    }

    @Override
    public BST merge(BST that) {
        if (that == null) {
            return this;
        }
        if (label == that.getLabel()) {
            return this;
        } else if(label > that.getLabel()) {
            if (left == null) {
                return new Branch(label, that, right);
            } else {
                return new Branch(label, left.merge(that), right);
            }
        } else if(label < that.getLabel()) {
            if (right == null) {
                return new Branch(label, left, that);
            } else {
                return new Branch(label, left, right.merge(that));
            }
        }
        return null;// we'll never get this far
    }
}
