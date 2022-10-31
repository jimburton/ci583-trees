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
        return ifElseIfElse(e == label, e < label
                , this
                , new Branch(label, Optional.of(getLeft().map(l -> l.insert(e)).orElse(new Leaf(e))), getRight())
                , new Branch(label, getLeft(), Optional.of(getRight().map(r -> r.insert(e)).orElse(new Leaf(e)))));
    }

    @Override
    public boolean search(int e) {
        return (e == label || getLeft().map(l -> l.search(e)).orElse(false)
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
    public Optional<BST> remove(int e) {
        return ifElseIfElse(e == this.label, e < this.label,
                getLeft().map(l -> l.merge(getRight())).or(this::getRight)
              , Optional.of(new Branch(label, getLeft().map(l -> l.remove(e)).orElse(getLeft()), getRight()))
              , Optional.of(new Branch(label, getLeft(), getRight().map(r -> r.remove(e)).orElse(getRight()))));
    }

    @Override
    public BST merge(Optional<BST> that) {
        if(that == null) {
            return this;
        }
        return that.map(t -> {
            if (t instanceof Branch) {
                Branch thatBranch = (Branch) t;
                return ifElseIfElse(thatBranch.label == label, thatBranch.label < label
                , this.merge(thatBranch.getLeft().map(l -> l.merge(thatBranch.getRight())).or(thatBranch::getRight))
                , new Branch(label, Optional.of(getLeft().map(l -> l.merge(Optional.of(thatBranch))).orElse(thatBranch))
                                , getRight())
                , new Branch(label, getLeft(), Optional.of(getRight().map(r -> r.merge(Optional.of(thatBranch)))
                                .orElse(thatBranch))));
            } else { // t is a Leaf
                return ifElseIfElse(t.label == label, t.label < label
                , this
                , new Branch(label, getLeft().map(l -> l.merge(Optional.of(t))), getRight())
                , new Branch(label, getLeft(), getRight().map(r -> r.merge(Optional.of(t)))));
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
