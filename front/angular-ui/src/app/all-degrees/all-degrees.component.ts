import {Component, OnInit} from '@angular/core';
import {DataInterfaceService} from "../services/data-interface.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {document} from "ngx-bootstrap/utils";
import {Modal} from 'bootstrap';
import {DegreeService} from "../services/degree.service";
import {
  Degree,
} from "../model/swagger/api";

@Component({
  selector: 'app-all-degrees',
  templateUrl: './all-degrees.component.html',
  styleUrls: ['./all-degrees.component.scss']
})
export class AllDegreesComponent implements OnInit {

  constructor(private dataInterfaceService: DataInterfaceService, private fromBuilderEditDegree: FormBuilder,
              private formBuilderAddDegree: FormBuilder, private degreeService: DegreeService) {
  }

  degrees = [];
  fromEditDegree: FormGroup;
  submittedEditDegree: boolean = false

  formAddDegree: FormGroup;
  submittedAddDegree: boolean = false;
  loadingAddDegree: boolean = false;

  ngOnInit(): void {
    this.dataInterfaceService.fetchAllDegrees(this.onDegreesReceived, this);

    console.log(this.degrees)
    this.fromEditDegree = this.fromBuilderEditDegree.group({
      name: ['', [Validators.required]],
      degree: ['']
    })

    this.formAddDegree = this.formBuilderAddDegree.group({
      name: ['', [Validators.required]]
    })
  }

  addDegrees() {
    const modal = new Modal(document.getElementById('addDegreeModal'), {
      keyboard: false
    }).show();
  }

  onDegreesReceived(degrees, context: this) {
    for (let degree of degrees) {
      context.degrees.push(degree)
    }
  }

  get f() {
    return this.fromEditDegree.controls;
  }

  get g() {
    return this.formAddDegree.controls;
  }

  editDegree(degree) {
    const modal = new Modal(document.getElementById("editDegreeModal"), {
      keyboard: false
    });

    this.fromEditDegree.controls['name'].setValue(degree.name);
    this.fromEditDegree.controls['degree'].setValue(degree);
    modal.show();
  }

  updateDegree() {
    this.submittedEditDegree = true;

    if (this.fromEditDegree.invalid) {
      return;
    }

    this.degreeService.updateDegree(this.fromEditDegree.controls['degree'].value,
      this.fromEditDegree.controls['name'].value);

    const modal = new Modal(document.getElementById("editUserModal"), {
      keyboard: false
    });
    modal.close();
  }

  onSubmitAddDegree() {
    this.submittedAddDegree = true;

    if (this.formAddDegree.invalid) {
      return;
    }

    this.loadingAddDegree = true;

    this.degreeService.addDegree(this.formAddDegree.controls['name'].value)
  }
}
