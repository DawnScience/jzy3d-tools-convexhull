package org.jzy3d.convexhull.utils;

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
public class QuickSortTest {

    public QuickSortTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    class BasicComparator implements IComparator<Integer> {

        public int compare(Integer a, Integer b) {
            return a.compareTo(b);
        }
    }

    /**
     * Test of sort method, of class QuickSort.
     */
    @Test
    public void testSort() {
        System.out.println("Testing QuickSort...");
        Integer[] taulu = {2, 8, 7, 1, 3, 5, 6, 4};

        // lajittele
        QuickSort.<Integer>sort(taulu, new BasicComparator());

        int prev = 0;
        for (Integer a : taulu) {
            assertTrue(prev < a);
            prev = a;
        }        
    }
}