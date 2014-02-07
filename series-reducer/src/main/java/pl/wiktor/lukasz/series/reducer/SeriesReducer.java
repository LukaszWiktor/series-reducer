package pl.wiktor.lukasz.series.reducer;

import java.util.List;

public class SeriesReducer {

    /**
     * Reduces number of points in given series using Ramer-Douglas-Peucker algorithm.
     * 
     * @param points
     *          initial, ordered list of points
     * @param epsilon
     *          allowed margin of the resulting curve, has to be > 0
     */
    public static List<Point> reduce(List<Point> points, double epsilon) {
        return null;
    }
    
    static double distance(Point p, Point lineStart, Point lineEnd) {
        double dx = lineStart.getX() - lineEnd.getX();
        double dy = lineStart.getY() - lineEnd.getY();
        return Math.abs(dy * p.getX() - dx * p.getY() + lineStart.getX() * lineEnd.getY() - lineEnd.getX() * lineStart.getY()) 
               / Math.sqrt(dx*dx + dy*dy);
    }
}
