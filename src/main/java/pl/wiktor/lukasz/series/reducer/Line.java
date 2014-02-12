package pl.wiktor.lukasz.series.reducer;

import java.util.Arrays;
import java.util.List;

public class Line {
    
    private Point start;
    private Point end;
    
    private double dx;
    private double dy;
    private double sxey;
    private double exsy;
    private  double length;
    
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
    
    double distance(Point p) {
        return Math.abs(dy * p.getX() - dx * p.getY() + sxey - exsy) / length;
    }
}


