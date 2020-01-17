package com.nafkhanzam.utils;

public class Line extends Pair<Point, Point> {

    public Line(Point a, Point b) {
        super(a, b);
    }

    public static Line of(Point a, Point b) {
        return new Line(a, b);
    }

    @Override
    public String toString() {
        return String.format("%s->%s", a, b);
    }

}