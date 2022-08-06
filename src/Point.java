import java.util.List;

public class Point {
    protected String name;
    protected List<Double> coordinates;

    public Point(String name, List<Double> coordinates) {
        this.name = name;
        this.coordinates = coordinates;
    }

    @Override
    public String toString() {
        return name + ": {" + coordinates + "}";
    }
}
