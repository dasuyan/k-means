import java.util.List;

public class Group {
    protected String name;
    protected List<Point> list;

    public Group(String name, List<Point> list) {
        this.name = name;
        this.list = list;
    }

    @Override
    public String toString() {
        return "Group: " + name + ": [" + list + "]";
    }
}
