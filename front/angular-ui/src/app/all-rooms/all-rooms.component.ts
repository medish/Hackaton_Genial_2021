import {Component, OnInit} from '@angular/core';
import {Department, DepartmentcontrollerApi, RoomcontrollerApi} from '../model/swagger/api';
import {Room} from "../model/swagger/api";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";



@Component({
  selector: 'app-all-rooms',
  templateUrl: './all-rooms.component.html',
  styleUrls: ['./all-rooms.component.scss']
})
export class AllRoomsComponent implements OnInit {

  constructor(private roomcontrollerApi: RoomcontrollerApi, private departmentcontrollerApi: DepartmentcontrollerApi, private http: HttpClient) {
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
    this.http.delete(environment.baseUrl + "/rooms/" + room.id).subscribe(data => {
      console.log(data);
      alert(data);
    })
  }



}
