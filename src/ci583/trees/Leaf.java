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
        return ifElseIfElse(e.equals(label), lt(e, label)
        , this
        , new Branch(label, Optional.of(new Leaf(e)), Optional.empty())
        , new Branch(label, Optional.empty(), Optional.of(new Leaf(e))));

    }

    @Override
    public boolean search(T e) {
        return e.equals(label);
    }

    @Override
    public int countNodes() { return 1; }

    @Override
    public int height() { return 0; }

    @Override
    public Optional<BST<T>> remove(T e) { return e.equals(label) ? Optional.empty() : Optional.of(this); }

    @Override
    public BST<T> merge(Optional<BST<T>> that) {
        if(that == null) {
            return this;
        }
        return that.map(t ->
            ifElseIfElse(t.label.equals(label), lt(t.label, label)
            , this
            , new Branch<T>(label, that, Optional.empty())
            , new Branch<T>(label, Optional.empty(), that))
        ).orElse(this);
    }

    @Override
    public String toString() { return label + ""; }
}
