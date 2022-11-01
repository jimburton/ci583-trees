package ci583.trees;

import java.util.Optional;
import static ci583.trees.BST.ifElseIfElse;

public class Branch<T extends Comparable<T>> extends BST<T> {
    private final Optional<BST<T>> left;
    private final Optional<BST<T>> right;

    public Branch(T label, Optional<BST<T>> left, Optional<BST<T>> right) {
        super(label);
        this.left = left;
        this.right = right;
    }

    public Optional<BST<T>> getLeft() {
        return left;
    }

    public Optional<BST<T>> getRight() {
        return right;
    }

    // Exercises
    @Override
    public BST<T> insert(T e) {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public boolean search(T e) {
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
    public Optional<BST<T>> remove(T e) {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public BST<T> merge(Optional<BST<T>> that) {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public String toString() {
        String l = left.map(lt -> lt + " ").orElse("");
        String r = right.map(rt -> " " + rt).orElse("");
        return l + label + r;
    }
}
