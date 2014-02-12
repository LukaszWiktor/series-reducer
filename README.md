series-reducer
==============

A java implementation of Ramer–Douglas–Peucker algorithm for reducing number of points in data series.


### How it works?###

The algorithm removes points from the series while maintaining the shape of the curve. See an example below:

![example 1](https://raw.github.com/wiki/LukaszWiktor/series-reducer/examples/example1.png)

### How to use it?###

All you need to do is to invoke `SeriesReducer.reduce(points, epsilon)` where `points` is an ordered list of points and `epsilon` defines a margin within which points can be removed.

Data presented on the example charts were produced by the following code snippet:
```java
List<Point> points = new ArrayList<Point>();
for (double x = 0; x < 4; x += 0.05) {
    points.add(new MyPoint(x, Math.cos(x*x - 1)));
}
List<Point> reduced = SeriesReducer.reduce(points, 0.01);
```