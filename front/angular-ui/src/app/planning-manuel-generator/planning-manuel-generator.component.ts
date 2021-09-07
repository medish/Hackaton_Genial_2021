import { Component, OnInit } from '@angular/core';
import dayGridPlugin from '@fullcalendar/daygrid';
import timeGridPlugin from '@fullcalendar/timegrid';
import interactionPlugin, { Draggable } from '@fullcalendar/interaction';


@Component({
  selector: 'app-planning-manuel-generator',
  templateUrl: './planning-manuel-generator.component.html',
  styleUrls: ['./planning-manuel-generator.component.scss']
})
export class PlanningManuelGeneratorComponent implements OnInit {
  options: any;

  constructor() { }

  ngOnInit() {
    let draggableEl = document.getElementById('external-events');
    var self = this;

    // @ts-ignore
    new Draggable(draggableEl, {
      itemSelector: '.fc-event',
      eventData: function(eventEl: any) {
        console.log(eventEl);
        return {
          title: eventEl.innerText
        };
      }
    });
    this.options = {
      plugins: [dayGridPlugin, timeGridPlugin, interactionPlugin],
      defaultDate: '2017-02-01',
      editable: true,
      initialView: 'timeGridWeek',
      locale: 'fr',
      timeZone: 'UTC',
      dayHeaderFormat: {
        weekday: 'long'
      },
      headerToolbar :false,
      droppable: true, // this allows things to be dropped onto the calendar
      eventColor: '#17a2b8',
      allDaySlot: false,
      weekNumbers: false,
    };
  }

}
