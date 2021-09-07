import { Component, OnInit } from '@angular/core';
import { ConstraintService } from '../services/constraint/constraint.service';

@Component({
  selector: 'app-input-constraint',
  templateUrl: './input-constraint.component.html',
  styleUrls: ['./input-constraint.component.scss']
})
export class InputConstraintComponent implements OnInit {

  constructor(public constraintService:ConstraintService) { }

  ngOnInit(): void {
  }

  currentFileName:string='';

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
}
