package JavaCourseITAcademy.partOfRobot;

public enum PartOfRobot {
    RIGHT_ARM(0),
    LEFT_ARM(1),
    RIGHT_LEG(2),
    LEFT_LEG(3),
    BODY(4),
    HEAD(5);

    private final int value;

    PartOfRobot(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
