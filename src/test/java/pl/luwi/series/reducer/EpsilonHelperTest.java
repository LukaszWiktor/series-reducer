package pl.luwi.series.reducer;

import static java.lang.Math.sqrt;
import static java.util.Arrays.asList;
import static pl.luwi.series.reducer.PointImpl.p;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

public class EpsilonHelperTest {
    
    @Test
    public void shouldCalculateDeviations() {
        List<Point> points = asList(p(1, 1), p(2, 2), p(3, 1), p(4, 3), p(5,1));
        double[] deviations = EpsilonHelper.deviations(points);
        double[] expectedDeviations = new double[]{1.0, 3/sqrt(5), 2.0};
        for (int i = 0; i < deviations.length; i++) {
            Assert.assertEquals(deviations[i], expectedDeviations[i]);    
        }
    }
    
    @Test
    public void shouldCalculateAverage() {
        double avg = EpsilonHelper.avg(new double[] {1.0, 2.0, 3.0});
        Assert.assertEquals(avg, 2.0);
    }
    
    @Test
    public void shouldFindMaximum() {
        double max = EpsilonHelper.max(new double[] {1.0, 2.0, 1.0, 3.0, 1.0});
        Assert.assertEquals(max, 3.0);
    }

}
