package core.output;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;

import core.optaplaner.domain.LessonOptaPlaner;
import server.models.Input;
import server.models.Professor;
import server.models.Room;

public class TimeTable implements Output, Input {

    private List<Timeslot> timeslotList;
    private List<Room> roomList;
    private List<LessonOptaPlaner> lessonList;

    private HardSoftScore score;

    public TimeTable() {
    }

    public TimeTable(List<Timeslot> timeslotList, List<Room> roomList, List<LessonOptaPlaner> lessonList) {
        this.timeslotList = timeslotList;
        this.roomList = roomList;
        this.lessonList = lessonList;
    }

    public List<Timeslot> getTimeslotList() {
        return timeslotList;
    }

    public List<Room> getRoomList() {
        return roomList;
    }

    public List<LessonOptaPlaner> getLessonList() {
        return lessonList;
    }

    public HardSoftScore getScore() {
        return score;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        Map<Timeslot, Map<Room, List<LessonOptaPlaner>>> lessonMap = lessonList.stream()
                .filter(lesson -> lesson.getTimeslot() != null && lesson.getRoom() != null).collect(Collectors
                        .groupingBy(LessonOptaPlaner::getTimeslot, Collectors.groupingBy(LessonOptaPlaner::getRoom)));
        stringBuilder.append("|            | " + roomList.stream().map(room -> String.format("%-10s", room.getNumber()))
                .collect(Collectors.joining(" | ")) + " |\n");
        stringBuilder.append("|" + "------------|".repeat(roomList.size() + 1) + "\n");
        for (Timeslot timeslot : timeslotList) {
            List<List<LessonOptaPlaner>> cellList = roomList.stream().map(room -> {
                Map<Room, List<LessonOptaPlaner>> byRoomMap = lessonMap.get(timeslot);
                if (byRoomMap == null) {
                    return Collections.<LessonOptaPlaner>emptyList();
                }
                List<LessonOptaPlaner> cellLessonList = byRoomMap.get(room);
                if (cellLessonList == null) {
                    return Collections.<LessonOptaPlaner>emptyList();
                }
                return cellLessonList;
            }).collect(Collectors.toList());

            stringBuilder.append("| "
                    + String.format(
                            "%-10s", timeslot.getDayOfWeek().toString().substring(0, 3) + " " + timeslot.getStartTime())
                    + " | "
                    + cellList.stream()
                            .map(cellLessonList -> String.format("%-10s",
                                    cellLessonList.stream().map(LessonOptaPlaner::getSubject)
                                            .collect(Collectors.joining(", "))))
                            .collect(Collectors.joining(" | "))
                    + " |\n");
            stringBuilder.append("|            | "
                    + cellList.stream()
                            .map(cellLessonList -> String.format("%-10s",
                                    cellLessonList.stream().map(LessonOptaPlaner::getTeacher).map(Professor::getName)
                                            .collect(Collectors.joining(", "))))
                            .collect(Collectors.joining(" | "))
                    + " |\n");
            stringBuilder.append("|            | "
                    + cellList.stream()
                            .map(cellLessonList -> String.format("%-10s",
                                    cellLessonList.stream().map(LessonOptaPlaner::getStudentGroup)
                                            .collect(Collectors.joining(", "))))
                            .collect(Collectors.joining(" | "))
                    + " |\n");
            stringBuilder.append("|" + "------------|".repeat(roomList.size() + 1) + "\n");
        }
        List<LessonOptaPlaner> unassignedLessons = lessonList.stream()
                .filter(lesson -> lesson.getTimeslot() == null || lesson.getRoom() == null)
                .collect(Collectors.toList());
        if (!unassignedLessons.isEmpty()) {
            stringBuilder.append("");
            stringBuilder.append("Unassigned lessons");
            for (LessonOptaPlaner lesson : unassignedLessons) {
                stringBuilder.append("  " + lesson.getSubject() + " - " + lesson.getTeacher() + " - "
                        + lesson.getStudentGroup() + "\n");
            }
        }
        return stringBuilder.toString();
    }

}
