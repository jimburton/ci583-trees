package ci583.trees;

import java.util.Optional;
import static ci583.trees.BST.ifElseIfElse;

public class Leaf <T extends Comparable> extends BST<T> {
    public Leaf(T label) {
        super(label);
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
    public int countNodes() { throw new UnsupportedOperationException("Method not implemented"); }

    @Override
    public int height() { throw new UnsupportedOperationException("Method not implemented"); }

    @Override
    public Optional<BST<T>> remove(T e) { throw new UnsupportedOperationException("Method not implemented"); }

    @Override
    public BST<T> merge(Optional<BST<T>> that) {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public String toString() { return label + ""; }
}
