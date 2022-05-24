import { Component, OnInit } from '@angular/core';
import { faCirclePlus, faEye } from '@fortawesome/free-solid-svg-icons';
import { ConstraintPrecedence } from '../model/constraint/constraint-precedence';
import { ConstraintTimeRoom } from '../model/constraint/constraint-time-room';

@Component({
  selector: 'app-constraints',
  templateUrl: './constraints.component.html',
  styleUrls: ['./constraints.component.scss']
})
export class ConstraintsComponent implements OnInit {

  constructor() { }
  faCirclePlus = faCirclePlus;
  faEye = faEye;
  isCollapsedconstraints = true;
  isCollapsedArray = true;
  precedenceConstraints:ConstraintPrecedence[]=[];
  timeAndRoomConstraints:ConstraintTimeRoom[]=[];

  ngOnInit(): void {
  }

  updatePrecedenceConstraints(constraints:ConstraintPrecedence[]){
    if(!constraints)return;
    this.precedenceConstraints=this.precedenceConstraints.concat(constraints);
  }
  updateTimeAndRoomsConstraints(constraints:ConstraintTimeRoom[]){
    if(!constraints)return;
    this.timeAndRoomConstraints=this.timeAndRoomConstraints.concat(constraints);
  }
}
