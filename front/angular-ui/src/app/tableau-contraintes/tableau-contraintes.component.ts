import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { ConstraintPrecedence } from '../model/constraint/constraint-precedence';
import { ConstraintTimeRoom } from '../model/constraint/constraint-time-room';
import { DataInterfaceService } from '../services/data-interface.service';


let CONSTRAINTS_TIME_AND_ROOM: ConstraintTimeRoom[] = [
  {
    id:1,
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
    id:2,
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


export class TableauContraintesComponent implements OnInit, OnChanges {
  

  deleteConstraint(id_constraint_clicked,index,type){
    let constraint = document.getElementById(id_constraint_clicked);
    constraint.innerHTML = "";
    if(type=='TR')this.serv.deleteTimeConstraints(this.constraintsTimeRoom[index].id)
    else if(type=='P')this.serv.deletePrecedenceConstraints(this.constraintPrecedence[index].id);
  }


  @Input("constraintsTimeRoom") constraintsTimeRoom = CONSTRAINTS_TIME_AND_ROOM;
  @Input("constraintPrecedence") constraintPrecedence = CONSTRAINTS_PRECEDENCE;
  constructor(private serv:DataInterfaceService) { }
  ngOnInit(): void {
    this.serv.fetchPrecedenceConstraints((data)=>{this.constraintPrecedence = data})
    this.serv.fetchTimeConstraints((data)=>{this.constraintsTimeRoom=data})
    
  }

  ngOnChanges(changes: SimpleChanges) {
    console.log(changes);
  }
}
