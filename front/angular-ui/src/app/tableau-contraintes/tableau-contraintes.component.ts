import { Component, Input, OnInit } from '@angular/core';
import { ConstraintPrecedence } from '../model/constraint/constraint-precedence';
import { ConstraintTimeRoom } from '../model/constraint/constraint-time-room';
import { DataInterfaceService } from '../services/data-interface.service';


let CONSTRAINTS_TIME_AND_ROOM: ConstraintTimeRoom[] = [
  {
    selector:{
        selectorUnits:[
          {table:'a',
          attribute:'b',
          value:'c',}
        ]
    },
    veut: true,
    priority:0, // This attribute should be between 0 and 100 
    room:{selectorUnits:[
     { table: "",
      attribute: "Salle",
      value: "19",}
    ]},
    day:1,
    hourBegin:'08:00',
    hourEnd:'12:00',
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
  
  @Input("constraintsTimeRoom") constraintsTimeRoom = CONSTRAINTS_TIME_AND_ROOM;
  @Input("constraintPrecedence") constraintPrecedence = CONSTRAINTS_PRECEDENCE
  constructor(private serv:DataInterfaceService) { }
  ngOnInit(): void {
    
  }
  

}
