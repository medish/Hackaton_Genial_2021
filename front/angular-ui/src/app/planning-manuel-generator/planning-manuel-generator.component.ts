import {Component, OnInit, ViewChild} from '@angular/core';
import {Modal} from 'bootstrap';
import dayGridPlugin from '@fullcalendar/daygrid';
import timeGridPlugin from '@fullcalendar/timegrid';
import interactionPlugin, {Draggable} from '@fullcalendar/interaction';
import { DataInterfaceService } from '../services/data-interface.service';
import { Lesson, Room, Degree, Professor} from '../model/datastore/datamodel';
import { FormBuilder, FormGroup } from '@angular/forms';
import {FullCalendarComponent} from "@fullcalendar/angular";

@Component({
  selector: 'app-planning-manuel-generator',
  templateUrl: './planning-manuel-generator.component.html',
  styleUrls: ['./planning-manuel-generator.component.scss']
})

export class PlanningManuelGeneratorComponent implements OnInit {
  options: any;
  currentEvent: string;
  roomsForm: FormGroup;
  classForm: FormGroup;
  teacherForm: FormGroup;
  degreeForm: FormGroup;
  roomsList: Room[] = [];
  classes: Lesson[] = [];
  professors: Professor[] = [];
  degrees: Degree[] = [];
  selectedDegree: number;
  that = this;
  id_event_clicked: string="";
  calendarApi :any;
  currentDraggable: Draggable;

  constructor(private dataService : DataInterfaceService, private fb : FormBuilder) {
  }

  @ViewChild('calendar') calendarComponent: FullCalendarComponent;

  modelData:{title:string} = {title:''};

  deleteEvent(id_event_clicked:string){
    let calendarApi = this.calendarComponent.getApi();
    let event = calendarApi.getEventById(id_event_clicked);
    event.remove()
  }

  getAllEvents(){
    return this.calendarComponent.getApi().getEvents();
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
      degreeControl: ['Choisir un cursus']
    });
    this.currentDraggable = new Draggable(draggableEl, {
      itemSelector: '.fc-event',
      eventData: function (eventEl: any) {
        let eventInitialColors={td:"#0d6efd",cours:"#dc3545",tp:"#ffc107"}
        let target_color= eventEl.innerText.toLowerCase()
        
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
      eventClick:  (info)=>{
        const day = new Date(info.event.startStr).getDay();
        var myModal = new Modal(document.getElementById("modalManuel"), {
          keyboard: false
        });
        //TODO: modify the id_event_clicked
        this.id_event_clicked=info.event.id;
        this.modelData = {title:info.event.title};
        this.onModalReady();
        this.currentEvent = this.modelData.title;
        myModal.show();
      },
      eventAdd: function (addInfo) {
        var myModal = new Modal(document.getElementById("modalManuel"), {
          keyboard: false
        });
        myModal.show();
      }

    };
  }

  roomChangeHandler(roomNum: number) {
    let calendarApi = this.calendarComponent.getApi();
    let event = calendarApi.getEventById(this.id_event_clicked);
    event?.setExtendedProp("room", roomNum);
    console.log("room number: " + event?.extendedProps["room"]);
  }

  onModalReady() {
    this.classes = [];
    this.dataService.fetchAllRooms(this.onRoomsReceived, this);
    this.dataService.fetchAllClasses(this.onClassesReceived, this);
    this.dataService.fetchAllTeachers(this.onTeachersReceived, this);
    this.dataService.fetchAllDegrees(this.onDegreesReceived, this);
  }
  degreeChangeHandler(degreeId: number) {
    this.selectedDegree = degreeId;
    for(let classItem of this.classes) {
      const degreesForCourse = classItem.course.degrees;
      const degreesId = degreesForCourse.map(degree => degree.id);
      if(!(degreesId.includes(degreeId))) {
        this.classes = this.classes.filter(c => c.id != degreeId);
      }
    }
  }

  classChangeHandler(classId : number) {
    const selectedClass = this.classes.find(elem => elem.id = classId);
    const teachersForClass = selectedClass!!.professors
    const teachersId = teachersForClass.map(professor => professor.id);
    let color_ = selectedClass!!.course.color;
    let calendarApi = this.calendarComponent.getApi();
    let event = calendarApi.getEventById(this.id_event_clicked);
    event?.setProp("backgroundColor", color_);
    event?.setProp("title", selectedClass?.course.name);
    for(let professor of this.professors) {
      if(!(teachersId.includes(professor.id))) {
        this.professors = this.professors.filter(t => t.id != professor.id);
      }
    }
  }

  onClassesReceived(classes : [Lesson], context : this) {
    for(let classItem of classes) {
      let currentRoomType = classItem.roomType.name.toLowerCase();
      if(context.modelData.title.toLowerCase() === currentRoomType) {
        context.classes.push(classItem);
      }
    }
  }

  onRoomsReceived(roomsReceived : [Room], context : this) {
    for(let room of roomsReceived) {
      context.roomsList.push(room);
    }
  }

  onTeachersReceived(professors: [Professor], context: this) {
    for(let professor of professors) {
      context.professors.push(professor);
    }
  }

  onDegreesReceived(degrees : [Degree], context: this) {
    for(let degree of degrees) {
      context.degrees.push(degree);
    }
  }
}
