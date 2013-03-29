package org.jzy3d.convexhull;

import java.awt.geom.Point2D;
import java.util.Deque;


public interface ConvexHullFunction {

    public Deque<Point2D> getConvexHull(Point2D[] pts);
}