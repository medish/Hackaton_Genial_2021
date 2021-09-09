import { Component, OnInit } from '@angular/core';
import { ConstraintService } from '../services/constraint/constraint.service';

@Component({
  selector: 'app-get-file-constraints',
  templateUrl: './get-file-constraints.component.html',
  styleUrls: ['./get-file-constraints.component.scss']
})
export class GetFileConstraintsComponent implements OnInit {

  constructor(public constraintService:ConstraintService) { }

  ngOnInit(): void {
  }
  currentFileNameTimeAndRoom='';
  currentFileNamePrecedence='';
  currentConstraintsTimeAndRoom=[];
  currentConstraintsPrecedence=[];

  onFileSelectedTimeAndRoom(event:any){
    let fileReader = new FileReader();
    fileReader.onload = (e) => {
      this.currentConstraintsTimeAndRoom = this.constraintService.parseConstraintsTimeAndRoom(fileReader.result?.toString());
    }
    if(event?.target?.files?.length > 0){
      this.currentFileNameTimeAndRoom = event.target.files[0].name
      fileReader.readAsText(event.target.files[0]);
    }
  }

  onFileSelectedPrecedence(event:any){
    let fileReader = new FileReader();
    fileReader.onload = (e) => {
      this.currentConstraintsPrecedence = this.constraintService.parseConstraintsPrecedence(fileReader.result?.toString());
    }
    if(event?.target?.files?.length > 0){
      this.currentFileNamePrecedence = event.target.files[0].name
      fileReader.readAsText(event.target.files[0]);
    }
  }
}
