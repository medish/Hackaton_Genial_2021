import { Injectable } from '@angular/core';
import { EventApi } from '@fullcalendar/angular';
import { createEvents } from 'ics';

@Injectable({
  providedIn: 'root'
})
export class ExportService {

  constructor() { }

  exportToICS(allEvents:EventApi[]) {
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

  download(filename, input) {
    var element = document.createElement('a');
    element.setAttribute('href', 'data:text/plain;charset=utf-8, '+encodeURIComponent(input));
    element.setAttribute('download', filename);
    document.body.appendChild(element);
    element.click();
    document.body.removeChild(element);
  }
}
