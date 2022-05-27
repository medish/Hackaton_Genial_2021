import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import { ConstraintTimeRoom } from '../../model/constraint/constraint-time-room';
import { ConstraintService } from '../../services/constraint/constraint.service';
import {addUnitPriority} from "ngx-bootstrap/chronos/units/priorities";
import {Selector} from "../../model/selector/selector";
import {SelectorUnit} from "../../model/selector/selector-unit";
import {Professor,ProfessorcontrollerApi,Course,CoursecontrollerApi,Room,RoomcontrollerApi} from '../../model/swagger/api'
import {FormBuilder, FormGroup, Validators} from "@angular/forms";


@Component({
  selector: 'app-form-constraint-time',
  templateUrl: './form-constraint-time.component.html',
  styleUrls: ['./form-constraint-time.component.scss']
})
export class FormConstraintTimeComponent implements OnInit {

  constructor(
    public constraintService:ConstraintService,
    private professorController: ProfessorcontrollerApi,
    private courseController: CoursecontrollerApi,
    private roomController: RoomcontrollerApi,
    private formBuilder: FormBuilder
  ) {}


  @Output('onValidate')onValidate = new EventEmitter<ConstraintTimeRoom>()


  days = [
    'Lundi','Mardi', 'Mercredi', 'Jeudi', 'Vendredi'
  ];

  wants = [
    'souhaite',
    'ne souhaite pas'
  ];

  selectors = [
    {
      label:'Professeur',
      value:'teacher'
    },
    {
      label: 'Cours',
      value: 'lesson'
    }
  ]

  submitted = false;
  validated = false;
  professors : Professor[] = [];
  courses : Course[] = [];
  rooms : Room[] = [];
  timeFormGroup: FormGroup;

  onSubmit() {

    this.submitted = true;
    this.validated = false;
    if(this.timeFormGroup.invalid){
      return;
    }

    let form_result= [
      this.timeFormGroup.value.selector+":id:"+this.timeFormGroup.value.selectorTarget,
      this.timeFormGroup.value.want == 'souhaite' ? 'true':'false',
      (this.days.indexOf(this.timeFormGroup.value.day)+1).toString(),
      this.timeFormGroup.value.startTime+":00",
      this.timeFormGroup.value.endTime+":00",
      "room:id:"+this.timeFormGroup.value.room,
      this.timeFormGroup.value.priority.toString()
    ];

    let slct = form_result[0].split(':')
    let r = form_result[5].split(':')

    let timeConstraint: ConstraintTimeRoom = {
      id: undefined,
      selector: {selectorUnits: [{table: slct[0], attribute: slct[1], value: slct[2]}]},
      wants: form_result[1] == true,
      day: parseInt(form_result[2]),
      dateBegin: form_result[3],
      dateEnd: form_result[4],
      room: {selectorUnits: [{table: r[0], attribute: r[1], value: r[2]}]},
      priority: parseInt(form_result[6])
    }

    this.validated = this.constraintService.verifySplitLineConstraintsTimeRoom(form_result);
    if(this.validated){
      this.onValidate.emit(timeConstraint);
    }
  }

  ngOnInit(): void {
    this.professorController.getAllUsingGET8().then(data=>{
      for(let prof of data){
        this.professors.push(prof);
      }
    });

    this.courseController.getAllUsingGET().then(data=>{
      for(let course of data){
        this.courses.push(course);
      }
    });

    this.roomController.getAllUsingGET9().then(data=>{
      for(let room of data){
        this.rooms.push(room);
      }
    });

    this.timeFormGroup = this.formBuilder.group({
      selector: ['', [Validators.required]],
      selectorTarget: ['', [Validators.required]],
      want: ['', [Validators.required]],
      day: ['', [Validators.required]],
      startTime: ['', [Validators.required]],
      endTime: ['', [Validators.required]],
      room: ['', [Validators.required]],
      priority: ['',[Validators.required]]
    });
  }
}
