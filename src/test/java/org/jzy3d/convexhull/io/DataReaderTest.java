package org.jzy3d.convexhull.io;

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

/**
 *
 * @author deric
 */
public class DataReaderTest {

    private static Fixtures fixtures;

    public DataReaderTest() {
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
     * Test of readData method, of class DataReader.
     */
    @Test
    public void testReadData_String() throws Exception {
    }

    /**
     * Test of readData method, of class DataReader.
     */
    @Test
    public void testReadData_File1() throws Exception {

        File file = fixtures.data1();

        try {
            List<Point2D> lista = DataReader.readData(file);

            System.out.println("The number of keys is " + lista.size());
            assertEquals(6149, lista.size());

            for (int i = 0; i < 4; i++) {
                System.out.println("Piste " + i + " = " + lista.get(i));
            }

            
            Point2D p = lista.get(lista.size() - 1);
            System.out.println("The last point is " + p);
            double delta = 0.1e-5;
            assertEquals(0.81, p.getX(), delta);
            assertEquals(-0.31, p.getY(), delta);            
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
    
        /**
     * Test of readData method, of class DataReader.
     */
    @Test
    public void testReadData_File2() throws Exception {

        File file = fixtures.data2();

        try {
            List<Point2D> lista = DataReader.readData(file);

            System.out.println("The number of keys is " + lista.size());
            assertEquals(32000, lista.size());

            for (int i = 0; i < 4; i++) {
                System.out.println("point " + i + " = " + lista.get(i));
            }
            
            Point2D p = lista.get(lista.size() - 1);
            System.out.println("The last point is " + p);
            double delta = 0.1e-5;
            assertEquals(-0.0118, p.getX(), delta);
            assertEquals(0.0327, p.getY(), delta);            
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
}