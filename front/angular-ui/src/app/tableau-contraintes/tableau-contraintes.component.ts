import { Component, OnInit } from '@angular/core';
<<<<<<< HEAD
import { Constraint } from '../model/constraint/constraint';
import { ConstraintPrecedence } from '../model/constraint/constraint-precedence';
import { ConstraintTimeRoom } from '../model/constraint/constraint-time-room';
import { DataInterfaceService } from '../services/data-interface.service';
=======
import { ConstraintPrecedence } from '../model/constraint/constraint-precedence';
import { ConstraintTimeRoom } from '../model/constraint/constraint-time-room';


const CONSTRAINTS_TIME_AND_ROOM: ConstraintTimeRoom[] = [
  {
    selector:{
      selectorUnits:[{table:'ens',attribute:'name',value:'zielonka'}]
    },
    veut:true,
    priority:50,
    room:{selectorUnits:[{table:'room',attribute:'name',value:'123'}]},
    day:1,
    hourBegin:'10:30',
    hourEnd:'12:00'
  }
];
const CONSTRAINTS_PRECEDENCE:ConstraintPrecedence[]=[
  {
    selector:{
      selectorUnits:[{table:'ens',attribute:'name',value:'zielonka'}]
    },
    veut:true,
    priority:50,
    precedence:'before',
    strict:true,
    selectorTarget:{
      selectorUnits:[{table:'ens',attribute:'name',value:'klimann'}]
    }
  }
]
>>>>>>> front
@Component({
  selector: 'tableau-contraintes',
  templateUrl: './tableau-contraintes.component.html',
  styleUrls: ['./tableau-contraintes.component.scss']
})


export class TableauContraintesComponent implements OnInit {

<<<<<<< HEAD
  constraints: Constraint[] = [];
  constructor(private dataInterface : DataInterfaceService) { }
  ngOnInit(): void {
    this.dataInterface.fetchPrecedenceConstraints(this.onPrecedenceConstraintsReceived);
    this.dataInterface.fetchTimeConstraints(this.onTimeRoomConstraintsReceived)
  }

  onPrecedenceConstraintsReceived(constraints : [ConstraintPrecedence]) {
    
  }

  onTimeRoomConstraintsReceived(constraints : [ConstraintTimeRoom]) {
    for(let constraint of constraints) {
      let genericConstraint = constraint as Constraint;
      this.constraints.push(genericConstraint);
    }
=======
  constraintsTimeRoom = CONSTRAINTS_TIME_AND_ROOM;
  constraintPrecedence = CONSTRAINTS_PRECEDENCE
  constructor() { }
  ngOnInit(): void {
    
>>>>>>> front
  }
  

}
