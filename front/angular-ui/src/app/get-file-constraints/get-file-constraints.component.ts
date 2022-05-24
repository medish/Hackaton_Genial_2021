import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { ConstraintPrecedence } from '../model/constraint/constraint-precedence';
import { ConstraintTimeRoom } from '../model/constraint/constraint-time-room';
import { ConstraintService } from '../services/constraint/constraint.service';
import { faFileArrowUp } from "@fortawesome/free-solid-svg-icons";
import {FileChangeEvent} from "@angular/compiler-cli/src/perform_watch";

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
    const inputs = document.querySelectorAll('.inputfile');
    console.log("inputs: " + inputs.length + "\n");
    Array.prototype.forEach.call(inputs, function(input : Element) {
        /*const labelVal : string = input.innerHTML;
      input.addEventListener('change', function(event ) {
        //console.log("event type: " + typeof event);
        let fileName: string = '';
        if(this.files && this.files.length > 1)
          fileName = (this.getAttribute('data-multiple-caption' || '').replace('{count}', this.files.length));
        else
          //fileName = currentEvent.target.value.split('\\').pop();
          fileName = "heehe";
        if(fileName)
          input.querySelector('span').innerHTML = fileName;
        else
          input.innerHTML = labelVal;
      })*/
    })

  }
  faFileArrowUp = faFileArrowUp;
  currentFileNameTimeAndRoom='';
  currentTimeAndRoom:ConstraintTimeRoom[]=[];
  currentFileNamePrecedence='';
  currentPrecedence:ConstraintPrecedence[]=[];
  errorMessageTimeAndRoom='';
  errorMessagePrecedence='';
  onFileSelectedTimeAndRoom(event:any){
    let fileReader = new FileReader();
    fileReader.onload = (e) => {
      let result = this.constraintService.parseConstraintsTimeAndRoom(fileReader.result?.toString());
      this.errorMessageTimeAndRoom = ''
      if(!result){
        console.log("error")
        this.errorMessageTimeAndRoom = 'Syntax error'
      }else{
        this.currentTimeAndRoom = result;
      }
    }
    if(event?.target?.files?.length > 0){
      this.currentFileNameTimeAndRoom = event.target.files[0].name;
      fileReader.readAsText(event.target.files[0]);
    }
  }
  sendFileSelectedTimeAndRoom(){
    if(this.currentTimeAndRoom?.length>0){
      console.log(this.currentTimeAndRoom)
      this.onAddConstraintTimeRoom.emit(this.currentTimeAndRoom);
    }
  }
  onFileSelectedPrecedence(event:any){
    let fileReader = new FileReader();
    fileReader.onload = (e) => {
      let result = this.constraintService.parseConstraintsPrecedence(fileReader.result?.toString());
      this.errorMessagePrecedence = ''
      if(!result){
        this.errorMessagePrecedence = 'Syntax error';
      }else{
        this.currentPrecedence = result;
      }
    }
    if(event?.target?.files?.length > 0){
      this.currentFileNamePrecedence = event.target.files[0].name
      fileReader.readAsText(event.target.files[0]);
    }
  }

  sendFileSelectedPrecedence(){
    if(this.currentPrecedence?.length>0){
      this.onAddConstraintPrecedence.emit(this.currentPrecedence);
    }
  }
}
