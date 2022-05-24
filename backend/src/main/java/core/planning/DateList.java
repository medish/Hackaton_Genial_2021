package core.planning;

import server.models.DateSlot;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class DateList {
    public static List<DateSlot> build() {
        List<DateSlot> timeslotList = new ArrayList<>();

        for (DayOfWeek day : DayOfWeek.values()) {
            if (day != DayOfWeek.SUNDAY && day != DayOfWeek.SATURDAY) {
                for (int h = 8; h <= 19; ++h) {
                    for (int m = 0; m <= 45; m += 15) {

                        timeslotList.add(new DateSlot(day, LocalTime.of(h, m)));
                    }
                }
            }
        }

        return timeslotList;
    }
}
