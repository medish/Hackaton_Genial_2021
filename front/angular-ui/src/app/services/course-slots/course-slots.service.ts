import { Injectable } from '@angular/core';
import { CalendarContent, createEventInstance, EventApi } from '@fullcalendar/angular';
import { duration } from 'html2canvas/dist/types/css/property-descriptors/duration';
import { CourseSlot, Department, DepartmentcontrollerApi } from 'src/app/model/swagger/api';
import uniqid from 'uniqid';
@Injectable({
  providedIn: 'root'
})
export class CourseSlotsService {
  departements: Department[] = [];
  constructor(private departementController: DepartmentcontrollerApi) {
    this.departementController?.getAllUsingGET4().then(data => {
      this.departements = data;
    })
  }

  fromCalendarToCourseSlot(calendarEvent): CourseSlot {
    console.log("calev : ",calendarEvent)
    return {
      courseGroup: calendarEvent.extendedProps['courseGroup'],
      endTime:calendarEvent.endTime,
      dateSlot:{day:this.inverseMapDays[calendarEvent?.daysOfWeek],startTime:calendarEvent?.startTime},
      id: calendarEvent.extendedProps['idBack'],
      professor: calendarEvent.extendedProps['teacher'],
      room: calendarEvent.extendedProps['room'],
      startTime:calendarEvent.startTime
    };
  }
  public mapDays = {
    'MONDAY': "1",
    'TUESDAY': "2",
    'WEDNESDAY': "3",
    'THURSDAY': "4",
    'FRIDAY': "5",
    'SATURDAY': "6",
    'SUNDAY': "7"
  }
  public inverseMapDays = {
    "1": 'MONDAY',
    "2": 'TUESDAY',
    "3": 'WEDNESDAY',
    "4": 'THURSDAY',
    "5": 'FRIDAY',
    "6": 'SATURDAY',
    "7": "SUNDAY"
  }

  fromCourseSlotToCalendar(courseSlot: CourseSlot): any {
    return {
      title: courseSlot?.courseGroup?.majorCourse?.course?.degree?.name + ' - Groupe ' + courseSlot?.courseGroup?.id + ' - ' + courseSlot?.courseGroup?.majorCourse?.course?.name + ' - ' + courseSlot?.professor?.firstName + ' ' + courseSlot?.professor?.lastName + ' - ' + courseSlot?.room?.name,
      color: '#' + courseSlot?.courseGroup?.majorCourse?.course?.color,
      id: uniqid(),
      daysOfWeek: this.mapDays[courseSlot?.dateSlot?.day],
      startTime: courseSlot?.dateSlot?.startTime,
      endTime: courseSlot?.endTime,
      extendedProps: {
        room: courseSlot?.room?.name,
        course: courseSlot?.courseGroup?.majorCourse?.course?.name,
        courseGroup: courseSlot?.courseGroup?.id,
        teacher: courseSlot?.professor?.firstName + ' ' + courseSlot?.professor?.lastName,
        degree: courseSlot?.courseGroup?.majorCourse?.course?.degree?.id,
        department: this.departements?.filter(dep => dep?.rooms?.map(r => r.name)?.includes(courseSlot?.room?.name))?.[0]?.id,
        duration: '1H',
        idBack: courseSlot?.id
      }
    }
  }
}
