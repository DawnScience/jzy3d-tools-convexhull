package org.jzy3d.convexhull;

import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.util.Deque;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class GrahamScanTest {

    private static Fixtures fixtures;

    public GrahamScanTest() {
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
     * Test of getConvexHull method, of class GrahamScan.
     */
    @Test
    public void testGetConvexHull() {
        try {
            System.out.println("Testing GrahamScan.");

            ConvexHullFunction f = new GrahamScan();
            ConvexHullFunction f2 = new JarvisMarch();
            
            File file = fixtures.data2();
            List<Point2D> lista = DataReader.readData(file);

            Point2D[] data = new Point2D[lista.size()];
            data = lista.toArray(data);

            Deque<Point2D> pino = f.getConvexHull(data);
            Deque<Point2D> pino2 = f2.getConvexHull(data);

            System.out.println("Size: " + pino.size());
            //assertEquals(170, pino.size());
            System.out.println("Size: " + pino2.size());
            assertEquals(22, pino2.size());
            
        } catch (IOException ex) {
            Logger.getLogger(GrahamScanTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}