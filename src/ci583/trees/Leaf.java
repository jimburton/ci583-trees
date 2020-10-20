package ci583.trees;

public class Leaf extends BST{

    public Leaf(int label) {
        super(label);
    }

    // Exercises

    @Override
    public BST insert(int e) {
        if (e == label) {
            return this;
        } else if (e > label) {
            return new Branch(e, this, null);
        } else {
            return new Branch(label, new Leaf(e), null);
        }
    }

    @Override
    public boolean search(int e) {
        return (e == label);
    }

    @Override
    public String toString() { return label + ""; }

    @Override
    public int countNodes() { return 1; }

    @Override
    public int height() { return 0; }

    @Override
    public BST remove(int e) {
        // tertiary if-statement -- the expression before the ? is the test, then the statements separated by
        // a comma are the two options.
        return (e ==label) ? null : this;
    }

    @Override
    public BST merge(BST that) {
        if (that == null) {
            return this;
        }
        if (label == that.getLabel()) {
            return this;
        } else if(label > that.getLabel()) {
            return new Branch(label, that, null);
        } else if(label < that.getLabel()) {
            return new Branch(label, null, that);
        }
        return null;// we'll never get this far
    }
}
