import {Component, OnInit, ViewChild} from '@angular/core';
import {Modal} from 'bootstrap';
import dayGridPlugin from '@fullcalendar/daygrid';
import timeGridPlugin from '@fullcalendar/timegrid';
import interactionPlugin, {Draggable} from '@fullcalendar/interaction';
import {FullCalendarComponent} from "@fullcalendar/angular";

@Component({
  selector: 'app-planning-manuel-generator',
  templateUrl: './planning-manuel-generator.component.html',
  styleUrls: ['./planning-manuel-generator.component.scss']
})

export class PlanningManuelGeneratorComponent implements OnInit {
  options: any;
  id_event_clicked: string="";
  calendarApi :any;

  constructor() {
  }

  @ViewChild('calendar') calendarComponent: FullCalendarComponent;

  modelData:{title:string} = {title:''};

  deleteEvent(id_event_clicked:string){
    let calendarApi = this.calendarComponent.getApi();
    let event = calendarApi.getEventById(id_event_clicked);
    event.remove()
  }

  ngOnInit() {
    let draggableEl = document.getElementById('external-events');
    var self = this;
    // @ts-ignore
    new Draggable(draggableEl, {
      itemSelector: '.fc-event',
      eventData: function (eventEl: any) {
        console.warn("From draggable Manuel")
        return {
          title: eventEl.innerText,
          id:Math.random()
        };
      }
    });
    this.options = {
      plugins: [dayGridPlugin, timeGridPlugin, interactionPlugin],
      editable: true,
      initialView: 'timeGridWeek',
      locale: 'fr',
      timeZone: 'UTC',
      dayHeaderFormat: {
        weekday: 'long'
      },
      headerToolbar: false,
      droppable: true, // this allows things to be dropped onto the calendar
      //eventColor: '#17a2b8',
      allDaySlot: false,
      weekNumbers: false,
      slotMinTime: "8:00:00",
      slotMaxTime: "20:00:00",
      firstDay: 1,
      eventClick:  (info)=>{
        console.log(info.event.startStr)
        console.log(info.event.endStr);
        const day = new Date(info.event.startStr).getDay();
        var myModal = new Modal(document.getElementById("modalManuel"), {
          keyboard: false
        });

        // modify the id_event_clicked
        this.id_event_clicked=info.event.id;
        console.warn("Yeahhhh "+this.id_event_clicked)

        this.modelData = {title:info.event.title}
        myModal.show();
      },
      eventAdd: function (addInfo) {
        alert("jjjrj")
        console.log("jdks")
      }

    };
  }
}
