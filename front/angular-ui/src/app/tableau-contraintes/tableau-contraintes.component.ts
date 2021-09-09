import { Component, OnInit } from '@angular/core';
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
@Component({
  selector: 'tableau-contraintes',
  templateUrl: './tableau-contraintes.component.html',
  styleUrls: ['./tableau-contraintes.component.scss']
})


export class TableauContraintesComponent implements OnInit {

  constraintsTimeRoom = CONSTRAINTS_TIME_AND_ROOM;
  constraintPrecedence = CONSTRAINTS_PRECEDENCE
  constructor() { }
  ngOnInit(): void {
    
  }
  

}
