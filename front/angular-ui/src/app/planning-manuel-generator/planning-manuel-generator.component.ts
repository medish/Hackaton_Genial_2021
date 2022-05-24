import {Component, OnInit, ViewChild} from '@angular/core';
import {Modal} from 'bootstrap';
import dayGridPlugin from '@fullcalendar/daygrid';
import timeGridPlugin from '@fullcalendar/timegrid';
import interactionPlugin, {Draggable} from '@fullcalendar/interaction';
import { DataInterfaceService } from '../services/data-interface.service';
import { Room, Degree, Professor, Course, Lesson} from '../model/datastore/datamodel';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import {Calendar, CalendarOptions, Dictionary, EventApi, FullCalendarComponent} from "@fullcalendar/angular";
import uniqid from 'uniqid';
import {createEvents} from 'ics';
import {document} from "ngx-bootstrap/utils";
import { ExportService } from '../services/export/export.service';

export const TD_COLOR = "#0d6efd";
export const COURS_COLOR= "#dc3545";
export const TP_COLOR = "#ffc107";

@Component({
  selector: 'app-planning-manuel-generator',
  templateUrl: './planning-manuel-generator.component.html',
  styleUrls: ['./planning-manuel-generator.component.scss']
})

export class PlanningManuelGeneratorComponent implements OnInit {
  options: CalendarOptions;
  formGroupModel:FormGroup;

  roomsList: Room[] = [];
  courses: Course[] = [];
  professors: Professor[] = [];
  degrees: Degree[] = [];
  selectedDegree: string;
  that = this;
  calendarApi :any;
  currentDraggable: Draggable;
  @ViewChild('calendar') calendarComponent: FullCalendarComponent;

  constructor(private dataService : DataInterfaceService, private fb : FormBuilder, private exportService:ExportService) {
  }

  ngOnInit() {
    let draggableEl = document.getElementById('external-events');
    this.formGroupModel = this.fb.group({
      room: new FormControl(''),
      course: new FormControl(''),
      teacher: new FormControl(''),
      degree: new FormControl(''),
      duration:new FormControl(''),
      idEvent:new FormControl(''),
      title:new FormControl(''),
      backgroundColor:new FormControl('')
    })
    this.currentDraggable = new Draggable(draggableEl, {
      itemSelector: '.fc-event',
      eventData: function (eventEl: any) {
        let eventInitialColors={td:TD_COLOR,cours:COURS_COLOR,tp:TP_COLOR}
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
      eventDragStart : function (info ) {},
      eventClick:  (info)=>{
        this.formGroupModel.controls['idEvent'].setValue(info.event.id)
        this.prepareVerification()
        var modal = new Modal(document.getElementById("modalManuel"), {
          keyboard: false
        });
        this.onModalReady();
        modal.show();
      },
      eventChange: (change)=>{}      

    };
  }


  deleteEvent(idEvent:string){
    let calendarApi : Calendar = this.calendarComponent.getApi();
    calendarApi.getEventById(idEvent)?.remove();
  }
  saveEvent(idEvent:string){
    let calendarApi : Calendar = this.calendarComponent.getApi();
    let event = calendarApi.getEventById(idEvent);
    event.setExtendedProp("room",this.formGroupModel.controls['room'].value)
    event.setExtendedProp("duration",this.formGroupModel.controls['duration'].value)
    event.setExtendedProp("course",this.formGroupModel.controls['course'].value)
    event.setExtendedProp("teacher",this.formGroupModel.controls['teacher'].value)
    event.setExtendedProp("degree",this.formGroupModel.controls['degree'].value)
    event.setProp("title",this.formGroupModel.controls['title'].value)
    event.setProp("backgroundColor",this.formGroupModel.controls['backgroundColor'].value)
  }
  getAllEvents(){
    return this.calendarComponent.getApi().getEvents();
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
      "createdAt":new Date(),
      "outputs":{}
    }

    for (let i = 0; i < arrayEvents.length; i++) {
      let dateStartStr = new Date(arrayEvents[i].startStr)
      
      let course = arrayEvents[i].extendedProps.course

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
            "id":uniqid(),
            "date":{
              "dateId":{
                day:dayNumber.toString(),
                hour:startTime
              },
              hour:startTime,
              day:dayNumber.toString()
            },
            //TODO ajouter room
            "course":course,
            "hour":startTime,
            "endTime":endTime,
            "day":dayNumber
          }
      )
    }

    planningJson.outputs=arrayPrepared;
    console.warn(planningJson)
    console.warn(arrayEvents)
    //this.dataService.verifyConstraints(planningJson);
    return JSON.stringify(planningJson);
  }


  exportToICS() {
    this.exportService.exportToICS(this.getAllEvents());
  }



  onModalReady() {
    if(this.formGroupModel.controls['idEvent'].value){
      let calendarApi : Calendar = this.calendarComponent.getApi();
      let event = calendarApi.getEventById(this.formGroupModel.controls['idEvent'].value)
      this.formGroupModel.controls['room'].setValue(event.extendedProps['room']);
      this.formGroupModel.controls['course'].setValue(event.extendedProps['course']);
      this.formGroupModel.controls['teacher'].setValue(event.extendedProps['teacher']);
      this.formGroupModel.controls['degree'].setValue(event.extendedProps['degree']);
      this.formGroupModel.controls['duration'].setValue(event.extendedProps['duration']);
      this.formGroupModel.controls['title'].setValue(event.title);
      this.formGroupModel.controls['backgroundColor'].setValue(event.backgroundColor);
    }
    this.clearExistingData();
    this.dataService.fetchAllRooms(this.onRoomsReceived, this);
    this.dataService.fetchAllLessons(this.onLessonsReceived, this);
    this.dataService.fetchAllTeachers(this.onTeachersReceived, this);
    this.dataService.fetchAllDegrees(this.onDegreesReceived, this);
  }

  clearExistingData() {
    this.degrees = [];
    this.courses = [];
    this.roomsList = [];
    this.professors = [];
  }
  degreeChangeHandler(degreeId: string) {
    this.formGroupModel.controls['degree'].setValue(degreeId);
  }

  courseChangeHandler(className) {
    const selectedCourse = this.courses.find(elem => elem.name === className);
    this.formGroupModel.controls['title'].setValue(selectedCourse?.name);
    //TODO Handle duration
    this.formGroupModel.controls['duration'].setValue('1H');
    this.formGroupModel.controls['course'].setValue(selectedCourse?.name);
  }

  teacherChangeHandler(teacherFirstNameName: string) {
    this.formGroupModel.controls['teacher'].setValue(teacherFirstNameName);
  }

  roomChangeHandler(roomNum: string) {
    this.formGroupModel.controls['room'].setValue(roomNum);
  }

  onLessonsReceived(lessons : [Lesson], context : this) {
    let alreadyHere = []
    for(let lesson of lessons) {
      if(!alreadyHere.includes(lesson?.course?.id)){
        context.courses.push(lesson.course);
        alreadyHere.push(lesson?.course?.id)
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
