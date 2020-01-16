package com.nafkhanzam.stima.tucil1.convexhull;

import java.util.ArrayList;
import java.util.List;

import com.nafkhanzam.utils.Line;
import com.nafkhanzam.utils.Point;

public class ConvexHullBruteForce extends BaseConvexHullAlgorithm {

	public ConvexHullBruteForce(List<Point> points) {
		super(points);
	}

	// O(n^3)
	@Override
	public List<Line> getResult() {
		List<Line> res = new ArrayList<>();

		if (this.points.size() <= 1) {
			return res;
		}

		int N = this.points.size();
		// O(n^3)
		for (int i = 0; i < N; ++i) {
			// O(n^2)
			for (int j = 0; j < N; ++j) {
				if (i == j) {
					continue;
				}

				Point a = this.points.get(i), b = this.points.get(j);

				// O(n)
				if (_isLineValidForBeingTheVeryLeft(a, b)) {
					res.add(Line.of(a, b));
				}
			}
		}

		return res;
    }

	private boolean _isLineValidForBeingTheVeryLeft(Point a, Point b) {
		for (int i = 0; i < this.points.size(); ++i) {
			if (!_isOnTheRightSide(a, b, this.points.get(i))) {
				return false;
			}
		}
		return true;
	}

	private boolean _isOnTheRightSide(Point a, Point b, Point p) {
		Point bb = b.shallowCopy();
		Point pp = p.shallowCopy();

		bb.translate(-a.a, -a.b);
		pp.translate(-a.a, -a.b);

		return bb.a*pp.b - bb.b*pp.a <= 0;
	}

}