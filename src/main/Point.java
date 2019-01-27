package main;

class Point {
    double x, y;

    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the Point halway between the given points.
     */
    static Point average(Point a, Point b) {
        return new Point((a.x+b.x)/2.0, (a.y+b.y)/2.0);
    }
}
