package org.jzy3d.convexhull.algorithms;

import java.awt.geom.Point2D;
import org.jzy3d.convexhull.utils.IComparator;

public class XYComparator implements IComparator<Point2D> {

    private double epsilon;

    public XYComparator() {
        this(0.000001);
    }

    public XYComparator(double epsilon) {
        this.epsilon = epsilon;
    }

    /**
     *
     */
    @Override
    public int compare(Point2D a, Point2D b) {

        assert a != null;
        assert b != null;

        int result = compareDouble(a.getY(), b.getY(), this.epsilon);

        if (result == 0) {
            result = compareDouble(a.getX(), b.getX(), this.epsilon);
        }

        return result;
    }

    /*
     * @.pre {true}
     * @.post {Jos a < b, RESULT < 0
     *        jos a = b, RESULT == 0
     *        jos a > b, RESULT > 0}
     */
    private int compareDouble(double a, double b, double eps) {
        double diff = a - b;
        if (-eps < diff && diff < eps) {
            return 0;
        }
        if (a < b) {
            return -1;
        }
        if (a > b) {
            return 1;
        }

        throw new AssertionError("It should be impossible to reach here.");
    }
}