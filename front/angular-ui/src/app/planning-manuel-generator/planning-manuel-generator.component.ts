import {Component, OnInit, ViewChild} from '@angular/core';
import {Modal} from 'bootstrap';
import dayGridPlugin from '@fullcalendar/daygrid';
import timeGridPlugin from '@fullcalendar/timegrid';
import interactionPlugin, {Draggable} from '@fullcalendar/interaction';
import {DataInterfaceService} from '../services/data-interface.service';
import {Class, Room, Degree, Teacher} from '../model/datastore/datamodel';
import {FormBuilder, FormGroup} from '@angular/forms';
import {FullCalendarComponent} from "@fullcalendar/angular";


@Component({
  selector: 'app-planning-manuel-generator',
  templateUrl: './planning-manuel-generator.component.html',
  styleUrls: ['./planning-manuel-generator.component.scss']
})

export class PlanningManuelGeneratorComponent implements OnInit {
  options: any;
  roomsForm: FormGroup;
  classForm: FormGroup;
  teacherForm: FormGroup;
  degreeForm: FormGroup;
  roomsList: Room[] = [];
  classes: Class[] = [];
  teachers: Teacher[] = [];
  degrees: Degree[] = [];
  that = this;
  id_event_clicked: string = "";
  calendarApi: any;

  constructor(private dataService: DataInterfaceService, private fb: FormBuilder) {
  }

  @ViewChild('calendar') calendarComponent: FullCalendarComponent;

  modelData: { title: string } = {title: ''};

  /**
   * Delete event.
   *
   * @param id_event_clicked
   */
  deleteEvent(id_event_clicked: string) {
    let calendarApi = this.calendarComponent.getApi();
    let event = calendarApi.getEventById(id_event_clicked);
    event.remove()
  }

  /**
   * Get All Events.
   * @return Array
   */
  getAllEvents() {
    return this.calendarComponent.getApi().getEvents();
  }

  /**
   * Get current Planning
   */
  prepareVerification() {

    let arrayEvents = this.getAllEvents();
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

      arrayPrepared.push(
        {
          "id": arrayEvents[i].id,
          "title": arrayEvents[i].title,
          "startTime": startTime,
          "endTime": endTime,
          "dayNumber": dayNumber
        }
      )
      console.warn(arrayEvents[i]["id"])
    }
    console.warn(arrayPrepared)

    return arrayPrepared;
  }


  ngOnInit() {
    let draggableEl = document.getElementById('external-events');

    this.roomsForm = this.fb.group({
      roomControl: ['Choisir la salle ou l\'amphi']
    })
    this.classForm = this.fb.group({
      classControl: ['Choisir la classe concernée']
    })
    this.teacherForm = this.fb.group({
      teacherControl: ['Choisir le professeur']
    })
    this.degreeForm = this.fb.group({
      degreeControl: ['Choose a degree']
    });
    let that = this;
    this.dataService.fetchAllRooms(this.onRoomsReceived, that);
    this.dataService.fetchAllClasses(this.onClassesReceived, that);
    this.dataService.fetchAllTeachers(this.onTeachersReceived, that);
    this.dataService.fetchAllDegrees(this.onDegreesReceived, that);

    var self = this;

    // @ts-ignore
    new Draggable(draggableEl, {
      itemSelector: '.fc-event',
      eventData: function (eventEl: any) {
        console.warn("From draggable Manuel")
        let eventInitialColors = {td: "#0d6efd", cours: "#dc3545", tp: "#ffc107"}
        let target_color = eventEl.innerText.toLowerCase()

        return {
          title: eventEl.innerText,
          id: Math.random(),
          color: eventInitialColors[target_color]
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
      //defaultTimedEventDuration:'02:00',
      eventClick: (info) => {
        console.log(info.event.startStr)
        console.log(info.event.endStr);
        const day = new Date(info.event.startStr).getDay();
        var myModal = new Modal(document.getElementById("modalManuel"), {
          keyboard: false
        });

        // modify the id_event_clicked
        this.id_event_clicked = info.event.id;
        console.warn("Yeahhhh " + this.id_event_clicked)
        this.prepareVerification()
        this.modelData = {title: info.event.title}
        myModal.show();
      },
      eventAdd: function (addInfo) {
        alert("jjjrj")
        console.log("jdks")
      }

    };
  }

  degreeChangeHandler() {

  }

  onClassesReceived(classes: [Class], context: this) {
    for (let classItem of classes) {
      context.classes.push(classItem);
    }
  }

  onRoomsReceived(roomsReceived: [Room], context: this) {
    for (let room of roomsReceived) {
      context.roomsList.push(room);
    }

  }

  onTeachersReceived(teachers: [Teacher], context: this) {
    for (let teacher of teachers) {
      context.teachers.push(teacher);
    }
  }

  onDegreesReceived(degrees: [Degree], context: this) {
    for (let degree of degrees) {
      context.degrees.push(degree);
    }
  }
}
