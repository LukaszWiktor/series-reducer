package pl.wiktor.lukasz.series.reducer;

import static pl.wiktor.lukasz.series.reducer.PointImpl.p;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LineTest {
    
    @DataProvider(name="pointsAndLines")
    public Object[][] pointsAndLinesDataProvider() {
        return new Object[][] {
                {p(1.0, 1.0), new Line(p(0.0, 0.0), p(2.0, 0.0)), 1.0}, // horizontal line
                {p(1.0, 1.0), new Line(p(0.0, 0.0), p(0.0, 1.0)), 1.0}, // vertical line
                {p(1.0, 1.0), new Line(p(-1.0, 1.0), p(1.0, -1.0)), Math.sqrt(2)}, // sloped line
                {p(0.0, 0.0), new Line(p(-1.0, 1.0), p(1.0, -1.0)), 0.0}, // point is on the line
        };
    }
    
    @Test(dataProvider="pointsAndLines")
    public void shouldCalculateDistanceFromPointToLine(Point p, Line line, double expectedDistance) {
        double calculatedDistance = line.distance(p);
        Assert.assertTrue(Math.abs(expectedDistance - calculatedDistance) < 0.000000000000001);
    }
}
