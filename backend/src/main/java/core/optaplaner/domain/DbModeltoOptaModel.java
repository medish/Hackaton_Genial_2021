package core.optaplaner.domain;

import java.util.ArrayList;
import java.util.List;

import server.models.Lesson;
import server.models.Room;

public class DbModeltoOptaModel {

    public TimeTableOptaPlaner getTimeTableOptaPlaner() {

        TempFromDataBase tempFromDataBase = new TempFromDataBase();

        List<Lesson> lessonsList = tempFromDataBase.getAllLessons();
        List<LessonOptaPlaner> lessonOptaPlanerList = convertLessons(lessonsList);

        List<Room> roomsList = tempFromDataBase.getAllRooms();

        TimeTableOptaPlaner timeTableOptaPlaner = new TimeTableOptaPlaner(tempFromDataBase.getAllRooms(),
                lessonOptaPlanerList);

        return timeTableOptaPlaner;
    }

    private List<LessonOptaPlaner> convertLessons(List<Lesson> lessonsList) {
        ArrayList<LessonOptaPlaner> list = new ArrayList<LessonOptaPlaner>();

        for (Lesson lesson : lessonsList) {
            LessonOptaPlaner lo = new LessonOptaPlaner().fromInput(lesson);
            list.add(lo);
        }
        return list;
    }

}
