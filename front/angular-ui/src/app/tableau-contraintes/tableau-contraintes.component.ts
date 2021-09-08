import { Component, OnInit } from '@angular/core';
import { Constraint } from '../model/constraint/constraint';
import { ConstraintPrecedence } from '../model/constraint/constraint-precedence';
import { ConstraintTimeRoom } from '../model/constraint/constraint-time-room';
import { DataInterfaceService } from '../services/data-interface.service';
@Component({
  selector: 'tableau-contraintes',
  templateUrl: './tableau-contraintes.component.html',
  styleUrls: ['./tableau-contraintes.component.scss']
})


export class TableauContraintesComponent implements OnInit {

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
  }
  

}
