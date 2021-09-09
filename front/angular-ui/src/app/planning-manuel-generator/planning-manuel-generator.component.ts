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

/**
 *
 */
export class PlanningManuelGeneratorComponent implements OnInit {

  options: any;
  id_event_clicked: string="";
  calendarApi :any;

  constructor() {
  }

  @ViewChild('calendar') calendarComponent: FullCalendarComponent;

  modelData:{title:string} = {title:''};

  /**
   * Delete event.
   * @param id_event_clicked
   */
  deleteEvent(id_event_clicked:string){
    let calendarApi = this.calendarComponent.getApi();
    let event = calendarApi.getEventById(id_event_clicked);
    event.remove()
  }

  getAllEvents(){
     return this.calendarComponent.getApi().getEvents();
  }

  prepareVerification(){
    let arrayEvents= this.getAllEvents();
    console.warn(arrayEvents)
    for(let i =0;i<arrayEvents.length;i++){
        console.warn(arrayEvents[i]["id"])
        alert(arrayEvents[i]["id"])
    }
  }


  ngOnInit() {
    let draggableEl = document.getElementById('external-events');
    var self = this;

    // @ts-ignore
    new Draggable(draggableEl, {
      itemSelector: '.fc-event',
      eventData: function (eventEl: any) {
        console.warn("From draggable Manuel")
        let eventInitialColors={td:"#0d6efd",cours:"#dc3545",tp:"#ffc107"}
        let target_color= eventEl.innerText.toLowerCase()
        return {
          title: eventEl.innerText,
          id:Math.random(),
          color:eventInitialColors[target_color],
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
      weekends:true,
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

        this.prepareVerification()
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
