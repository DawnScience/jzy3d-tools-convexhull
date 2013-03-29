package org.jzy3d.convexhull.algorithms;

import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.jzy3d.convexhull.Fixtures;
import org.jzy3d.convexhull.io.DataReader;
import org.jzy3d.convexhull.utils.QuickSort;

/**
 *
 * @author deric
 */
public class XYComparatorTest {

    private static Fixtures fixtures;

    public XYComparatorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        fixtures = new Fixtures();
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

    /**
     * Test of compare method, of class XYComparator.
     */
    @Test
    public void testCompare() {
        System.out.println("Testing QuickSort with XYComparator");

        Point2D[] taulu = getTestData(2);

        /*System.out.println("Before:");
         for (int ii = 0; ii < taulu.length; ii++) {
         System.out.println(taulu[ii] + " ");
         }
         System.out.println();
         */
        QuickSort.<Point2D>sort(taulu, new XYComparator());

        System.out.println("After Quicksort:");
        int j = 1;
        for (int ii = 0; ii < taulu.length; ii++) {
            assertTrue(taulu[ii].getY() <= taulu[j].getY());
            //System.out.println(taulu[ii] + " " + ii);
            j = ii + 1;
        }

    }

    private static Point2D[] getTestData(int n) {

        Point2D[] taulu = null;

        if (n == 0) {
            taulu = new Point2D[5];
            int i = 0;
            taulu[i++] = new Point2D.Double(3.0, 0.0);
            taulu[i++] = new Point2D.Double(2.0, 3.0);
            taulu[i++] = new Point2D.Double(2.0, 2.0);
            taulu[i++] = new Point2D.Double(2.0, 1.0);
            taulu[i++] = new Point2D.Double(1.0, 0.0);
        }

        if (n == 1) {
            taulu = new Point2D[8];
            int i = 0;
            taulu[i++] = new Point2D.Double(-1.362, 2.226);
            taulu[i++] = new Point2D.Double(-0.19, 0.16);
            taulu[i++] = new Point2D.Double(-1.2, 1.794);
            taulu[i++] = new Point2D.Double(0.15, 0.3);
            taulu[i++] = new Point2D.Double(1, -1.53);
            taulu[i++] = new Point2D.Double(1.19, 0.05);
            taulu[i++] = new Point2D.Double(-1.524, 2.28);
            taulu[i++] = new Point2D.Double(-1.117, 3.449);
        }

        if (n == 2) {
            try {
                File file = fixtures.data2();
                List<Point2D> lista = DataReader.readData(file);
                taulu = new Point2D[lista.size()];
                taulu = lista.toArray(taulu);
            } catch (IOException e) {
                System.out.println("File error.");
                System.exit(-1);
            }
        }
        return taulu;
    }
}