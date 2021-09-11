import { Component, OnInit } from '@angular/core';
import {CalendarOptions} from "@fullcalendar/angular";

@Component({
  selector: 'app-week-calendar',
  templateUrl: './week-calendar.component.html',
  styleUrls: ['./week-calendar.component.scss']
})
export class WeekCalendarComponent implements OnInit {

  constructor() { }

  calendarOptions: CalendarOptions = {
    initialView: 'timeGridWeek',
    locale: 'fr',
    timeZone: 'UTC',
    dayHeaderFormat: {
      weekday: 'long'
    },
    headerToolbar :false,
    editable: true,
    droppable: true, // this allows things to be dropped onto the calendar
    eventColor: '#17a2b8',
    allDaySlot: false,
    weekNumbers: false,
    slotMinTime: "8:00:00",
    slotMaxTime: "20:00:00",
    firstDay:1,
  };
  ngOnInit(): void {


  }

}
