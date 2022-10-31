package ci583.trees;

import java.util.Optional;

import static ci583.Functions.ifElseIfElse;

public class Leaf extends BST{
    public Leaf(int label) {
        super(label);
    }

    // Exercises

    @Override
    public BST insert(int e) {
        return ifElseIfElse(e == label, e < label
        , this
        , new Branch(label, Optional.of(new Leaf(e)), Optional.empty())
        , new Branch(label, Optional.empty(), Optional.of(new Leaf(e))));

    }

    @Override
    public boolean search(int e) {
        return (e == label);
    }

    @Override
    public int countNodes() { return 1; }

    @Override
    public int height() { return 0; }

    @Override
    public Optional<BST> remove(int e) { return (e == label) ? Optional.empty() : Optional.of(this); }

    @Override
    public BST merge(Optional<BST> that) {
        if(that == null) {
            return this;
        }
        return that.map(t ->
            ifElseIfElse(t.label == label, t.label < label
            , this
            , new Branch(label, that, Optional.empty())
            , new Branch(label, Optional.empty(), that))
        ).orElse(this);
    }

    @Override
    public String toString() { return label + ""; }
}
