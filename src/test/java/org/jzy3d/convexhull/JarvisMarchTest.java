package org.jzy3d.convexhull;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.jzy3d.convexhull.algorithms.Point2f;
import org.jzy3d.convexhull.io.DataReader;
import org.jzy3d.convexhull.utils.Stack;

/**
 *
 * @author deric
 */
public class JarvisMarchTest {

    private static Fixtures fixtures;

    public JarvisMarchTest() {
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
     * Test of getConvexHull method, of class JarvisMarch.
     */
    @org.junit.Test
    public void testGetConvexHull() {
        ConvexHullFunction f = new JarvisMarch();

        List<Point2f> lista;

        try {
            File file = fixtures.data1();

            lista = DataReader.readData(file);
        } catch (IOException ioe) {
            throw new AssertionError("\nReading data2D2.txt failed!");
        }

        Point2f[] data = new Point2f[lista.size()];
        data = lista.toArray(data);

        Stack<Point2f> pino = f.getConvexHull(data);
        assertEquals(6149, lista.size());
        System.out.println("\nListan koko: " + lista.size());
        assertEquals(13, pino.size());
        System.out.println("Peitteen koko: " + pino.size());
    }

    @org.junit.Test
    public void testGetConvexHull2() {
        ConvexHullFunction f = new JarvisMarch();

        Point2f[] data = new Point2f[18];
        int i = 0;
        data[i++] = new Point2f(0, 0);//0
        data[i++] = new Point2f(1, -3);
        data[i++] = new Point2f(-3, -2);
        data[i++] = new Point2f(-1, -2.3);
        data[i++] = new Point2f(2, -1);
        data[i++] = new Point2f(4, 1);//5
        data[i++] = new Point2f(3, 2);
        data[i++] = new Point2f(3, 4);
        data[i++] = new Point2f(2, 6);
        data[i++] = new Point2f(1, 5);
        data[i++] = new Point2f(-2, 5);//10
        data[i++] = new Point2f(-4, 3);
        data[i++] = new Point2f(-5, 1);
        data[i++] = new Point2f(-4, 0);
        data[i++] = new Point2f(-1, -1);
        data[i++] = new Point2f(-1, 3);//15
        data[i++] = new Point2f(-3, 2);
        data[i++] = new Point2f(-2, 4);

        Stack<Point2f> pino = f.getConvexHull(data);

        System.out.println("\nList size: " + data.length);
         assertEquals(18, data.length);
        System.out.println("Peitteen koko: " + pino.size());
         assertEquals(9, pino.size());
        
        while (!pino.empty()) {
            System.out.println(pino.pop());
        }
        assertEquals(0, pino.size());
    }
}