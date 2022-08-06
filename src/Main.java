import java.util.*;

public class Main {
    public static void main(String[] args) {
        String fileName = "C:\\Users\\mateu\\Desktop\\k-means_s23711\\k-means_s23711\\src\\iris.data";
        int k = 5;

        //initialisation variant: Random partition
        List<Group> groups = Utility.createGroups(k);
        List<Point> points = Utility.getPoints(fileName);
        Collections.shuffle(points);
        int pointSize = points.get(0).coordinates.size();


        List<Group> groupsWithPoints = Utility.addPointsToGroup(points, groups);
        List<Point> centroidList = Utility.getCentroidList(groupsWithPoints, pointSize);

        for (Group group : groupsWithPoints)
            System.out.println(group);
        for (Point point : centroidList)
            System.out.println(point);
        System.out.println(Utility.getEMap(groupsWithPoints, centroidList));

        System.out.println("\n\nK-means:");

        boolean check = true;
        int counter = 1;

        while (check) {
            System.out.println("\nIteration: " + counter++);
            check = false;

            List<Group> newGroup = Utility.createGroups(k);
            for (Group group : groupsWithPoints) {
                String groupName = group.name;
                for (Point point : group.list) {
                    List<Double> list = new ArrayList<>();
                    for (Point centroid : centroidList) {
                        list.add(Utility.distance(point, centroid));
                    }
                    int minId = 0;
                    for (int i = 0; i <= list.size() - 2; i++) {
                        if (list.get(minId) > list.get(i + 1)) {
                            minId = i + 1;
                        }
                    }
                    String newGroupName = newGroup.get(minId).name;
                    newGroup.get(minId).list.add(point);
                    if (!Objects.equals(groupName, newGroupName)) check = true;
                }
            }
            groupsWithPoints = newGroup;

            centroidList = Utility.getCentroidList(groupsWithPoints, pointSize);
            HashMap<String, Double> entropyMap = Utility.getEMap(groupsWithPoints, centroidList);
            for (Group group : groupsWithPoints) {
                System.out.println(group);
            }
            for (Point centroid : centroidList) {
                System.out.println(centroid);
            }
            System.out.println(entropyMap);
        }
    }
}
