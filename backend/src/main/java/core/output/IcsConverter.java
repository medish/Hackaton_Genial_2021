package core.output;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

import biweekly.Biweekly;
import biweekly.ICalendar;
import biweekly.component.VEvent;
import biweekly.property.Summary;
import server.models.Output;

public class IcsConverter {

    public static void convert(TimeTable output) {

        System.out.println("=====converting");
        // System.out.println(output.getLessonList().size());
        // System.out.println(output.getRoomList().size());
        // System.out.println(output.getDateList().size());

        ICalendar ical = new ICalendar();

        for (Output lesson : output.getOutputList()) {
            VEvent event = createEvent(lesson);
            ical.addEvent(event);
        }

        String resultStr = Biweekly.write(ical).go();
        System.out.println(resultStr);

    }

    public static VEvent createEvent(Output lesson) {
        VEvent event = new VEvent();

        // set up the start and end datetime of event
        LocalDateTime firstDayOfweek = LocalDateTime.now().with(DayOfWeek.MONDAY);
        LocalDateTime startDateTime = firstDayOfweek.with(TemporalAdjusters.nextOrSame(lesson.getDay()))
                .with(lesson.getStartTime());
        LocalDateTime endDateTime = LocalDateTime.now().with(TemporalAdjusters.nextOrSame(lesson.getDay()))
                .with(lesson.getEndTime());
        // System.out.println(startDateTime);
        // System.out.println(endDateTime);
        event.setDateStart(Date.from(startDateTime.atZone(ZoneId.systemDefault()).toInstant()));
        event.setDateEnd(Date.from(endDateTime.atZone(ZoneId.systemDefault()).toInstant()));

        String location = lesson.getRoom().toString();
        // System.out.println("Room: " + location);
        event.setLocation(location);

        String studentgroup = lesson.getDegrees().iterator().next().getName();
        // System.out.println(studentgroup);
        event.setDescription(studentgroup);

        String teacher = lesson.getProfessors().iterator().next().getName();
        // System.out.println(teacher);
        String subject = lesson.getCourse() + " | " + teacher;
        Summary summary = event.setSummary(subject);

        // System.out.println(subject);
        summary.setLanguage("fr-fr");
        event.setSummary(summary);

        // Recurrence recur = new
        // Recurrence.Builder(Frequency.WEEKLY).interval(2).build();
        // event.setRecurrenceRule(recur);

        // System.out.println(event.toString());
        return event;
    }

}
