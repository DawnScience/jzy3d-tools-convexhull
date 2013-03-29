package org.jzy3d.convexhull;

import java.awt.geom.Point2D;


/**
 *
 * @author Tomas Barton
 */
public class PerformanceTest {

    /**
     * Luo n kpl pisteitä satunnaisesti.
     */
    private static Point2D[] generate(int n) {
        assert n >= 0;

        Point2D[] t = new Point2D[n];

        java.util.Random gen = new java.util.Random();

        for (int i = 0; i < n; i++) {
            double x = 500 - 1000 * gen.nextDouble();
            double y = 500 - 1000 * gen.nextDouble();
            t[i] = new Point2D.Double(x, y);

            assert t[i] != null;
        }

        assert t != null;

        return t;
    }

    @org.junit.Test
    public void testPerformance() {

        System.out.println("pisteet, aika (ms)");

        System.out.print("jarvis ==== ");
        runTest(new JarvisMarch(), 250);

        System.out.print("graham ==== ");
        runTest(new GrahamScan(), 250);
    }

    /**
     * Tulostaa (pisteet ja ajan) generoimalla tuhannen välein aina
     * tuhannet*1000 pisteeseen saakka funktiolla f.
     */
    private static void runTest(ConvexHullFunction f, int tuhannet) {
        long total = 0;
        int start = 200;
        int runs = tuhannet - start;
        for (int i = start; i <= tuhannet; i++) {
            int pts = 100 * i;        
            Point2D[] p = generate(pts);

            long t1 = System.currentTimeMillis();
            f.getConvexHull(p);
            long t2 = System.currentTimeMillis();
            total += t2 - t1;
        }
        System.out.println("avg = " + (total / (double) runs));

        
    }
}
