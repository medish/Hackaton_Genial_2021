package core.output;

import server.models.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TimeTable implements IOutput, IInput {

    private List<DateSlot> dateSlots;
    private List<Room> rooms;
    private List<CourseSlot> courseSlots;

    public TimeTable() {
    }

    public TimeTable(List<DateSlot> dateSlots, List<Room> rooms, List<CourseSlot> courseSlots) {
        this.dateSlots = dateSlots;
        this.rooms = rooms;
        this.courseSlots = courseSlots;
    }

    public List<DateSlot> getDateSlots() {
        return dateSlots;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public List<CourseSlot> getCourseSlots() {
        return courseSlots;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        Map<DateSlot, Map<Room, List<CourseSlot>>> dateSlotMap = courseSlots.stream()
                .filter(courseSlot -> courseSlot.getDateSlot() != null && courseSlot.getRoom() != null)
                .collect(Collectors.groupingBy(CourseSlot::getDateSlot, Collectors.groupingBy(CourseSlot::getRoom)));
        stringBuilder.append("|            | "
                + rooms.stream().map(room -> String.format("%-10s", room.getName())).collect(Collectors.joining(" | "))
                + " |\n");
        stringBuilder.append("|" + "------------|".repeat(rooms.size() + 1) + "\n");
        for (DateSlot dateSlot : dateSlots) {
            Map<Room, List<CourseSlot>> byRoomMap = dateSlotMap.get(dateSlot);
            List<List<CourseSlot>> cellList = rooms.stream().map(room -> {
                if (byRoomMap == null) {
                    return Collections.<CourseSlot>emptyList();
                }
                List<CourseSlot> cellLessonList = byRoomMap.get(room);
                if (cellLessonList == null) {
                    return Collections.<CourseSlot>emptyList();
                }
                return cellLessonList;
            }).collect(Collectors.toList());

            stringBuilder.append("| "
                    + String.format(
                            "%-10s", dateSlot.getDay().toString().substring(0, 3) + " " + dateSlot.getStartTime())
                    + " | "
                    + cellList.stream()
                            .map(cellLessonList -> String.format("%-10s",
                                    cellLessonList.stream().map(CourseSlot::getMajorCourse).map(MajorCourse::getCourse).map(Course::getName)
                                            .collect(Collectors.joining(", "))))
                            .collect(Collectors.joining(" | "))
                    + " |\n");
            stringBuilder.append("|            | " + cellList.stream()
                    .map(cellLessonList -> String.format("%-10s",
                            cellLessonList.stream()
                                    .map(output -> output.getProfessors() == null ? new Professor()
                                            : output.getProfessors().iterator().next())
                                    .map(Professor::getLastName).collect(Collectors.joining(", "))))
                    .collect(Collectors.joining(" | ")) + " |\n");
            stringBuilder.append("|            | "
                    + cellList.stream()
                            .map(cellLessonList -> String.format("%-10s",
                                    cellLessonList.stream().map(output -> output.getDegree()).map(Degree::getName)
                                            .collect(Collectors.joining(", "))))
                            .collect(Collectors.joining(" | "))
                    + " |\n");
            stringBuilder.append("|" + "------------|".repeat(rooms.size() + 1) + "\n");
        }
        List<CourseSlot> unassignedLessons = courseSlots.stream()
                .filter(lesson -> lesson.getDateSlot() == null || lesson.getRoom() == null)
                .collect(Collectors.toList());
        if (!unassignedLessons.isEmpty()) {
            stringBuilder.append("");
            stringBuilder.append("Unassigned lessons");
            for (CourseSlot lesson : unassignedLessons) {
                stringBuilder.append("  " + lesson.getMajorCourse().getCourse().getName() + " - "
                        + lesson.getProfessors().iterator().next() + " - " + lesson.getMajorCourse().getCourse().getDegree() + "\n");
            }
        }

        return stringBuilder.toString();

    }

    /**
     * Build an output list from a given lesson list
     * 
     * @param courseGroups {@link CourseGroup}
     * @return List of output {@link CourseSlot}
     */
    public static List<CourseSlot> buildListSlots(List<CourseGroup> courseGroups) {
        return courseGroups.stream().map(courseGroup -> new CourseSlot(courseGroup, null, null, null, null)).toList();
    }

}
