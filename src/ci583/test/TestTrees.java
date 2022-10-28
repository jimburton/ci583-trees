package ci583.test;

import ci583.trees.BST;
import ci583.trees.Branch;
import ci583.trees.Leaf;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

public class TestTrees {

    BST tree;

    @Before
    public void setUp() {
        //
    }

    @Test
    public void testInsertAndCountNodes() {
        tree = new Leaf(42);
        assertEquals(tree.countNodes(), 1);
        tree = tree.insert(23);
        assertEquals(tree.countNodes(), 2);
        tree = tree.insert(66);
        assertEquals(tree.countNodes(), 3);
        tree = tree.insert(66);
        assertEquals(tree.countNodes(), 3);
        BST.printTree(System.out, tree);
        assertEquals(tree.getLabel(), 42);
        assertEquals(((Branch)tree).getLeft().get().getLabel(), 23);
        assertEquals(((Branch)tree).getRight().get().getLabel(), 66);
    }

    @Test
    public void testSearch() {
        tree = new Leaf(42);
        tree = tree.insert(23);
        tree = tree.insert(66);
        assertTrue(tree.search(42));
        assertTrue(tree.search(23));
        assertTrue(tree.search(66));
        assertFalse(tree.search(0));
    }

    @Test
    public void testInsertAndSearch() {
        tree = new Leaf(42);
        Random random = new Random();
        int min = -1000;
        int max = 1000;
        List<Integer> rInts = new ArrayList<>();
        for(int i=0;i<100;i++) {
            rInts.add(random.nextInt(max - min) + min);
        }

        for(int i: rInts) {
            tree = tree.insert(i);
        }
        BST.printTree(System.out, tree);
        for(int i: rInts) {
            assertTrue(tree.search(i));
        }
    }

    @Test
    public void testInsertAndHeight() {
        tree = new Leaf(42);
        tree = tree.insert(23);
        tree = tree.insert(66);
        tree = tree.insert(11);
        tree = tree.insert(50);
        assertEquals(tree.height(), 2);
    }

    @Test
    public void testMerge() {
        tree = new Leaf(42);
        tree = tree.insert(23);
        tree = tree.insert(66);
        tree = tree.insert(11);
        tree = tree.insert(50);

        BST t2 = new Leaf(19);
        t2 = t2.insert(9);
        t2 = t2.insert(101);
        t2 = t2.insert(70);
        t2 = t2.insert(33);
        BST.printTree(System.out, tree);
        BST.printTree(System.out, t2);
        BST.printTree(System.out, tree.merge(t2));
        assertEquals(tree.merge(t2).toString(), "11 9 19 33 70 101 23 42 50 66");
        assertEquals(tree.merge(null), tree);
    }

    @Test
    public void testRemove() {
        tree = new Leaf(42);
        tree = tree.insert(23);
        tree = tree.insert(66);
        tree = tree.insert(11);
        tree = tree.insert(50);
        assertEquals(tree, tree.remove(99));// something that isn't there
        assertEquals(tree.remove(23).toString().trim(), "11 42 50 66");
    }

}
