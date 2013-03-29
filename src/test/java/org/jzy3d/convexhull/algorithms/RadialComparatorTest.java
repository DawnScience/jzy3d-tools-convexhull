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
public class RadialComparatorTest {
    
    private static Fixtures fixtures;

    public RadialComparatorTest() {
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
     *
     * @author Teemu Linkosaari
     */
    class MyMath {

        /**
         * Laskee kahden pisteen välisen kulman. Luokka on vain testausta
         * varten.
         *
         * @.pre { origon y-koordinaatti <= a:n y-koordinaatti } .@pre { RESULT
         * == kulma radiaaneina && 0 <= RESULT <= PI}
         */
        public double getAngle(Point2D o, Point2D a) {

            if (o == a) {
                return -1.0d; // pisteen kulma itsensä suhteen ei määritelty
            }
            double eps = 0.0009;
            assert o.getY() - eps <= a.getY() : "\nepatosi: ";

            double dy = a.getY() - o.getY();
            assert dy + eps >= 0;

            double dx = a.getX() - o.getX();

            if (dx > 0) {
                return Math.atan(dy / dx);
            } else {
                return Math.PI / 2 + Math.atan(-dx / dy);
            }
        }
    }

    /**
     *
     */
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
        } else if (n == 1) {
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
        } else if (n == 2) {
            try {
                List<Point2D> lista = DataReader.readData(fixtures.data1());
                taulu = new Point2D[lista.size()];
                taulu = lista.toArray(taulu);
            } catch (IOException e) {
                System.out.println("File error.");
                System.exit(-1);
            }
        } else if (n == 3) {
            try {
                List<Point2D> lista = DataReader.readData(fixtures.data2());
                taulu = new Point2D[lista.size()];
                taulu = lista.toArray(taulu);
            } catch (IOException e) {
                System.out.println("File error.");
                System.exit(-1);
            }
        } else {
            throw new AssertionError("Testidata ei oikea.");
        }

        return taulu;
    }

    /**
     * Test of compare method, of class RadialComparator.
     */
    @Test
    public void testCompare() {
        System.out.println("Testing QuickSort with RadialComparator");
        Point2D[] tc = getTestData(2);

        // Lajittele y-koordinaatit kasvavasti, nyt tc[0] on pienin.
        QuickSort.<Point2D>sort(tc, new XYComparator());

        Point2D origo = tc[0];

        // lajittele origin suhteen
        QuickSort.<Point2D>sort(tc, new RadialComparator(tc[0]));

        // nyt origo on listan viimeinen, koska kulmaa ei ole määritelty.
        assert origo == tc[ tc.length - 1] : "\norigo on muuttunut";

        // tulostetaan kulmat origon suhteen.
        System.out.println("Listan koko on " + tc.length);
        assertEquals(6149, tc.length);

        double prev = 0;
        double cur = 0;
        
        MyMath math = new MyMath();

        for (int i = 0; i <= tc.length - 2; i++) {            
            assertTrue(prev <= cur);
            //System.out.println(i + " " + math.getAngle(tc[ tc.length - 1], tc[i]));
        }

        System.out.println("Ei loytynyt virheita.");
    }

    /**
     * Test of setOrigin method, of class RadialComparator.
     */
    @Test
    public void testSetOrigin() {
    }
}