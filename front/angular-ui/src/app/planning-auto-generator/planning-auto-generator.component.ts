import {Component, OnInit, ViewChild} from '@angular/core';
import dayGridPlugin from '@fullcalendar/daygrid';
import timeGridPlugin from '@fullcalendar/timegrid';
import interactionPlugin, {Draggable} from '@fullcalendar/interaction';
import {FullCalendarComponent} from "@fullcalendar/angular";
import uniqid from 'uniqid';
import { DataInterfaceService } from '../services/data-interface.service';
import { createEvents } from 'ics';

@Component({
  selector: 'app-planning-auto-generator',
  templateUrl: './planning-auto-generator.component.html',
  styleUrls: ['./planning-auto-generator.component.scss']
})
export class PlanningAutoGeneratorComponent implements OnInit {
  options: any;
  constructor(private back:DataInterfaceService) {
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

  getAllEvents(){
    return this.calendarComponent.getApi().getEvents();
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
  download(filename, input) {
    var element = document.createElement('a');
    element.setAttribute('href', 'data:text/plain;charset=utf-8, '+encodeURIComponent(input));
    element.setAttribute('download', filename);
    document.body.appendChild(element);
    element.click();
    document.body.removeChild(element);
  }

  exportToICS() {
    let allEvents = this.getAllEvents();
    let events = [];
    for (let i = 0; i < allEvents.length; i++) {
      let event = allEvents[i];
      console.log(event.extendedProps);
      let start = [
        event.start.getFullYear(),
        event.start.getMonth()+1,
        event.start.getDate(),
        event.start.getUTCHours(),
        event.start.getMinutes()];
      let hours = Math.floor(event.extendedProps["duration"]/60);
      let minutes = event.extendedProps["duration"]%60;
      console.log(event.extendedProps["duration"]);
      if (isNaN(hours))
        hours = 0;
      if (isNaN(minutes))
        minutes = 0;
      events.push({
        start : start,
        duration : {hours :  hours, minutes : minutes},
        title : event.title,
        location : event.extendedProps["room"],
        description : event.extendedProps["prof"]
      });
    }
    const {error, value} = createEvents(events);
    if (error)
      console.log(error);
    else {
      var filename = "calendar.ics";
      this.download(filename, value);
    }
  }
  generatePlanning(){
    this.back.generatePlanning(planning=>{      
      console.log("gen retour",planning);
    })
  }


}
