import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Utility {
    public static List<Group> createGroups(int k) {
        List<Group> groups = new ArrayList<>();
        for (char i = 'A'; i <= 'A' + k - 1; i++) {
            groups.add(new Group(String.valueOf(i), new ArrayList<>()));
        }
        System.out.println(groups);
        return groups;
    }
    public static List<Point> getPoints(String fileName) {
        List<Point> points = new ArrayList<>();
        int count = 1;
        try {
            List<String> allLines = Files.readAllLines(Paths.get(fileName));
            for (String line : allLines) {
                List<String> read = new ArrayList<>(List.of(line.split(",")));
                read.remove(read.size() - 1);

                List<Double> coords = new ArrayList<>();
                for (String s : read) { coords.add(Double.parseDouble(s)); }

                points.add(new Point("P" + (count++), coords));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return points;
    }
    public static List<Group> addPointsToGroup(List<Point> points, List<Group> groups) {
        int groupId = 0;
        for (Point point : points) {
            if (groupId > groups.size() - 1)
                groupId = 0;
            groups.get(groupId).list.add(point);
            groupId++;
        }
        return groups;
    }
    public static List<Point> getCentroidList(List<Group> groups, int size) {
        List<Point> centroidList = new ArrayList<>();
        for (int i = 0; i <= groups.size() - 1; i++) {
            centroidList.add(new Point("Centroid" + groups.get(i).name, new ArrayList<>()));

            for (int j = 0; j <= size - 1; j++) {
                Double sum = 0.0;
                for (Point point : groups.get(i).list)
                    sum += point.coordinates.get(j);
                centroidList.get(i).coordinates.add(sum / (double)(groups.get(i).list.size()));
            }
        }
        return centroidList;
    }
    public static Double distance(Point point, Point centroid) {
        double sum = 0.0;
        for (int i = 0; i <= point.coordinates.size() - 1; i++) {
            sum += Math.pow(point.coordinates.get(i) - centroid.coordinates.get(i), 2.0);
        }
        return sum;
    }
    public static HashMap<String, Double> getEMap(List<Group> groups, List<Point> centroidList) {
        HashMap<String, Double> entropyMap = new HashMap<>();
        for (int i = 0; i <= groups.size() - 1; i++) {
            double sum = 0.0;
            for (Point point : groups.get(i).list) {
                sum += distance(point, centroidList.get(i));
            }
            entropyMap.put("E" + groups.get(i).name, sum);
        }
        return entropyMap;
    }
}
