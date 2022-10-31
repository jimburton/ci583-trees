package ci583.test;

import ci583.trees.BST;
import ci583.trees.Branch;
import ci583.trees.Leaf;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
        assertEquals(1, tree.countNodes());
        tree = tree.insert(23);
        assertEquals(tree.countNodes(), 2);
        tree = tree.insert(66);
        assertEquals(3, tree.countNodes());
        tree = tree.insert(66);
        assertEquals(3, tree.countNodes());
        BST.printTree(System.out, tree);
        assertEquals(42, tree.getLabel());
        assertEquals(23, ((Branch)tree).getLeft().get().getLabel());
        assertEquals(66, ((Branch)tree).getRight().get().getLabel());
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
        assertEquals(2, tree.height());
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
        BST.printTree(System.out, tree.merge(Optional.of(t2)));
        assertEquals("11 9 19 33 70 101 23 42 50 66", tree.merge(Optional.of(t2)).toString().trim());
        assertEquals(tree, tree.merge(null));
    }

    @Test
    public void testRemove() {
        tree = new Leaf(42);
        tree = tree.insert(23);
        tree = tree.insert(66);
        tree = tree.insert(11);
        tree = tree.insert(50);
        assertEquals(tree.toString().trim(), tree.remove(99).get().toString().trim());// something that isn't there
        assertEquals("11 42 50 66", tree.remove(23).get().toString().trim());
    }

}
