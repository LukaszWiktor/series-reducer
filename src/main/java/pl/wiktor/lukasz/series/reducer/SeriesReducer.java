package pl.wiktor.lukasz.series.reducer;

import java.util.ArrayList;
import java.util.Arrays;
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
        Line line = new Line(points.get(0), points.get(points.size() - 1));
        for (int i = 1; i < points.size() - 1; i++) {
            double distance = distance(points.get(i), line);
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
            return line.asList();
        }
    }
    
    static class Line {
        
        Point start;
        Point end;
        
        double dx;
        double dy;
        double sxey;
        double exsy;
        double length;
        
        public Line(Point start, Point end) {
            this.start = start;
            this.end = end;
            dx = start.getX() - end.getX();
            dy = start.getY() - end.getY();
            sxey = start.getX() * end.getY();
            exsy = end.getX() * start.getY();
            length = Math.sqrt(dx*dx + dy*dy);
        }
        
        public List<Point> asList() {
            return Arrays.asList(start, end);
        }
    }
    
    static double distance(Point p, Line line) {
        return Math.abs(line.dy * p.getX() - line.dx * p.getY() + line.sxey - line.exsy) / line.length;
    }
}
