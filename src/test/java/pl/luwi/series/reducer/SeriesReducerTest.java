package pl.luwi.series.reducer;

import static java.util.Arrays.asList;
import static pl.luwi.series.reducer.PointImpl.p;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SeriesReducerTest {
  
    @DataProvider(name="series")
    public Object[][] seriesDataProvider() {
        return new Object[][] {
                // 1.
                {asList(p(1, 1.0), p(2, 1.1), p(3, 0.9), p(4, 1.0)), /* epsilon = */ 0.2,
                 asList(p(1, 1.0), p(4, 1.0))},
                 
                // 2. 
                {asList(p(1, 1.0), p(2, 1.1), p(3, 0.9), p(4, 1.0)), /* epsilon = */ 0.05,
                 asList(p(1, 1.0), p(2, 1.1), p(3, 0.9), p(4, 1.0))},
                 
                // 3. 
                {asList(p(1, 1.0), p(2, 1.1), p(3, 1.2), p(4, 1.1), p(5, 1.0)), /* epsilon = */ 0.1,
                 asList(p(1, 1.0), p(3, 1.2), p(5, 1.0))},
                 
                //  4.
                {zigzag(101), 0.1, zigzag(101)},
                 
                //  5.
                {zigzag(101), 1.0, asList(p(0.0, 0.0), p(100.0, 0.0))}
        };
    }
    
    /*
     * creates a line shaped like this: /\/\/\/\/\
     */
    private List<Point> zigzag(int length) {
        List<Point> zigzag = new NamedArrayList<Point>("zig-zag " + length);
        for (int x = 0; x < length; x++) {
            zigzag.add(p(x, x%2));
        }
        return zigzag;
    }

    @Test(dataProvider="series")
    public void shouldReduceSeries(List<Point> inputSeries, double epsilon, List<Point> expectedResult) {
        List<Point> reducedSeries = SeriesReducer.reduce(inputSeries, epsilon);
        Assert.assertEquals(reducedSeries, expectedResult);
    }
    
    @DataProvider(name="invalidEpsilon")
    public Object[][] invalidEpsilonDataProvider() {
        return new Object[][] {{-0.1}, {-2.0}};
    }
    
    @Test(dataProvider="invalidEpsilon", expectedExceptions=IllegalArgumentException.class)
    public void shouldValidateEpsilon(double invalidEpsilon) {
        SeriesReducer.reduce(asList(p(1, 1), p(2, 2), p(3, 3)), invalidEpsilon);
    }
    
    @DataProvider(name="automaticEpsilon")
    public Object[][] automaticEpsilonDataProvier() {
        List<Point> points1 = new NamedArrayList<Point>("y = 2*x + 1");
        for (int x = 0; x < 100; x++) {
            points1.add(p(x, 2*x + 1));;
        }
        
        List<Point> points2 = new NamedArrayList<Point>("y = (x-3)*(x-5)*(x-7)");
        for (double x = 0; x < 10; x += 0.1) {
            points2.add(p(x, (x-3)*(x-5)*(x-7)));
        }
        
        List<Point> points3 = new NamedArrayList<Point>("y = cos(x^2 - 1)");
        for (double x = 0.0; x < 4.0; x += 0.05) {
            points3.add(p(x, Math.cos(x*x + 1)));
        }
        
        List<Point> points4 = new NamedArrayList<Point>("y = 10^6 * 0.9^x");
        for (int x = 0; x < 1000; x++) {
            points4.add(p(x, 10e6 * Math.pow(0.9, x)));
        }
        
        return new Object[][]{
                {points1},
                {points2},
                {points3},
                {points4}};
    }
    
    // this test doesn't assert anything
    // it just checks results of using maximum or average of deviations as the epsilon parameter
    @Test(dataProvider="automaticEpsilon")
    public void testAutomaticEpsilon(NamedArrayList<Point> points) {
        double[] deviations = EpsilonHelper.deviations(points);
        double maxDev = EpsilonHelper.max(deviations);
        double avgDev = EpsilonHelper.avg(deviations);
        
        System.out.println("---------- " + points.getName() + " ----------");
        List<Point> reduced1 = SeriesReducer.reduce(points, maxDev);
        System.out.println("maxDev = " + maxDev + " => " + reduced1.size() + " / " + points.size());
        
        List<Point> reduced2 = SeriesReducer.reduce(points, avgDev);
        System.out.println("avgDev = " + avgDev +" => " + reduced2.size() + " / " + points.size());
    }    
}
