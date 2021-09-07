import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Room, Class, Teacher, Department, Degree, OutputCalendarService, RoomType } from '../services/output-calendar.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.scss']
})
export class AdminComponent implements OnInit {

  constructor(private outputData: OutputCalendarService) { }

  rooms : [Room] | undefined

  classes : [Class] | undefined

  roomTypes : [RoomType] | undefined

  teachers : [Teacher] | undefined

  departments : [Department] | undefined

  degrees : [Degree] | undefined

  ngOnInit(): void {
    this.getRooms();
  }

  // NOTE: these functions must be implemented directly in the component that uses them
  getRooms() { 
    this.outputData.fetchAllRooms()
    .subscribe((data : [Room]) => this.rooms = {...data})
  }

  getClasses() {
    this.outputData.fetchAllClasses()
    .subscribe((data : [Class]) => this.classes = {...data})
  }

  getRoomTypes() {
    this.outputData.fetchAllRoomTypes()
    .subscribe((data : [RoomType]) => this.roomTypes = {...data})
  }

  getTeachers() {
    this.outputData.fetchAllTeachers()
    .subscribe((data : [Teacher]) => this.teachers = {...data})
  }

  getDepartments() {
    this.outputData.fetchAllDepartments()
    .subscribe((data : [Department]) => this.departments = {...data})
  }

  getDegrees() {
    this.outputData.fetchAllDegrees()
    .subscribe((data : [Degree]) => this.degrees = {...data})
  }

  ngAfterViewInit() {
    console.log("rooms: " + this.rooms);
  }
}
