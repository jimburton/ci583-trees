package ci583.trees;

import java.util.Optional;
import static ci583.trees.BST.ifElseIfElse;

public class Leaf extends BST{
    public Leaf(int label) {
        super(label);
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
    public int countNodes() { throw new UnsupportedOperationException("Method not implemented"); }

    @Override
    public int height() { throw new UnsupportedOperationException("Method not implemented"); }

    @Override
    public Optional<BST> remove(int e) { throw new UnsupportedOperationException("Method not implemented"); }

    @Override
    public BST merge(Optional<BST> that) {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public String toString() { return label + ""; }
}
