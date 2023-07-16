package JavaCourseITAcademy.generalPartsList;

import JavaCourseITAcademy.partOfRobot.PartOfRobot;

import java.util.LinkedList;
import java.util.List;

public class GeneralPartsList {
    private final List<PartOfRobot> parts_list;

    public GeneralPartsList() {
        parts_list = new LinkedList<>();
    }

    public List<PartOfRobot> getParts_list() {
        synchronized (parts_list) {
            return parts_list;
        }
    }

    public PartOfRobot getPart(PartOfRobot part) {
        synchronized (parts_list) {
            if (parts_list.contains(part)) {
                PartOfRobot tmp = part;
                parts_list.remove(part);
                return tmp;
            } else
                return null;
        }
    }

    public void addPart(PartOfRobot part) {
        synchronized (parts_list) {
            parts_list.add(part);
            System.out.println("В общем листе: " + parts_list);
        }
    }
}
