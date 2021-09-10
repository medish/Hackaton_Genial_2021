import {Component, OnInit, ViewChild} from '@angular/core';
import {Modal} from 'bootstrap';
import dayGridPlugin from '@fullcalendar/daygrid';
import timeGridPlugin from '@fullcalendar/timegrid';
import interactionPlugin, {Draggable} from '@fullcalendar/interaction';
import { DataInterfaceService } from '../services/data-interface.service';
import { Lesson, Room, Degree, Professor} from '../model/datastore/datamodel';
import { FormBuilder, FormGroup } from '@angular/forms';
import {FullCalendarComponent} from "@fullcalendar/angular";
import uniqid from 'uniqid';

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
  selectedDegree: string;
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
    calendarApi.getEventById(id_event_clicked)?.remove();
  }

  getAllEvents(){
    return this.calendarComponent.getApi().getEvents();
  }


  getIdLesson(){
    // todo V
    return 0
  }
  /**
   * Get current Planning
   */
  prepareVerification() {

    let arrayEvents = this.getAllEvents();
    let constraintName = (<HTMLInputElement>document.getElementById('planning-name')).value;

    let arrayPrepared = [];
    let planningJson ={
      "id":uniqid(),
      "name":constraintName,
      "elements":{}
    }

    for (let i = 0; i < arrayEvents.length; i++) {
      let dateStartStr = new Date(arrayEvents[i].startStr)
      let id_lesson = arrayEvents[i].extendedProps.id_lesson
      if(id_lesson==undefined) {
        continue
      }

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

      arrayPrepared.push({
            "lesson_id":id_lesson,
            "hour":startTime,
             "endTime":endTime,
            "day":dayNumber
          }
      )
    }

    planningJson.elements=arrayPrepared;
    console.warn(planningJson)
    console.warn(arrayEvents)
    return JSON.stringify(planningJson);
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
        console.log("from draggable")
        return {
          title: eventEl.innerText,
          id:uniqid(),
          color:eventInitialColors[target_color]
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
      eventDragStart : function (info ) {
        console.log(info);
        //todo  call new json planning
        //todo call api
      },
      eventClick:  (info)=>{
        this.prepareVerification()
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

    };
  }

  roomChangeHandler(roomNum: number) {
    let calendarApi = this.calendarComponent.getApi();
    let event = calendarApi.getEventById(this.id_event_clicked);
    event?.setExtendedProp("room", roomNum);
  }

  onModalReady() {
    this.clearExistingData();
    this.dataService.fetchAllRooms(this.onRoomsReceived, this);
    this.dataService.fetchAllClasses(this.onClassesReceived, this);
    this.dataService.fetchAllTeachers(this.onTeachersReceived, this);
    this.dataService.fetchAllDegrees(this.onDegreesReceived, this);
  }

  clearExistingData() {
    this.degrees = [];
    this.classes = [];
    this.roomsList = [];
    this.professors = [];
  }
  degreeChangeHandler(degreeId: string) {
    this.selectedDegree = degreeId;
    for(let classItem of this.classes) {
      const degreesForCourse = classItem.course.degrees;
      const degreesId = degreesForCourse.map(degree => degree.id);
      if(!(degreesId.includes(degreeId))) {
        this.classes = this.classes.filter(c => c.id != degreeId);
      }
    }
  }

  classChangeHandler(className : string) {
    const selectedClass = this.classes.find(elem => elem.course.name === className);
    //selectedClass.id
    const teachersForClass = selectedClass!!.professors
    const teachersId = teachersForClass.map(professor => professor.id);
    let color_ = selectedClass!!.course.color;
    let calendarApi = this.calendarComponent.getApi();
    let event = calendarApi.getEventById(this.id_event_clicked);
    event?.setProp("backgroundColor", color_);
    event?.setProp("title", selectedClass?.course.name);
    event?.setExtendedProp("duration", selectedClass?.duration);
    event?.setExtendedProp("id_lesson", selectedClass?.id);
    for(let professor of this.professors) {
      if(!(teachersId.includes(professor.id))) {
        this.professors = this.professors.filter(t => t.id != professor.id);
      }
    }
  }

  teacherChangeHandler(teacherName: string) {
    let calendarApi = this.calendarComponent.getApi();
    let event = calendarApi.getEventById(this.id_event_clicked);
    event?.setExtendedProp("prof", teacherName);
  }

  onClassesReceived(classes : [Lesson], context : this) {
    console.log('classes',classes)
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
    console.log('degrees',degrees)
    for(let degree of degrees) {
      context.degrees.push(degree);
    }
  }
}
