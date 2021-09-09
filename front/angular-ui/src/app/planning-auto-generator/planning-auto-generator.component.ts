import {Component, OnInit} from '@angular/core';
import dayGridPlugin from '@fullcalendar/daygrid';
import timeGridPlugin from '@fullcalendar/timegrid';
import interactionPlugin, {Draggable} from '@fullcalendar/interaction';
import {jsPDF} from 'jspdf';
import html2canvas from "html2canvas";


@Component({
  selector: 'app-planning-auto-generator',
  templateUrl: './planning-auto-generator.component.html',
  styleUrls: ['./planning-auto-generator.component.scss']
})
export class PlanningAutoGeneratorComponent implements OnInit {
  options: any;
  constructor() {
  }
  isCollapsedconstraints = true;
  isCollapsedArray = true;
  message = 'expanded';


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
        {
          daysOfWeek: ['3'],
          startTime: '13:00:00',
          endTime: '14:00:00',
          color: "#7bcce4",
          title:"Cours JAva",
          description:"lorem impsum"
        },
        {
          daysOfWeek:[1],
          startTime: '10:00:00',
          endTime: '14:00:00',
          color: "red",
          title:"Cours C++",
          description:"lorem impsum"
        },
        {
          daysOfWeek:[4],
          startTime: '08:00:00',
          endTime: '11:00:00',
          color: "green",
          title:"Lorem ipsum"
        },
        {
          daysOfWeek:[5],
          startTime: '15:00:00',
          endTime: '17:00:00',
          color: "green"
        }
      ]

    };

    function exportToPdf() {
      console.log("start exporting to pdf........");
      var w = document.getElementById("calendar").offsetWidth;
      var h = document.getElementById("calendar").offsetHeight;
      html2canvas(document.getElementById("calendar")).then(function (canvas) {
        var img = canvas.toDataURL("image/calendar");
        var doc = new jsPDF('l', 'px', [w, h]);
        doc.addImage(img, 'JPEG', 0, 0, w, h);
        doc.save("calendrier.pdf");
      })
    }
    document.getElementById("PDFexport").addEventListener("click", exportToPdf);
  }
}
