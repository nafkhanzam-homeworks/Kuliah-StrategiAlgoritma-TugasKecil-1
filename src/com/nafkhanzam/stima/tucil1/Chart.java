package com.nafkhanzam.stima.tucil1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import com.nafkhanzam.stima.tucil1.convexhull.ConvexHullBruteForce;
import com.nafkhanzam.utils.Line;
import com.nafkhanzam.utils.Point;
import com.nafkhanzam.utils.RandomUtils;

public class Chart extends JPanel {

    private static final long serialVersionUID = 1193337153538279793L;
    private static final int RADIUS = 5;

    public int MX, MY;
    public List<Point> points;
    public List<Line> lines;
    public boolean useString = true;

    public Chart(int MX, int MY) {
        super();
        this.MX = MX;
        this.MY = MY;
        this.points = new ArrayList<>();
        this.lines = new ArrayList<>();
        this.setBackground(Color.BLACK);
    }

    public long getExecutedTimeInNs(int n) {
        clearPoints();

        while (n-- > 0) {
            this.points.add(Point.of(RandomUtils.random(0, MX), RandomUtils.random(0, MY)));
        }

        ConvexHullBruteForce alg = new ConvexHullBruteForce(this.points);
        long time = System.nanoTime();
        this.lines = alg.getResult();
        time = System.nanoTime() - time;

        this.repaint();

        return time;
    }

    public void clearPoints() {
        this.points.clear();
        this.lines.clear();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D)g;

        g2.setColor(Color.CYAN);
        _drawLine(g2, Line.of(Point.of(0, 0), Point.of(0, MY)));
        _drawLine(g2, Line.of(Point.of(0, 0), Point.of(MX, 0)));

        g2.setColor(Color.WHITE);
        for (int i = 0; i < this.lines.size(); ++i) {
            _drawLine(g2, this.lines.get(i));
        }

        for (Point point : points) {
            _drawPoint(g2, point, Color.YELLOW, Color.GREEN);
        }
    }

    private void _drawLine(Graphics2D g, Line _line) {
        Line line = convert(_line);
        g.draw(new Line2D.Float(line.a.a, line.a.b, line.b.a, line.b.b));
    }

    private void _drawPoint(Graphics2D g, Point _point, Color dotColor, Color textColor) {
        Point point = convert(_point);
        int r = RADIUS;
        int x = point.a - r/2, y = point.b - r/2;
        g.setColor(dotColor);
        g.fillOval(x, y, r, r);
        if (useString) {
            g.setColor(textColor);
            _drawCenteredString(g, _point.toString(), x, y + r + 15);
        }
    }

    private void _drawCenteredString(Graphics g, String text, int x, int y) {
        int w = g.getFontMetrics().stringWidth(text);
        g.drawString(text, x - w/2, y);
    }

    private Point convert(Point p) {
        int border = 50;
        double x = p.a, y = MY - p.b;
        double w = this.getWidth() - 2*border, h = this.getHeight() - 2*border;
        double cx = x*w/MX, cy = y*h/MY;
        return Point.of((int)cx + border, (int)cy + border);
    }

    private Line convert(Line l) {
        Point a = convert(l.a), b = convert(l.b);
        return Line.of(a, b);
    }

}