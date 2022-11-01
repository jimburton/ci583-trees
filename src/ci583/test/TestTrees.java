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

    BST<Integer> intTree;
    BST<String> strTree;

    @Before
    public void setUp() {
        //
    }

    @Test
    public void testInsertAndCountNodes() {
        intTree = new Leaf<>(42);
        assertEquals(1, intTree.countNodes());
        intTree = intTree.insert(23);
        assertEquals(intTree.countNodes(), 2);
        intTree = intTree.insert(66);
        assertEquals(3, intTree.countNodes());
        intTree = intTree.insert(66);
        assertEquals(3, intTree.countNodes());
        BST.printTree(System.out, intTree);
        assertEquals(Integer.valueOf(42), intTree.getLabel());
        Branch<Integer> b = (Branch<Integer>) intTree;
        assertEquals(Integer.valueOf(23), b.getLeft().get().getLabel());
        assertEquals(Integer.valueOf(66), b.getRight().get().getLabel());
    }

    @Test
    public void testInsertAndCountStrNodes() {
        strTree = new Leaf<>("j");
        String str = "ajkhgjvbcn,jnelky";
        for(int i=0;i<str.length();i++) {
            strTree = strTree.insert(str.charAt(i)+"");
        }
        BST.printTree(System.out, strTree);
        assertEquals("j", strTree.getLabel());
        assertEquals(13, strTree.countNodes()); // unique chars in str
        assertTrue(strTree.search("k"));
        assertFalse(strTree.search("#"));
    }

    @Test
    public void testSearch() {
        intTree = new Leaf<>(42);
        intTree = intTree.insert(23);
        intTree = intTree.insert(66);
        assertTrue(intTree.search(42));
        assertTrue(intTree.search(23));
        assertTrue(intTree.search(66));
        assertFalse(intTree.search(0));
    }

    @Test
    public void testInsertAndSearch() {
        intTree = new Leaf<>(42);
        Random random = new Random();
        int min = -1000;
        int max = 1000;
        List<Integer> rInts = new ArrayList<>();
        for(int i=0;i<100;i++) {
            rInts.add(random.nextInt(max - min) + min);
        }

        for(int i: rInts) {
            intTree = intTree.insert(i);
        }
        BST.printTree(System.out, intTree);
        for(int i: rInts) {
            assertTrue(intTree.search(i));
        }
    }

    @Test
    public void testInsertAndHeight() {
        intTree = new Leaf<>(42);
        intTree = intTree.insert(23);
        intTree = intTree.insert(66);
        intTree = intTree.insert(11);
        intTree = intTree.insert(50);
        assertEquals(2, intTree.height());
    }

    @Test
    public void testMerge() {
        intTree = new Leaf<>(42);
        intTree = intTree.insert(23);
        intTree = intTree.insert(66);
        intTree = intTree.insert(11);
        intTree = intTree.insert(50);

        BST t2 = new Leaf<>(19);
        t2 = t2.insert(9);
        t2 = t2.insert(101);
        t2 = t2.insert(70);
        t2 = t2.insert(33);
        BST.printTree(System.out, intTree);
        BST.printTree(System.out, t2);
        BST.printTree(System.out, intTree.merge(Optional.of(t2)));
        assertEquals("11 9 19 33 70 101 23 42 50 66", intTree.merge(Optional.of(t2)).toString().trim());
        assertEquals(intTree, intTree.merge(null));
    }

    @Test
    public void testRemove() {
        intTree = new Leaf<>(42);
        intTree = intTree.insert(23);
        intTree = intTree.insert(66);
        intTree = intTree.insert(11);
        intTree = intTree.insert(50);
        assertEquals(intTree.toString().trim(), intTree.remove(99).get().toString().trim());// something that isn't there
        assertEquals("11 42 50 66", intTree.remove(23).get().toString().trim());
    }

}
