import { Component, OnInit } from '@angular/core';
<<<<<<< HEAD
import { Room, Class, Teacher, Department, Degree, RoomType } from '../model/datastore/datamodel';
=======
import { Observable } from 'rxjs';
import { Room, Class, Teacher, Department, Degree, OutputCalendarService, RoomType } from '../services/output-calendar.service';
>>>>>>> Fusion avec la branche courante
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
    this.router.navigate(['/auto']);
  }

  runManual() {
    this.router.navigate(['/manuel']);
  }

<<<<<<< HEAD
=======
  runAuto() {
    console.log("running auto");
    this.router.navigate(['/admindashboard']);
  }

  runManual() {
    console.log("running manual");
  }

  ngAfterViewInit() {
    console.log("rooms: " + this.rooms);
  }
>>>>>>> Fusion avec la branche courante
}
