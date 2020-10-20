package ci583.trees;

public class Leaf extends BST{
    public Leaf(int label) {
        this.label = label;
    }

    // Exercises

    @Override
    public BST insert(int e) { throw new UnsupportedOperationException("Method not implemented"); }

    @Override
    public String toString() { throw new UnsupportedOperationException("Method not implemented"); }

    @Override
    public int countNodes() { throw new UnsupportedOperationException("Method not implemented"); }

    @Override
    public int height() { throw new UnsupportedOperationException("Method not implemented"); }

    @Override
    public BST remove(int e) { throw new UnsupportedOperationException("Method not implemented"); }

    @Override
    protected BST merge(BST that) { throw new UnsupportedOperationException("Method not implemented"); }
}
