package org.jzy3d.convexhull.utils;

import java.util.ArrayDeque;
import java.util.Deque;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author deric
 */
public class StackTest {

    private Deque<Integer> stack;

    public StackTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        stack = new ArrayDeque<Integer>();

        // ensimmäinen testi
        for (int i = 1; i <= 10; i++) {
            stack.push(i);
        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of push method, of class LinkedStack.
     */
    @Test
    public void testPush() {
        //toinen testi
        for (int i = 11; i <= 20; i++) {
            stack.push(i);
        }
        int i = 20;
        System.out.println("size "+stack.size());
        while (!stack.isEmpty()) {
            assertEquals(i, stack.size());
            int ret = stack.pop();
            assertEquals(i, ret);
            i--;
        }
        assertEquals(0, stack.size());
    }

    /**
     * Test of empty method, of class LinkedStack.
     */
    @Test
    public void testEmpty() {
    }

    /**
     * Test of pop method, of class LinkedStack.
     */
    @Test
    public void testPop() {
        for (int i = 10; i > 0; i--) {
            assertTrue(stack.size() == i && stack.pop() == i);
        }
        assertEquals(0, stack.size());
    }

    /**
     * Test of peek method, of class LinkedStack.
     */
    @Test
    public void testPeek() {
    }

    /**
     * Test of size method, of class LinkedStack.
     */
    @Test
    public void testSize() {
        assertEquals(10, stack.size());
    }
}