package org.jzy3d.convexhull;

import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.util.Deque;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.jzy3d.convexhull.io.DataReader;

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

        List<Point2D> lista;

        try {
            File file = fixtures.data1();

            lista = DataReader.readData(file);
        } catch (IOException ioe) {
            throw new AssertionError("\nReading data2D2.txt failed!");
        }

        Point2D[] data = new Point2D[lista.size()];
        data = lista.toArray(data);

        Deque<Point2D> pino = f.getConvexHull(data);
        assertEquals(6149, lista.size());
        System.out.println("\nListan koko: " + lista.size());
        assertEquals(13, pino.size());
        System.out.println("Peitteen koko: " + pino.size());
    }

    @org.junit.Test
    public void testGetConvexHull2() {
        ConvexHullFunction f = new JarvisMarch();

        Point2D[] data = new Point2D[18];
        int i = 0;
        data[i++] = new Point2D.Double(0, 0);//0
        data[i++] = new Point2D.Double(1, -3);
        data[i++] = new Point2D.Double(-3, -2);
        data[i++] = new Point2D.Double(-1, -2.3);
        data[i++] = new Point2D.Double(2, -1);
        data[i++] = new Point2D.Double(4, 1);//5
        data[i++] = new Point2D.Double(3, 2);
        data[i++] = new Point2D.Double(3, 4);
        data[i++] = new Point2D.Double(2, 6);
        data[i++] = new Point2D.Double(1, 5);
        data[i++] = new Point2D.Double(-2, 5);//10
        data[i++] = new Point2D.Double(-4, 3);
        data[i++] = new Point2D.Double(-5, 1);
        data[i++] = new Point2D.Double(-4, 0);
        data[i++] = new Point2D.Double(-1, -1);
        data[i++] = new Point2D.Double(-1, 3);//15
        data[i++] = new Point2D.Double(-3, 2);
        data[i++] = new Point2D.Double(-2, 4);

        Deque<Point2D> pino = f.getConvexHull(data);

        System.out.println("\nList size: " + data.length);
         assertEquals(18, data.length);
        System.out.println("Peitteen koko: " + pino.size());
         assertEquals(9, pino.size());
        
        while (!pino.isEmpty()) {
            System.out.println(pino.pop());
        }
        assertEquals(0, pino.size());
    }
}