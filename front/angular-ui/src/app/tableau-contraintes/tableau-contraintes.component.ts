import {Component, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import { ConstraintPrecedence, ConstraintPrecedenceExport } from '../model/constraint/constraint-precedence';
import { ConstraintTimeRoom, ConstraintTimeRoomExport } from '../model/constraint/constraint-time-room';
import { DataInterfaceService } from '../services/data-interface.service';
import {HttpClient} from "@angular/common/http";
import {DateSlotDayEnum, TimeConstraint, TimeconstraintcontrollerApi} from "../model/swagger/api";
import {filter, Observable} from "rxjs";
import {Router} from "@angular/router";
import {MessagingService} from "../services/messaging.service";


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
    wants: true,
    priority:0, // This attribute should be between 0 and 100
    room:{selectorUnits:[
     { table: "",
      attribute: "Salle",
      value: "19",}
    ]},
    day:1,
    dateBegin:'08:00',
    dateEnd:'12:00',
}
];
const CONSTRAINTS_PRECEDENCE:ConstraintPrecedence[]=[
  {
    id:2,
    selector:{
      selectorUnits:[{table:'ens',attribute:'name',value:'zielonka'}]
    },
    wants:true,
    priority:50,
    whenConstraint:'before',
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

  confirmationMessage : Observable<Boolean>;

  constructor(private serv:DataInterfaceService, messagingService: MessagingService) {
    serv.getResult().subscribe(value => {
      messagingService.updateMessage(value);
    });
  }
  ngOnInit(): void {
    this.serv.fetchPrecedenceConstraints((data)=>{this.constraintPrecedence = data})
    this.serv.fetchTimeConstraints((data)=>{this.constraintsTimeRoom=data})
  }

  ngOnChanges(changes: SimpleChanges) {
    for (let propName in changes) {
      let change = changes[propName];
      if(change.currentValue.length != 0) {
        if (change.currentValue[0].whenConstraint != null) {
          //let precedenceConstraint: [ConstraintPrecedence] = change.currentValue;
          let precedenceConstraint: [ConstraintPrecedenceExport] = [null];
          precedenceConstraint.pop();
          let prec : [ConstraintPrecedence] = change.currentValue;
          for (let i = 0; i < prec.length; i++) {
            const curr : ConstraintPrecedence = prec[i];
            let whenConstraint = curr.whenConstraint == "before" ? "avant" : curr.whenConstraint == "pendant" ? "synchro" : "apres";
            let tmp: ConstraintPrecedenceExport = {
              id: curr.id,
              selector: curr.selector.selectorUnits[0].table+":"+curr.selector.selectorUnits[0].attribute+":"+curr.selector.selectorUnits[0].value,
              wants: curr.wants,
              whenConstraint: whenConstraint,
              strict: curr.strict,
              target: curr.selectorTarget.selectorUnits[0].table+":"+curr.selectorTarget.selectorUnits[0].attribute+":"+curr.selectorTarget.selectorUnits[0].value,
              priority: curr.priority
            }
            precedenceConstraint.push(tmp);
          }
          console.log(precedenceConstraint);
          this.serv.sendPrecedenceConstraints(precedenceConstraint);
        } else {
          let timeRoomConstraint : [ConstraintTimeRoom] = change.currentValue;
          let timeRoomConstraintExp: [TimeConstraint] = [null];
          timeRoomConstraintExp.pop();
          let t = new TimeconstraintcontrollerApi();
          for(let i = 0; i < timeRoomConstraint.length; i++) {
            const curr : ConstraintTimeRoom = timeRoomConstraint[i];
            let day : DateSlotDayEnum = curr.day == 1 ? "MONDAY" : curr.day == 2 ? "TUESDAY" : curr.day == 3 ? "WEDNESDAY" : curr.day == 4 ? "TUESDAY" : "FRIDAY";
            let start = curr.dateBegin.split(":");
            let end = curr.dateEnd.split(":");
            let tmp : any = {
              id: curr.id,
              selector: curr.selector.selectorUnits[0].table+":"+curr.selector.selectorUnits[0].attribute+":"+curr.selector.selectorUnits[0].value,
              wants: curr.wants,
              day:day,
              dateBegin: {day:day,startTime:curr.dateBegin},
              dateEnd: {day:day,startTime:curr.dateEnd},
              room: curr.room,
              priority: curr.priority,
              startTime:curr.dateBegin,
              endTime:curr.dateEnd
            }
            timeRoomConstraintExp.push(tmp);
          }
          this.serv.sendTimeRoomConstraints(timeRoomConstraintExp);
        }
      }
    }
  }
}
