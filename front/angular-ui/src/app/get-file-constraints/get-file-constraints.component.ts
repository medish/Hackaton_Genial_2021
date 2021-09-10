import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { ConstraintPrecedence } from '../model/constraint/constraint-precedence';
import { ConstraintTimeRoom } from '../model/constraint/constraint-time-room';
import { ConstraintService } from '../services/constraint/constraint.service';

@Component({
  selector: 'app-get-file-constraints',
  templateUrl: './get-file-constraints.component.html',
  styleUrls: ['./get-file-constraints.component.scss']
})
export class GetFileConstraintsComponent implements OnInit {

  constructor(public constraintService:ConstraintService) { }
  @Output('onAddConstraintTimeRoom')onAddConstraintTimeRoom = new EventEmitter<ConstraintTimeRoom[]>()
  @Output('onAddConstraintPrecedence')onAddConstraintPrecedence = new EventEmitter<ConstraintPrecedence[]>();
  ngOnInit(): void {
  }
  currentFileNameTimeAndRoom='';
  currentFileNamePrecedence='';
  errorMessageTimeAndRoom='';
  errorMessagePrecedence='';
  onFileSelectedTimeAndRoom(event:any){
    let fileReader = new FileReader();
    fileReader.onload = (e) => {
      let result = this.constraintService.parseConstraintsTimeAndRoom(fileReader.result?.toString());
      this.errorMessageTimeAndRoom = ''
      if(result){
        this.onAddConstraintTimeRoom.emit(result);
      }else{
        this.errorMessageTimeAndRoom = 'Error on upload of time and rooms constraints file'
      }
    }
    if(event?.target?.files?.length > 0){
      this.currentFileNameTimeAndRoom = event.target.files[0].name;
      fileReader.readAsText(event.target.files[0]);
    }
  }

  onFileSelectedPrecedence(event:any){
    let fileReader = new FileReader();
    fileReader.onload = (e) => {
      let result = this.constraintService.parseConstraintsPrecedence(fileReader.result?.toString());
      this.errorMessagePrecedence = ''
      if(result){
        this.onAddConstraintPrecedence.emit(result);
      }else{
        this.errorMessagePrecedence = 'Error on upload of precedence constraints file';
      }
    }
    if(event?.target?.files?.length > 0){
      this.currentFileNamePrecedence = event.target.files[0].name
      fileReader.readAsText(event.target.files[0]);
    }
  }
}
