package ci583.trees;

public class Leaf extends BST{
    public Leaf(int label) {
        super(label);
    }

    // Exercises

    @Override
    public BST insert(int e) { throw new UnsupportedOperationException("Method not implemented"); }

    @Override
    public boolean search(int e) {
        return false;
    }

    @Override
    public int countNodes() { throw new UnsupportedOperationException("Method not implemented"); }

    @Override
    public int height() { throw new UnsupportedOperationException("Method not implemented"); }

    @Override
    public BST remove(int e) { throw new UnsupportedOperationException("Method not implemented"); }

    @Override
    public BST merge(BST that) { throw new UnsupportedOperationException("Method not implemented"); }
}
