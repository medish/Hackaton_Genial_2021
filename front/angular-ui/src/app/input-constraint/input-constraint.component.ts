import { Component, OnInit } from '@angular/core';
import { ConstraintTimeRoom } from '../model/constraint/constraint-time-room';
import { ConstraintService } from '../services/constraint/constraint.service';
import { DataInterfaceService } from '../services/data-interface.service';

@Component({
  selector: 'app-input-constraint',
  templateUrl: './input-constraint.component.html',
  styleUrls: ['./input-constraint.component.scss']
})
export class InputConstraintComponent implements OnInit {

  constructor(private constraintService: ConstraintService, private dataInterface: DataInterfaceService) { }

  ngOnInit(): void {
    this.dataInterface.fetchTimeConstraints(this.onTimeConstraintsReceived);
  }

  currentFileName:string='';

  onTimeConstraintsReceived(constraints: [ConstraintTimeRoom]) {
    // handle constraints
    console.log(constraints);
  }

  onFileSelected(event:any){
    let fileReader = new FileReader();
    fileReader.onload = (e) => {

      this.constraintService.parseConstraints(fileReader.result?.toString());

    }
    if(event?.target?.files?.length > 0){
      this.currentFileName = event.target.files[0].name
      fileReader.readAsText(event.target.files[0]);
    }
  }

  //parseConstraints<Type>(constraints: [Constraint]) {

  //}
}
