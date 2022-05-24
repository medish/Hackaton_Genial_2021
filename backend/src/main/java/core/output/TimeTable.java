package core.output;

import server.models.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TimeTable implements IOutput, IInput {

    private List<DateSlot> timeslotList;
    private List<Room> roomList;
    private List<CourseSlot> outputList;

    public TimeTable() {
    }

    public TimeTable(List<DateSlot> timeslotList, List<Room> roomList, List<CourseSlot> outputList) {
        this.timeslotList = timeslotList;
        this.roomList = roomList;
        this.outputList = outputList;
    }

    public List<DateSlot> getDateList() {
        return timeslotList;
    }

    public List<Room> getRoomList() {
        return roomList;
    }

    public List<CourseSlot> getOutputList() {
        return outputList;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        Map<DateId, Map<Room, List<CourseSlot>>> lessonMap = outputList.stream()
                .filter(lesson -> lesson.getDate().getDateId() != null && lesson.getRoom() != null).collect(Collectors
                        .groupingBy((lesson) -> lesson.getDate().getDateId(), Collectors.groupingBy(CourseSlot::getRoom)));
        stringBuilder.append("|            | " + roomList.stream().map(room -> String.format("%-10s", room.getNumber()))
                .collect(Collectors.joining(" | ")) + " |\n");
        stringBuilder.append("|" + "------------|".repeat(roomList.size() + 1) + "\n");
        for (Date timeslot : timeslotList) {
            List<List<CourseSlot>> cellList = roomList.stream().map(room -> {
                Map<Room, List<CourseSlot>> byRoomMap = lessonMap.get(timeslot.getDateId());
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
                    + String.format("%-10s", timeslot.getDay().toString().substring(0, 3) + " " + timeslot.getHour())
                    + " | "
                    + cellList.stream()
                            .map(cellLessonList -> String.format("%-10s",
                                    cellLessonList.stream().map(CourseSlot::getCourse).map(Course::getName)
                                            .collect(Collectors.joining(", "))))
                            .collect(Collectors.joining(" | "))
                    + " |\n");
            stringBuilder.append("|            | "
                    + cellList.stream()
                            .map(cellLessonList -> String.format("%-10s",
                                    cellLessonList.stream()
                                            .map(output -> output.getProfessors() == null
                                                    ? new Professor(null, "null", null, null, false)
                                                    : output.getProfessors().iterator().next())
                                            .map(Professor::getName).collect(Collectors.joining(", "))))
                            .collect(Collectors.joining(" | "))
                    + " |\n");
            stringBuilder.append("|            | " + cellList.stream()
                    .map(cellLessonList -> String.format("%-10s",
                            cellLessonList.stream().map(output -> output.getDegrees().iterator().next())
                                    .map(Degree::getName).collect(Collectors.joining(", "))))
                    .collect(Collectors.joining(" | ")) + " |\n");
            stringBuilder.append("|" + "------------|".repeat(roomList.size() + 1) + "\n");
        }
        List<CourseSlot> unassignedLessons = outputList.stream()
                .filter(lesson -> lesson.getDate().getDateId() == null || lesson.getRoom() == null)
                .collect(Collectors.toList());
        if (!unassignedLessons.isEmpty()) {
            stringBuilder.append("");
            stringBuilder.append("Unassigned lessons");
            for (CourseSlot lesson : unassignedLessons) {
                stringBuilder
                        .append("  " + lesson.getCourse().getName() + " - " + lesson.getProfessors().iterator().next()
                                + " - " + lesson.getCourse().getDegrees().iterator().next() + "\n");
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
        return courseGroups.stream().map(group -> new CourseSlot(group, )).collect(Collectors.toList());
    }

}
