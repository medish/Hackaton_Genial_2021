import {Component, OnInit} from '@angular/core';
import {Department, DepartmentcontrollerApi, RoomcontrollerApi} from '../model/swagger/api';
import {Room} from "../model/swagger/api";

@Component({
  selector: 'app-all-rooms',
  templateUrl: './all-rooms.component.html',
  styleUrls: ['./all-rooms.component.scss']
})
export class AllRoomsComponent implements OnInit {

  constructor(private roomcontrollerApi: RoomcontrollerApi, private departmentcontrollerApi: DepartmentcontrollerApi) {
  }

  all_rooms: Room[] = []
  all_departments: Department[] = [];

  ngOnInit(): void {
    this.roomcontrollerApi.getAllUsingGET9().then(data => {
      this.all_rooms = data
      console.log(data);
    })

    this.departmentcontrollerApi.getAllUsingGET4().then(data => {
      this.all_departments = data;
      console.log(data)
    })

    // filter

  }


  editRoom(room: Room): void {

  }

  deleteRoom(room: Room): void {

  }

}
