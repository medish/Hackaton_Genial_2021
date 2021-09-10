package core.planning;

import server.models.Date;
import server.models.DateId;

import java.lang.reflect.Array;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class DateList {
    public static List<Date> build() {
        List<Date> timeslotList = new ArrayList<>();

        for (DayOfWeek day : DayOfWeek.values()) {
            if (day != DayOfWeek.SUNDAY && day != DayOfWeek.SATURDAY) {
                for (int h = 8; h <= 19; ++h) {
                    for (int m = 0; m <= 45; m += 15) {

                        timeslotList.add(new Date(new DateId(day, LocalTime.of(h, m))));
                    }
                }
            }
        }

        return timeslotList;
    }
}
