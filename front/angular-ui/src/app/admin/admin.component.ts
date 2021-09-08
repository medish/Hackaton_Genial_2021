import { Component, OnInit } from '@angular/core';
import { Room, Class, Teacher, Department, Degree, RoomType } from '../model/datastore/datamodel';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.scss']
})
export class AdminComponent implements OnInit {

  constructor(private router : Router) { }

  rooms : [Room] | undefined

  classes : [Class] | undefined

  roomTypes : [RoomType] | undefined

  teachers : [Teacher] | undefined

  departments : [Department] | undefined

  degrees : [Degree] | undefined

  ngOnInit(): void {    
  }


  runAuto() {
    this.router.navigate(['/admin']);
  }

  runManual() {
    console.log("running manual");
  }

}
