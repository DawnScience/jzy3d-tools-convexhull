package org.jzy3d.convexhull.algorithms;

import java.awt.geom.Point2D;
import org.jzy3d.convexhull.utils.IComparator;

/**
 * @author Jukka Moisio, Teemu Linkosaari
 */
public class RadialComparator implements IComparator<Point2D> {

    /**
     * Origo, jonka suhteen verrataan
     */
    private Point2D origin;

    /**
     * @.pre { origin != null )
     */
    public RadialComparator(Point2D origin) {
        assert origin != null;
        this.origin = origin;
    }

    @Override
    public int compare(Point2D p1, Point2D p2) {
        return polarCompare(origin, p1, p2);
    }

    /**
     *
     */
    public void setOrigin(Point2D newO) {
        origin = newO;
    }

    private static int polarCompare(Point2D o, Point2D p, Point2D q) {
        double dxp = p.getX() - o.getX();
        double dyp = p.getY() - o.getY();
        double dxq = q.getX() - o.getX();
        double dyq = q.getY() - o.getY();

        int orient = ComputationalGeometry.computeOrientation(o, p, q);

        if (orient == ComputationalGeometry.CounterClockwise) {
            return -1;
        }
        if (orient == ComputationalGeometry.Clockwise) {
            return 1;
        }

        // points are collinear - check distance
        double op = dxp * dxp + dyp * dyp;
        double oq = dxq * dxq + dyq * dyq;
        if (op < oq) {
            return 1;
        }
        if (op > oq) {
            return -1;
        }

        return 0;
    }
}