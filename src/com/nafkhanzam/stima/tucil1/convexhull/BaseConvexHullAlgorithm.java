package com.nafkhanzam.stima.tucil1.convexhull;

import java.util.List;

import com.nafkhanzam.utils.Point;

public abstract class BaseConvexHullAlgorithm {

    private List<Point> points;

    public BaseConvexHullAlgorithm(List<Point> points) {
        this.points = points;
    }

    public abstract List<Point> getResult();

}