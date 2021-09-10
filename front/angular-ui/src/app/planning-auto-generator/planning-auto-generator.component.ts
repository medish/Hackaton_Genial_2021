import {Component, OnInit, ViewChild} from '@angular/core';
import dayGridPlugin from '@fullcalendar/daygrid';
import timeGridPlugin from '@fullcalendar/timegrid';
import interactionPlugin, {Draggable} from '@fullcalendar/interaction';
import {FullCalendarComponent} from "@fullcalendar/angular";
import uniqid from 'uniqid';

@Component({
  selector: 'app-planning-auto-generator',
  templateUrl: './planning-auto-generator.component.html',
  styleUrls: ['./planning-auto-generator.component.scss']
})
export class PlanningAutoGeneratorComponent implements OnInit {
  options: any;
  constructor() {
  }
  @ViewChild('calendar') calendarComponent: FullCalendarComponent;

  /**
   *
   */
  save(){

    let arrayEvents = this.calendarComponent.getApi().getEvents()
    let arrayPrepared = [];
    console.warn(arrayEvents)
    for (let i = 0; i < arrayEvents.length; i++) {
      let dateStartStr = new Date(arrayEvents[i].startStr)
      var userTimezoneOffset = dateStartStr.getTimezoneOffset() * 60000;
      dateStartStr = new Date(dateStartStr.getTime() + userTimezoneOffset);
      let startTime=dateStartStr.getHours()+":"+dateStartStr.getMinutes()+":"+dateStartStr.getSeconds();
      let dateEndStr=new Date( new Date(arrayEvents[i].endStr).getTime() + userTimezoneOffset)
      let endTime="";
      if(!isNaN(dateEndStr.getTime())){
        endTime = dateEndStr.getHours()+":"+dateEndStr.getMinutes()+":"+dateEndStr.getSeconds();
      }else{
        let defaulthour=dateStartStr.getHours()+1;
        endTime = defaulthour+":"+dateStartStr.getMinutes()+":"+dateStartStr.getSeconds();
      }
      console.warn(dateEndStr.toLocaleDateString())
      console.warn(endTime);
      let dayNumber = dateStartStr.getDay()
      let constraintName = (<HTMLInputElement>document.getElementById('planning-name')).value;

      arrayPrepared.push(
        {
          "id":uniqid(),
          "name":constraintName,
        }
      )
      arrayPrepared.push({
        "elements":{
          "lesson_id":uniqid(),
          "hour":startTime,
          "day":dayNumber
        }
      }
      )
      /*arrayPrepared.push(
        {
          "id": arrayEvents[i].id,
          "title": arrayEvents[i].title,
          "startTime": startTime,
          "endTime": endTime,
          "dayNumber": dayNumber
        },
      )
      */
      console.warn(arrayEvents[i]["id"])
    }
    console.warn(arrayPrepared)

    return arrayPrepared;
  }


  ngOnInit(): void {
    var self = this;
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
      eventTextColor:"black",
      events: [

      ]

    };
  }


}
