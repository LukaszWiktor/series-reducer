package pl.wiktor.lukasz.series.reducer;

import static java.util.Arrays.asList;

import java.util.ArrayList;
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
        if (epsilon <= 0) {
            throw new IllegalArgumentException("Epsilon has to be greater than 0.");
        }
        double furthestPointDistance = 0.0;
        int furthestPointIndex = 0;
        Point lineStart = points.get(0);
        Point lineEnd = points.get(points.size() - 1);                
        for (int i = 1; i < points.size() - 1; i++) {
            double distance = distance(points.get(i), lineStart, lineEnd);
            if (distance > furthestPointDistance ) {
                furthestPointDistance = distance;
                furthestPointIndex = i;
            }
        }
        if (furthestPointDistance > epsilon) {
            List<Point> reduced1 = reduce(points.subList(0, furthestPointIndex+1), epsilon);
            List<Point> reduced2 = reduce(points.subList(furthestPointIndex, points.size()), epsilon);
            List<Point> result = new ArrayList<Point>(reduced1);
            result.addAll(reduced2.subList(1, reduced2.size()));
            return result;
        } else {
            return asList(lineStart, lineEnd);
        }
    }
    
    static double distance(Point p, Point lineStart, Point lineEnd) {
        double dx = lineStart.getX() - lineEnd.getX();
        double dy = lineStart.getY() - lineEnd.getY();
        return Math.abs(dy * p.getX() - dx * p.getY() + lineStart.getX() * lineEnd.getY() - lineEnd.getX() * lineStart.getY()) 
               / Math.sqrt(dx*dx + dy*dy);
    }
}
