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
        return ifElseIfElse(e.equals(label), lt(e, label)
                , this
                , new Branch(label, Optional.of(getLeft().map(l -> l.insert(e)).orElse(new Leaf(e))), getRight())
                , new Branch(label, getLeft(), Optional.of(getRight().map(r -> r.insert(e)).orElse(new Leaf(e)))));
    }

    @Override
    public boolean search(T e) {
        return (e.equals(label) || getLeft().map(l -> l.search(e)).orElse(false)
                             || getRight().map(r -> r.search(e)).orElse(false));
    }

    @Override
    public int countNodes() {
        return 1 + getLeft().map(BST::countNodes).orElse(0)
                 + getRight().map(BST::countNodes).orElse(0);
    }

    @Override
    public int height() {
        return 1 + Math.max(getLeft().map(BST::height).orElse(0),
                            getRight().map(BST::height).orElse(0));
    }

    @Override
    public Optional<BST<T>> remove(T e) {
        return ifElseIfElse(e.equals(this.label), lt(e, this.label),
                getLeft().map(l -> l.merge(getRight())).or(this::getRight)
              , Optional.of(new Branch(label, getLeft().map(l -> l.remove(e)).orElse(getLeft()), getRight()))
              , Optional.of(new Branch(label, getLeft(), getRight().map(r -> r.remove(e)).orElse(getRight()))));
    }

    @Override
    public BST<T> merge(Optional<BST<T>> that) {
        if(that == null) {
            return this;
        }
        return that.map(t -> {
            if (t instanceof Branch) {
                Branch<T> thatBranch = (Branch<T>) t;
                return ifElseIfElse(thatBranch.label.equals(label), lt(thatBranch.label, label)
                , this.merge(thatBranch.getLeft().map(l -> l.merge(thatBranch.getRight())).or(thatBranch::getRight))
                , new Branch<T>(label, Optional.of(getLeft().map(l -> l.merge(Optional.of(thatBranch))).orElse(thatBranch))
                                , getRight())
                , new Branch<T>(label, getLeft(), Optional.of(getRight().map(r -> r.merge(Optional.of(thatBranch)))
                                .orElse(thatBranch))));
            } else { // t is a Leaf
                Leaf<T> thatLeaf = (Leaf<T>) t;
                return ifElseIfElse(thatLeaf.label.equals(label), lt(thatLeaf.label, label)
                , this
                , new Branch<T>(label, getLeft().map(l -> l.merge(Optional.of(t))), getRight())
                , new Branch<T>(label, getLeft(), getRight().map(r -> r.merge(Optional.of(t)))));
            }
        }).orElse(this);
    }

    @Override
    public String toString() {
        String l = left.map(lt -> lt + " ").orElse("");
        String r = right.map(rt -> " " + rt).orElse("");
        return l + label + r;
    }
}
