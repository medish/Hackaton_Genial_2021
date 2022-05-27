import { Injectable } from '@angular/core';
import { CalendarContent, createEventInstance, EventApi } from '@fullcalendar/angular';
import { duration } from 'html2canvas/dist/types/css/property-descriptors/duration';
import { CourseSlot, Department, DepartmentcontrollerApi } from 'src/app/model/swagger/api';
import uniqid from 'uniqid';
@Injectable({
  providedIn: 'root'
})
export class CourseSlotsService {
  departements:Department[]=[];
  constructor(private departementController:DepartmentcontrollerApi) { 
    this.departementController?.getAllUsingGET4().then(data=>{
      this.departements=data;
    })
  }

  fromCalendarToCourseSlot(calendarEvent:EventApi):CourseSlot{
    return null;
  }
  public mapDays={
    'MONDAY':"1",
    'TUESDAY':"2",
    'WEDNESDAY':"3",
    'THURSDAY':"4",
    'FRIDAY':"5",
    'SATURDAY':"6",
    'SUNDAY':"7"
  }
  fromCourseSlotToCalendar(courseSlot:CourseSlot):any{
    return {
      title:courseSlot?.courseGroup?.course?.degree?.name+' - Groupe '+ courseSlot?.courseGroup?.groupId + ' - ' +  courseSlot?.courseGroup?.course?.name + ' - '+courseSlot?.professor?.firstName +' '+ courseSlot?.professor?.lastName +' - '+courseSlot?.room?.name,
      color:'#'+courseSlot?.courseGroup?.course?.color,
      id:uniqid(),
      daysOfWeek:this.mapDays[courseSlot?.dateSlot?.day],
      startTime:courseSlot?.dateSlot?.startTime,
      endTime:courseSlot?.endTime,
      extendedProps:{
        room:courseSlot?.room?.name,
        course:courseSlot?.courseGroup?.course?.name,
        courseGroup:courseSlot?.courseGroup?.groupId,
        teacher: courseSlot?.professor?.firstName+ ' '+ courseSlot?.professor?.lastName,
        degree: courseSlot?.courseGroup?.course?.degree?.id,
        department: this.departements?.filter(dep=>dep?.rooms?.map(r=>r.name)?.includes(courseSlot?.room?.name))?.[0]?.id,
        duration: '1H'
      }
    }
  }
}
