package ci583.trees;

import java.util.Optional;
import static ci583.trees.BST.ifElseIfElse;

public class Branch extends BST {
    private final Optional<BST> left;
    private final Optional<BST> right;

    public Branch(int label, Optional<BST> left, Optional<BST> right) {
        super(label);
        this.left = left;
        this.right = right;
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
        throw new UnsupportedOperationException("Method not implemented");
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
    public Optional<BST> remove(int e) {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public BST merge(Optional<BST> that) {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public String toString() {
        String l = left.map(lt -> lt.toString() + " ").orElse("");
        String r = right.map(rt -> " " + rt.toString()).orElse("");
        return l + label + r;
    }
}
