package ci583.trees;

import java.util.Optional;

public class Branch extends BST {
    private final Optional<BST> left;
    private final Optional<BST> right;

    public Branch(int label, BST left, BST right) {
        super(label);
        this.left = Optional.of(left);
        this.right = Optional.of(right);
    }

    public Optional<BST> getLeft() {
        return left;
    }

    public Optional<BST> getRight() {
        return right;
    }

    // Exercises
    @Override
    public BST insert(int e) {
        throw new UnsupportedOperationException("Method not implemented");
    }

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

    @Override
    public String toString() {
        String l = (left == null) ? "" : left.toString() + " ";
        String r = (right == null) ? "" : " " + right.toString();
        return l + label + r;
    }
}
