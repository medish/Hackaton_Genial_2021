import { Component, OnInit, ViewChild } from '@angular/core';
import { Modal } from 'bootstrap';
import dayGridPlugin from '@fullcalendar/daygrid';
import timeGridPlugin from '@fullcalendar/timegrid';
import interactionPlugin, { Draggable } from '@fullcalendar/interaction';
import { DataInterfaceService } from '../services/data-interface.service';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { Calendar, CalendarOptions, Dictionary, EventApi, FullCalendarComponent } from "@fullcalendar/angular";
import uniqid from 'uniqid';
import { document } from "ngx-bootstrap/utils";
import { ExportService } from '../services/export/export.service';
import { faFile } from '@fortawesome/free-solid-svg-icons';
import { Course, CoursecontrollerApi, CourseGroup, CoursegroupcontrollerApi, CourseSlot, Degree, DegreecontrollerApi, Department, DepartmentcontrollerApi, Planning, PlanningcontrollerApi, Professor, ProfessorcontrollerApi, Room, RoomcontrollerApi, TimeconstraintcontrollerApi } from '../model/swagger/api';
import { CourseSlotsService } from '../services/course-slots/course-slots.service';


export const TD_COLOR = "#0d6efd";
export const COURS_COLOR = "#dc3545";
export const TP_COLOR = "#ffc107";

@Component({
  selector: 'app-planning-manuel-generator',
  templateUrl: './planning-manuel-generator.component.html',
  styleUrls: ['./planning-manuel-generator.component.scss']
})

export class PlanningManuelGeneratorComponent implements OnInit {
  options: CalendarOptions;
  formGroupModel: FormGroup;

  roomsList: Room[] = [];
  courses: Course[] = [];
  courseGroups: CourseGroup[] = [];
  allCourseGroups: CourseGroup[] = [];
  professors: Professor[] = [];
  allProfessors: Professor[] = []
  degrees: Degree[] = [];
  selectedDegree: string;
  departments: Department[] = [];
  faFile = faFile;
  that = this;
  calendarApi: any;
  currentDraggable: Draggable;
  generating: boolean = false;
  @ViewChild('calendar') calendarComponent: FullCalendarComponent;
  selectedPlanning: Planning;
  constructor(private roomController: RoomcontrollerApi,
    private courseController: CoursecontrollerApi,
    private professorController: ProfessorcontrollerApi,
    private degreeController: DegreecontrollerApi,
    private departementController: DepartmentcontrollerApi,
    private courseGroupController: CoursegroupcontrollerApi,
    private timeConstraintController: TimeconstraintcontrollerApi,
    private planningController: PlanningcontrollerApi,
    private courseSlotsService: CourseSlotsService,
    private fb: FormBuilder,
    private exportService: ExportService) {
  }

  ngOnInit() {
    this.initForm();
    this.initCalendar();
    this.planningController.getAllUsingGET6().then(data => {
      let planning = data.sort((p1, p2) => p2.id - p1.id)?.[0]
      this.selectedPlanning = planning;
      console.log("selected ", planning)
      this.setEvents(planning?.['slots']);
    }).catch(err => {
      console.error('err :', err)
    })
  }
  initForm() {
    this.formGroupModel = this.fb.group({
      room: new FormControl(''),
      course: new FormControl(''),
      courseGroup: new FormControl(''),
      teacher: new FormControl(''),
      degree: new FormControl(''),
      duration: new FormControl(''),
      idEvent: new FormControl(''),
      title: new FormControl(''),
      department: new FormControl(),
      backgroundColor: new FormControl(''),
      idBack: new FormControl('')
    })
  }
  initCalendar() {
    let draggableEl = document.getElementById('external-events');
    this.currentDraggable = new Draggable(draggableEl, {
      itemSelector: '.fc-event',
      eventData: function (eventEl: any) {
        let eventInitialColors = { td: TD_COLOR, cours: COURS_COLOR, tp: TP_COLOR }
        let target_color = eventEl.innerText.toLowerCase()
        return {
          title: eventEl.innerText,
          id: uniqid(),
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
      expandRows: true,
      slotMinTime: "8:00:00",
      slotMaxTime: "20:00:00",
      firstDay: 1,
      eventTextColor: "white",
      events: [],
      eventDragStop: (info) => {
        this.callVerify()
      },
      eventClick: (info) => {
        this.formGroupModel.controls['idEvent'].setValue(info.event.id)
        //this.prepareVerification()
        var modal = new Modal(document.getElementById("modalManuel"), {
          keyboard: false
        });
        this.onModalReady();
        modal.show();
      },
      eventChange: () => {
        this.callVerify()
      }
    };
  }

  deleteEvent(idEvent: string) {
    let calendarApi: Calendar = this.calendarComponent.getApi();
    calendarApi.getEventById(idEvent)?.remove();
  }
  saveEvent(idEvent: string) {
    let calendarApi: Calendar = this.calendarComponent.getApi();
    let event = calendarApi.getEventById(idEvent);
    let newobj = {
      title: this.degrees.filter(d => d.id == this.formGroupModel.controls['degree'].value)?.[0]?.name + ' - Groupe ' + this.formGroupModel.controls['courseGroup'].value + ' - ' + this.formGroupModel.controls['course'].value + ' - ' + this.formGroupModel.controls['teacher'].value + ' - ' + this.formGroupModel.controls['room'].value,
      color: this.formGroupModel.controls['backgroundColor'].value,
      daysOfWeek: event?._def?.recurringDef?.typeData['daysOfWeek'],
      startTime: event?._def?.recurringDef?.typeData['startTime'],
      endTime: event?._def?.recurringDef?.typeData['endTime'],
      id: uniqid(),
      extendedProps: {
        room: this.formGroupModel.controls['room'].value,
        course: this.formGroupModel.controls['course'].value,
        courseGroup: this.formGroupModel.controls['courseGroup'].value,
        teacher: this.formGroupModel.controls['teacher'].value,
        degree: this.formGroupModel.controls['degree'].value,
        department: this.formGroupModel.controls['department'].value,
        duration: this.formGroupModel.controls['duration'].value,
        idBack: this.formGroupModel.controls['idBack'].value
      }
    }
    event.remove()
    this.calendarComponent.getApi().addEvent(newobj)
    this.callVerify()
  }
  getAllEvents() {
    return this.calendarComponent.getApi().getEvents();
  }
  setEvents(events) {
    this.calendarComponent.getApi().removeAllEvents();
    for (let event of events) {
      this.calendarComponent.getApi().addEvent(this.courseSlotsService.fromCourseSlotToCalendar(event));
    }
  }


  /**
   * Get current Planning
   */
  prepareVerification() {

    let arrayEvents = this.getAllEvents();
    let constraintName = (<HTMLInputElement>document.getElementById('planning-name')).value;

    let arrayPrepared = [];
    let planningJson = {
      "id": uniqid(),
      "name": constraintName,
      "createdAt": new Date(),
      "outputs": {}
    }

    for (let i = 0; i < arrayEvents.length; i++) {
      let dateStartStr = new Date(arrayEvents[i].startStr)

      let course = arrayEvents[i].extendedProps.course

      var userTimezoneOffset = dateStartStr.getTimezoneOffset() * 60000;
      dateStartStr = new Date(dateStartStr.getTime() + userTimezoneOffset);
      let startTime = dateStartStr.getHours() + ":" + dateStartStr.getMinutes() + ":" + dateStartStr.getSeconds();
      let dateEndStr = new Date(new Date(arrayEvents[i].endStr).getTime() + userTimezoneOffset)
      let endTime = "";
      if (!isNaN(dateEndStr.getTime())) {
        endTime = dateEndStr.getHours() + ":" + dateEndStr.getMinutes() + ":" + dateEndStr.getSeconds();
      } else {
        let defaulthour = dateStartStr.getHours() + 1;
        endTime = defaulthour + ":" + dateStartStr.getMinutes() + ":" + dateStartStr.getSeconds();
      }
      let dayNumber = dateStartStr.getDay()

      arrayPrepared.push({
        "id": uniqid(),
        "date": {
          "dateId": {
            day: dayNumber.toString(),
            hour: startTime
          },
          hour: startTime,
          day: dayNumber.toString()
        },
        //TODO ajouter room
        "course": course,
        "hour": startTime,
        "endTime": endTime,
        "day": dayNumber
      }
      )
    }

    planningJson.outputs = arrayPrepared;
    //this.dataService.verifyConstraints(planningJson);
    return JSON.stringify(planningJson);
  }


  exportToICS() {
    this.exportService.exportToICS(this.getAllEvents());
  }



  onModalReady() {
    if (this.formGroupModel.controls['idEvent'].value) {
      let calendarApi: Calendar = this.calendarComponent.getApi();
      let event = calendarApi.getEventById(this.formGroupModel.controls['idEvent'].value)
      this.formGroupModel.controls['room'].setValue(event.extendedProps['room']);
      this.formGroupModel.controls['course'].setValue(event.extendedProps['course']);
      this.formGroupModel.controls['courseGroup'].setValue(event.extendedProps['courseGroup'])
      this.formGroupModel.controls['teacher'].setValue(event.extendedProps['teacher']);
      this.formGroupModel.controls['degree'].setValue(event.extendedProps['degree']);
      this.formGroupModel.controls['duration'].setValue(event.extendedProps['duration']);
      this.formGroupModel.controls['department'].setValue(event.extendedProps['department'])
      this.formGroupModel.controls['title'].setValue(event.title);
      this.formGroupModel.controls['backgroundColor'].setValue(event.backgroundColor);
      this.formGroupModel.controls['idBack'].setValue(event.extendedProps['idBack']);
    }
    this.clearExistingData();
    this.roomController.getAllUsingGET9().then(data => {
      for (let room of data) {
        this.roomsList.push(room);
      }
    })
    this.courseController.getAllUsingGET().then(data => {
      for (let course of data) {
        this.courses.push(course);
      }
    })
    this.professorController.getAllUsingGET8().then(data => {
      for (let prof of data) {
        this.allProfessors.push(prof);
      }
      if (this.formGroupModel.controls['course']?.value) {
        this.professors = this.allProfessors.filter(professor => {
          if (this.courses.filter(c => c?.name == this.formGroupModel.controls['course']?.value)?.[0]?.professors?.map(p => p?.id).includes(professor?.id)) {
            return true;
          }
          return false;
        })
      }
    })
    this.degreeController.getAllUsingGET3().then(degrees => {
      for (let degree of degrees) {
        this.degrees.push(degree);
      }
    })
    this.departementController.getAllUsingGET4().then(departments => {
      for (let dep of departments) {
        this.departments.push(dep);
      }
    });
    this.courseGroupController.getAllUsingGET1().then(courseGroups => {
      for (let courseGroup of courseGroups) {
        this.allCourseGroups.push(courseGroup);
      }
      if (this.formGroupModel.controls['course']?.value) {
        this.courseGroups = this.allCourseGroups.filter(courseGroup => {
          if (courseGroup.majorCourse?.course.name == this.formGroupModel.controls['course']?.value) {
            return true;
          }
          return false;
        })
      }
    });
  }


  clearExistingData() {
    this.degrees = [];
    this.courses = [];
    this.roomsList = [];
    this.professors = [];
    this.departments = [];
    this.courseGroups = [];
    this.allCourseGroups = [];
    this.allProfessors = [];
  }


  degreeChangeHandler(degreeId: string) {
    this.formGroupModel.controls['degree'].setValue(degreeId);
  }

  courseChangeHandler(className) {
    const selectedCourse = this.courses.find(elem => elem.name === className);
    this.formGroupModel.controls['title'].setValue(selectedCourse?.name);
    this.formGroupModel.controls['backgroundColor'].setValue("#" + selectedCourse?.color)
    //TODO Handle duration
    this.formGroupModel.controls['duration'].setValue('1H');
    this.formGroupModel.controls['course'].setValue(selectedCourse?.name);
    this.courseGroups = this.allCourseGroups.filter(courseGroup => {
      if (courseGroup?.majorCourse?.course.name == this.formGroupModel.controls['course']?.value) {
        return true;
      }
      return false;
    });
    this.professors = this.allProfessors.filter(professor => {
      if (this.courses.filter(c => c?.name == this.formGroupModel.controls['course']?.value)?.[0]?.professors?.map(p => p?.id).includes(professor?.id)) {
        return true;
      }
      return false;
    })
    this.formGroupModel.controls['teacher'].setValue("");
    this.formGroupModel.controls['courseGroup'].setValue("");
  }
  changeCourseGroupHandler(courseGroup) {
    this.formGroupModel.controls['courseGroup'].setValue(courseGroup);
  }

  teacherChangeHandler(teacherFirstNameName: string) {
    this.formGroupModel.controls['teacher'].setValue(teacherFirstNameName);
  }

  roomChangeHandler(roomName: string) {
    this.formGroupModel.controls['room'].setValue(roomName);
  }

  departmentsChangeHandler(departmentName: string) {
    this.formGroupModel.controls['department'].setValue(departmentName);
  }

  generatePlanning() {
    this.generating = true;
    this.planningController.generatePlanningUsingGET().then(planning => {
      let slots = planning.slots?.map(slot => this.courseSlotsService.fromCourseSlotToCalendar(slot));
      this.calendarComponent.getApi().removeAllEvents();
      for (let evt of slots) {
        this.calendarComponent.getApi().addEvent(evt);
      }
      this.generating = false;
    })
  }

  msToHMS(ms) {
    // 1- Convert to seconds:
    let seconds = ms / 1000;
    // 2- Extract hours:
    const hours = seconds / 3600; // 3,600 seconds in 1 hour
    seconds = seconds % 3600; // seconds remaining after extracting hours
    // 3- Extract minutes:
    const minutes = seconds / 60; // 60 seconds in 1 minute
    // 4- Keep only seconds not extracted to minutes:
    seconds = seconds % 60;
    return hours.toLocaleString('en-US', {
      minimumIntegerDigits: 2,
      useGrouping: false
    }) + ":" + minutes.toLocaleString('en-US', {
      minimumIntegerDigits: 2,
      useGrouping: false
    }) + ":" + seconds.toLocaleString('en-US', {
      minimumIntegerDigits: 2,
      useGrouping: false
    });
  }
  callVerify() {
    let allEvents = this.getAllEvents();
    let allSlots = [];
    for (let event of allEvents) {
      console.log('event ', event)
      allSlots.push(this.courseSlotsService.fromCalendarToCourseSlot({
        title: this.degrees.filter(d => d.id == this.formGroupModel.controls['degree'].value)?.[0]?.name + ' - Groupe ' + this.formGroupModel.controls['courseGroup'].value + ' - ' + this.formGroupModel.controls['course'].value + ' - ' + this.formGroupModel.controls['teacher'].value + ' - ' + this.formGroupModel.controls['room'].value,
        backgroundColor: this.formGroupModel.controls['backgroundColor'].value,
        daysOfWeek: event?._def?.recurringDef?.typeData['daysOfWeek'],
        startTime: this.msToHMS(event?._def?.recurringDef?.typeData['startTime']?.milliseconds),
        endTime: this.msToHMS(event?._def?.recurringDef?.typeData['endTime']?.milliseconds),
        id: uniqid(),
        extendedProps: {
          room: this.roomsList?.filter(room => room?.name == this.formGroupModel.controls['room']?.value)?.[0],
          course: this.courses?.filter(course => course?.name == this.formGroupModel.controls['course']?.value)?.[0],
          courseGroup: this.allCourseGroups?.filter(courseGroup => courseGroup.id == this.formGroupModel.controls['courseGroup']?.value)?.[0],
          teacher: this.allProfessors?.filter(professor => (professor?.firstName + ' ' + professor?.lastName) == this.formGroupModel.controls['teacher']?.value)?.[0],
          degree: this.degrees?.filter(degree => degree.id == this.formGroupModel.controls['degree']?.value)?.[0],
          department: this.departments?.filter(department => department.id == this.formGroupModel.controls['department'].value)?.[0],
          duration: this.formGroupModel.controls['duration'].value,
          idBack: this.formGroupModel.controls['idBack'].value
        }
      }))
    }
    this.planningController
      ?.insertPlanningUsingPOST({ planning: { createdAt: this.selectedPlanning?.createdAt, slots: allSlots, id: this.selectedPlanning?.id, name: this.selectedPlanning?.name } }).then(data => {
        console.log("done", data)
      })
  }
}
