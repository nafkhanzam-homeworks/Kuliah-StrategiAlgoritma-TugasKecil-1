package com.nafkhanzam.utils;

public class Point extends Pair<Integer, Integer> {

    public Point(int a, int b) {
        super(a, b);
    }

    public static Point of(int a, int b) {
        return new Point(a, b);
    }

    @Override
    public String toString() {
        return String.format("(%s,%s)", a, b);
    }

    public Point shallowCopy() {
		return new Point(a, b);
	}

    public void translate(int a, int b) {
        this.a += a;
        this.b += b;
    }

}