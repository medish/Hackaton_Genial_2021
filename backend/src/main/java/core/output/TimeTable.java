package core.output;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import server.models.*;

public class TimeTable implements IOutput, IInput {

    private List<Date> timeslotList;
    private List<Room> roomList;
    private List<Output> outputList;

    public TimeTable() {
    }

    public TimeTable(List<Date> timeslotList, List<Room> roomList, List<Output> outputList) {
        this.timeslotList = timeslotList;
        this.roomList = roomList;
        this.outputList = outputList;
    }

    public List<Date> getDateList() {
        return timeslotList;
    }

    public List<Room> getRoomList() {
        return roomList;
    }

    public List<Output> getOutputList() {
        return outputList;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        Map<DateId, Map<Room, List<Output>>> lessonMap = outputList.stream()
                .filter(lesson -> lesson.getDate().getDateId() != null && lesson.getRoom() != null).collect(Collectors
                        .groupingBy((lesson) -> lesson.getDate().getDateId(), Collectors.groupingBy(Output::getRoom)));
        stringBuilder.append("|            | " + roomList.stream().map(room -> String.format("%-10s", room.getNumber()))
                .collect(Collectors.joining(" | ")) + " |\n");
        stringBuilder.append("|" + "------------|".repeat(roomList.size() + 1) + "\n");
        for (Date timeslot : timeslotList) {
            List<List<Output>> cellList = roomList.stream().map(room -> {
                Map<Room, List<Output>> byRoomMap = lessonMap.get(timeslot.getDateId());
                if (byRoomMap == null) {
                    return Collections.<Output>emptyList();
                }
                List<Output> cellLessonList = byRoomMap.get(room);
                if (cellLessonList == null) {
                    return Collections.<Output>emptyList();
                }
                return cellLessonList;
            }).collect(Collectors.toList());

            stringBuilder.append("| "
                    + String.format("%-10s", timeslot.getDay().toString().substring(0, 3) + " " + timeslot.getHour())
                    + " | "
                    + cellList.stream()
                    .map(cellLessonList -> String.format("%-10s",
                            cellLessonList.stream().map(Output::getCourse).map(Course::getName)
                                    .collect(Collectors.joining(", "))))
                    .collect(Collectors.joining(" | "))
                    + " |\n");
            stringBuilder.append("|            | " + cellList.stream()
                    .map(cellLessonList -> String.format("%-10s",
                            cellLessonList.stream().map(output -> output.getProfessors().iterator().next())
                                    .map(Professor::getName).collect(Collectors.joining(", "))))
                    .collect(Collectors.joining(" | ")) + " |\n");
            stringBuilder.append("|            | " + cellList.stream()
                    .map(cellLessonList -> String.format("%-10s",
                            cellLessonList.stream().map(output -> output.getDegrees().iterator().next())
                                    .map(Degree::getName).collect(Collectors.joining(", "))))
                    .collect(Collectors.joining(" | ")) + " |\n");
            stringBuilder.append("|" + "------------|".repeat(roomList.size() + 1) + "\n");
        }
        List<Output> unassignedLessons = outputList.stream()
                .filter(lesson -> lesson.getDate().getDateId() == null || lesson.getRoom() == null)
                .collect(Collectors.toList());
        if (!unassignedLessons.isEmpty()) {
            stringBuilder.append("");
            stringBuilder.append("Unassigned lessons");
            for (Output lesson : unassignedLessons) {
                stringBuilder
                        .append("  " + lesson.getCourse().getName() + " - " + lesson.getProfessors().iterator().next()
                                + " - " + lesson.getCourse().getDegrees().iterator().next() + "\n");
            }
        }
        return stringBuilder.toString();
    }


    /**
     * Build an output list from a given lesson list
     * @param lessonList {@link Lesson}
     * @return List of output {@link Output}
     */
    public static List<Output> buildListOutput(List<Lesson> lessonList) {
        return lessonList.stream().map(lesson ->
                new Output(null, null, lesson)
        ).collect(Collectors.toList());
    }

}
