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
    public BST insert(int e) { throw new UnsupportedOperationException("Method not implemented"); }

    @Override
    public boolean search(int e) {
        return false;
    }

    @Override
    public int countNodes() {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public int height() {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public BST remove(int e) {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public BST merge(BST that) {
        throw new UnsupportedOperationException("Method not implemented");
    }
}
