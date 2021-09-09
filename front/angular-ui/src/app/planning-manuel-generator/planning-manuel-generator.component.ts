import {Component, OnInit} from '@angular/core';
import {Modal} from 'bootstrap';
import dayGridPlugin from '@fullcalendar/daygrid';
import timeGridPlugin from '@fullcalendar/timegrid';
import interactionPlugin, {Draggable} from '@fullcalendar/interaction';
import { DataInterfaceService } from '../services/data-interface.service';
import { Class, Room, Degree, Teacher} from '../model/datastore/datamodel';
import { FormBuilder, FormGroup } from '@angular/forms';
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

  constructor(private dataService : DataInterfaceService, private fb : FormBuilder) {
  }

  modelData:{title:string} = {title:''};

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

    // @ts-ignore
    new Draggable(draggableEl, {
      itemSelector: '.fc-event',
      eventData: function (eventEl: any) {
        console.log(eventEl);
        return {
          title: eventEl.innerText
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
        console.log(info);
        console.log(info.event.startStr)
        console.log(info.event.endStr);
        const day = new Date(info.event.startStr).getDay();
        var myModal = new Modal(document.getElementById("modalManuel"), {
          keyboard: false
        });
        this.modelData = {title:info.event.title}
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

  onClassesReceived(classes : [Class], context : this) {
    for(let classItem of classes) {
      context.classes.push(classItem);
      console.log("class name: " + classItem.name);
    }
  }

  onRoomsReceived(roomsReceived : [Room], context : this) {
    for(let room of roomsReceived) {
      context.roomsList.push(room);
    }

  }

  onTeachersReceived(teachers: [Teacher], context: this) {
    for(let teacher of teachers) {
      context.teachers.push(teacher);
    }
  }

  onDegreesReceived(degrees : [Degree], context: this) {
    for(let degree of degrees) {
      context.degrees.push(degree);
    }
  }
}
