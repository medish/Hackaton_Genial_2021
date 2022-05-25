package core.planning;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

import org.junit.jupiter.api.Test;

import server.models.DateSlot;

public class DateListTest {
    @Test
    public void testDateList() {
        List<DateSlot> list = DateList.build();

        assertEquals(LocalTime.of(8, 0), list.get(0).getStartTime());
        assertEquals(DayOfWeek.MONDAY, list.get(0).getDay());

        assertEquals(LocalTime.of(8, 15), list.get(1).getStartTime());
        assertEquals(DayOfWeek.MONDAY, list.get(1).getDay());

        assertEquals(LocalTime.of(19, 45), list.get(list.size() - 1).getStartTime());
        assertEquals(DayOfWeek.FRIDAY, list.get(list.size() - 1).getDay());

    }
}
