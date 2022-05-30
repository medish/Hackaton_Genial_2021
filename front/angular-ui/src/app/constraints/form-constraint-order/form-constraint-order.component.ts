import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {ConstraintService} from "../../services/constraint/constraint.service";
import {
  Course,
  CoursecontrollerApi,
} from "../../model/swagger/api";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ConstraintPrecedence} from "../../model/constraint/constraint-precedence";

@Component({
  selector: 'app-form-constraint-order',
  templateUrl: './form-constraint-order.component.html',
  styleUrls: ['./form-constraint-order.component.scss']
})
export class FormConstraintOrderComponent implements OnInit {

  constructor(
    public constraintService:ConstraintService,
    private courseController: CoursecontrollerApi,
    private formBuilder: FormBuilder
  ) { }

  @Output('onValidate')onValidate = new EventEmitter<ConstraintPrecedence>()

  wants = [
    'Souhaite',
    'Ne souhaite pas'
  ];

  stricts = [
    'Oui',
    'Non'
  ];

  whens = [
    'Avant',
    'Après',
    'Synchro'
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
  courses : Course[] = [];
  orderFormGroup: FormGroup;

  onSubmit() {
    this.submitted = true;
    this.validated = false;
    /*if(this.orderFormGroup.invalid){
      return;
    }*/

    let form_result= [
      "cours:id:"+this.orderFormGroup.value.firstCourse,
      this.orderFormGroup.value.want == 'souhaite' ? 'true':'false',
      this.orderFormGroup.value.when == "Avant" ? 'before':this.orderFormGroup.value.when == 'Après'?"after":"synchro",
      this.orderFormGroup.value.strict == "Oui" ? 'true':'false',
      "cours:id:"+this.orderFormGroup.value.secondCourse,
      this.orderFormGroup.value.priority.toString(),
    ];

    let selector1 = form_result[0].split(':');
    let selector2 = form_result[4].split(':');

    let orderConstraint: ConstraintPrecedence = {
      id: undefined,
      selector: {selectorUnits: [{table: selector1[0], attribute: selector1[1], value: selector1[2]}]},
      wants: form_result[1],
      whenConstraint: this.orderFormGroup.value.when.toLowerCase(),
      strict: form_result[3],
      selectorTarget: {selectorUnits: [{table: selector2[0], attribute: selector2[1], value: selector2[2]}]},
      priority: this.orderFormGroup.value.priority
    }

    this.validated = this.constraintService.verifySplitLinePrecedence(form_result)
    if (this.validated)
      this.onValidate.emit(orderConstraint)
  }



  ngOnInit(): void {

    this.courseController.getAllUsingGET().then(data=> {
      for (let course of data)
        this.courses.push(course)
    })

    this.orderFormGroup = this.formBuilder.group({
      firstCourse: ['', [Validators.required]],
      want: ['', [Validators.required]],
      when: ['', [Validators.required]],
      strict: ['', [Validators.required]],
      target: ['', [Validators.required]],
      secondCourse: ['', Validators.required],
      priority: ['',  [Validators.required]]
    })
  }

}
