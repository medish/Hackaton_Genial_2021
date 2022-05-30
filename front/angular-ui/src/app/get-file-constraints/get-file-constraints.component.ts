import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { faFileArrowUp } from '@fortawesome/free-solid-svg-icons';
import { ConstraintPrecedence } from '../model/constraint/constraint-precedence';
import { ConstraintTimeRoom } from '../model/constraint/constraint-time-room';
import { ConstraintService } from '../services/constraint/constraint.service';
import { DataInterfaceService } from '../services/data-interface.service';
import { MessagingService } from '../services/messaging.service';
import { skip } from 'rxjs';

import { AlertComponent } from 'ngx-bootstrap/alert';

export enum Modes {
  CSV = 'Par fichier CSV',
  FORM = 'Par formulaire',
}

@Component({
  selector: 'app-get-file-constraints',
  templateUrl: './get-file-constraints.component.html',
  styleUrls: ['./get-file-constraints.component.scss'],
  providers: [DataInterfaceService]
})
export class GetFileConstraintsComponent implements OnInit {

  constructor(private constraintService: ConstraintService, private messagingService: MessagingService) {
    messagingService.currentMessageState.pipe(skip(1)).subscribe(value => {
      this.isOpenError = !value;
      this.isOpen = value;
    })
  }
  @Output('onAddConstraintTimeRoom')onAddConstraintTimeRoom = new EventEmitter<ConstraintTimeRoom[]>()
  @Output('onAddConstraintPrecedence')onAddConstraintPrecedence = new EventEmitter<ConstraintPrecedence[]>();

  ngOnInit(): void {
  }

  currentFileNameTimeAndRoom = '';
  currentTimeAndRoom: ConstraintTimeRoom[] = [];
  currentFileNamePrecedence = '';
  currentPrecedence: ConstraintPrecedence[] = [];
  errorMessageTimeAndRoom = '';
  errorMessagePrecedence = '';
  Modes = Modes;
  constraintsMode: string;
  constraintsModes: string[] = [Modes.CSV, Modes.FORM];
  faFileArrowUp = faFileArrowUp;

  public isOpen: boolean = false;
  dismissible: boolean = true;
  timeout: number = 10000;
  timeoutError: number = 20000;
  public isOpenError: boolean = false;

  onFileSelectedTimeAndRoom(event: any) {
    const files = event.target.files;
    const fileName = files[0].name;
    const inputFile = document.getElementById('time-and-room-button');
    inputFile.innerText = fileName;
    let fileReader = new FileReader();
    fileReader.onload = (e) => {
      let result = this.constraintService.parseConstraintsTimeAndRoom(
        fileReader.result?.toString()
      );
      this.errorMessageTimeAndRoom = '';
      if (!result) {
        this.isOpenError = true;
      } else {
        let result = this.constraintService.parseConstraintsTimeAndRoom(
          fileReader.result?.toString()
        );
        this.errorMessageTimeAndRoom = '';
        if (!result) {
          this.isOpenError = true;
        } else {
          this.currentTimeAndRoom = result;
        }
      }
      if (event?.target?.files?.length > 0) {
        this.currentFileNameTimeAndRoom = event.target.files[0].name;
        fileReader.readAsText(event.target.files[0]);
      }
    }
  }

  sendFileSelectedTimeAndRoom() {
    if (this.currentTimeAndRoom?.length > 0) {
      console.log(this.currentTimeAndRoom);
      this.onAddConstraintTimeRoom.emit(this.currentTimeAndRoom);
    }
  }

  onFileSelectedPrecedence(event: any) {
    const files = event.target.files;
    const fileName = files[0].name;
    const inputFile = document.getElementById('ordering_button');
    inputFile.innerText = fileName;
    let fileReader = new FileReader();
    fileReader.onload = (e) => {
      let result = this.constraintService.parseConstraintsPrecedence(
        fileReader.result?.toString()
      );
      this.errorMessagePrecedence = '';
      if (!result) {
        window.alert(
          'Une erreur de syntaxe se trouve dans le fichier. Veuillez réessayer'
        );
      } else {
        this.currentPrecedence = result;
      }
    };
    if (event?.target?.files?.length > 0) {
      this.currentFileNamePrecedence = event.target.files[0].name;
      fileReader.readAsText(event.target.files[0]);
    }
  }

  sendFileSelectedPrecedence() {
    if (this.currentPrecedence?.length > 0) {
      this.onAddConstraintPrecedence.emit(this.currentPrecedence);
    }
  }
}
