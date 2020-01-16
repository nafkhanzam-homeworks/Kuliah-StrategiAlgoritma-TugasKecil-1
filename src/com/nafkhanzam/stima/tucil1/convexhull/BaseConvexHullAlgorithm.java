package com.nafkhanzam.stima.tucil1.convexhull;

import java.util.List;

import com.nafkhanzam.utils.Line;
import com.nafkhanzam.utils.Point;

public abstract class BaseConvexHullAlgorithm {

    protected List<Point> points;

    public BaseConvexHullAlgorithm(List<Point> points) {
        this.points = points;
    }

    public abstract List<Line> getResult();

}