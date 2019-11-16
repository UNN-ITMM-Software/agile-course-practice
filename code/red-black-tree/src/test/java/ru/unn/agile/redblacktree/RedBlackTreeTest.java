package ru.unn.agile.redblacktree;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static junit.framework.TestCase.*;

public class RedBlackTreeTest {
    private static RedBlackTree tree;
    private static Random random = new Random();

    public RedBlackTreeTest() {
    }

    @Before
    public void setUp() {
        tree = new RedBlackTree();
    }

    @Test
    public void defaultConstructedIsEmpty() {
        assertTrue(tree.isEmpty());
    }

    @Test
    public void insertionChangesEmptiness() {
        tree.insert(8);

        assertFalse(tree.isEmpty());
    }

    @Test
    public void emptiedRedBlackTreeIsEmpty() {
        int expected = 42;
        tree.insert(expected);
        tree.remove(expected);

        assertTrue(tree.isEmpty());
    }

    @Test
    public void emptyTreeSizeIsZero() {
        assertEquals(0, tree.getSize());
    }

    @Test
    public void notEmptyTreeSizeIsNotZero() {
        tree.insert(42);
        assertFalse(tree.getSize() == 0);
    }

    @Test
    public void insertionChangesSize() {
        int threshold = 8;
        int counter = 0;
        while (counter < threshold) {
            ++counter;
            final int newSize = tree.getSize() + 1;
            tree.insert(counter);
            assertEquals(tree.getSize(), newSize);
        }
    }

    @Test
    public void canFindInserted() {
        int expected = 42;
        tree.insert(expected);

        assertTrue(tree.find(expected));
    }

    @Test
    public void insertCornerCaseMax() {
        tree.insert(Integer.MAX_VALUE);

        assertTrue(tree.find(Integer.MAX_VALUE));
    }

    @Test
    public void insertCornerCaseMin() {
        tree.insert(Integer.MIN_VALUE);

        assertTrue(tree.find(Integer.MIN_VALUE));
    }

    @Test
    public void removeCornerCaseMin() {
        tree.insert(Integer.MIN_VALUE);
        tree.remove(Integer.MIN_VALUE);

        assertFalse(tree.find(Integer.MIN_VALUE));
    }

    @Test
    public void removeCornerCaseMax() {
        tree.insert(Integer.MAX_VALUE);
        tree.remove(Integer.MAX_VALUE);

        assertFalse(tree.find(Integer.MAX_VALUE));
    }

    @Test
    public void insertSameNumberIncrementsSize() {
        int number = 42;
        tree.insert(number);
        tree.insert(number);
        assertEquals(2, tree.getSize());
    }

    @Test
    public void insertRandomNumberSequence() {
        for (int counter = 0; counter < 1000000; ++counter) {
            int randomNumber = random.nextInt(1024);
            tree.insert(randomNumber);
            assertTrue(tree.find(randomNumber));
        }
    }

    @Test
    public void removeRandomNumberSequence() {
        for (int counter = 0; counter < 1000000; ++counter) {
            int insertRandomNumber = random.nextInt(1024);
            tree.insert(insertRandomNumber);
            int removeRandomNumber = random.nextInt(1024);
            final boolean found = tree.find(removeRandomNumber);
            assertEquals(found, tree.remove(removeRandomNumber));
        }
    }

    @Test
    public void findRandomNumberSequence() {
        Set<Integer> set = new HashSet<>();

        for (int counter = 0; counter < 1000000; ++counter) {
            int insertRandomNumber = random.nextInt(1024);
            set.add(insertRandomNumber);
            tree.insert(insertRandomNumber);
            int findRandomNumber = random.nextInt(1024);
            assertEquals(set.contains(findRandomNumber), tree.find(findRandomNumber));
        }
    }

    @Test
    public void removeDoesNotRemoveAllOccurrences() {
        int number = 42;
        tree.insert(number);
        tree.insert(number);

        tree.remove(number);

        assertTrue(tree.find(number));
    }

    @Test
    public void removeRemoves() {
        int number = 42;
        tree.insert(number);

        tree.remove(number);

        assertFalse(tree.find(number));
    }

    @Test
    public void canFindEachInserted() {
        int threshold = 88;
        int counter = 0;
        while (counter < threshold) {
            ++counter;
            tree.insert(counter);
            assertTrue(tree.find(counter));
        }
    }

    @Test
    public void cannotFindNotInserted() {
        int threshold = 88;
        int counter = 0;
        while (counter < threshold) {
            assertFalse(tree.find(++counter));
        }
    }

    @Test
    public void removeReturnsFalseIfNotFound() {
        assertFalse(tree.remove(42));
    }

    @Test
    public void removeReturnsTrueIfFound() {
        int expected = 42;

        tree.insert(expected);

        assertTrue(tree.remove(expected));
    }

    @Test
    public void removeChangesSizeIfFound() {
        int threshold = 88;
        int counter = 0;
        while (counter < threshold) {
            tree.insert(counter++);
        }

        while (counter > 0) {
            int newSize = tree.getSize() - 1;
            tree.remove(--counter);
            assertEquals(newSize, tree.getSize());
        }
    }

    @Test
    public void removeDoesNotChangeSizeIfNotFound() {
        int threshold = 88;
        int counter = 0;
        while (counter < threshold) {
            tree.insert(++counter);
        }

        int expectedSize = tree.getSize();

        while (counter < threshold * 2) {
            tree.remove(++counter);
            assertEquals(tree.getSize(), expectedSize);
        }
    }
}
