package core.planning;

import core.planning.DateList;
import org.junit.jupiter.api.Test;
import server.models.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

public class DateListTest {
    @Test
    public void testDateList(){
        List<Date> list = DateList.build();

        assertEquals(LocalTime.of(8,0),list.get(0).getHour());
        assertEquals(DayOfWeek.MONDAY,list.get(0).getDay());

        assertEquals(LocalTime.of(8,15),list.get(1).getHour());
        assertEquals(DayOfWeek.MONDAY,list.get(1).getDay());

        assertEquals(LocalTime.of(19,45),list.get(list.size() - 1).getHour());
        assertEquals(DayOfWeek.FRIDAY,list.get(list.size() - 1).getDay());

    }
}
