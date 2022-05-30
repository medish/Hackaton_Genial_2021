import {Component, OnInit} from '@angular/core';
import {CoursecontrollerApi, Major, MajorcontrollerApi, Professor, ProfessorcontrollerApi} from "../model/swagger/api";
import {Course} from "../model/swagger/api";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {document} from "ngx-bootstrap/utils";
import {Modal} from 'bootstrap';
import {CoursService} from "../services/cours.service";
import {DataInterfaceService} from "../services/data-interface.service";
import {HttpClient} from "@angular/common/http";
import {logger} from "html2canvas/dist/types/core/__mocks__/logger";
import {environment} from "../../environments/environment";

@Component({
  selector: 'app-all-courses',
  templateUrl: './all-courses.component.html',
  styleUrls: ['./all-courses.component.scss']
})

export class AllCoursesComponent implements OnInit {

  constructor(private coursesController: CoursecontrollerApi, private coursService: CoursService,
              private fromBuilderAddCours: FormBuilder, private fromBuilderEditCours: FormBuilder,
              private dataInterfaceService: DataInterfaceService, private majorcontrollerApi: MajorcontrollerApi,
              private professorcontrollerApi: ProfessorcontrollerApi,
              private coursControllerApi: CoursecontrollerApi, private http: HttpClient) {
  }

  all_courses: Course[] = []
  degrees = [];
  majors: Major [] = [];
  professors: Professor[];

  fromEditCours: FormGroup;
  submittedEditCours: boolean = false

  formAddCours: FormGroup;
  submittedAddCours: boolean = false;
  loadingAddCours: boolean = false;

  ngOnInit(): void {
    this.coursesController.getAllUsingGET().then(data => {
      this.all_courses = data;
      console.log(data);
    });

    this.majorcontrollerApi.getAllUsingGET5().then(data => {
      console.log(data);
      this.majors = data;
    })

    this.professorcontrollerApi.getAllUsingGET8().then(data => {
      console.log(data);
      this.professors = data;
    })


    this.dataInterfaceService.fetchAllDegrees(this.onDegreesReceived, this);

    this.formAddCours = this.fromBuilderAddCours.group({
      name: ['', [Validators.required]],
      degree: ['', [Validators.required]],
      color: ['', [Validators.required, Validators.max(6)]],
      major: ['', [Validators.required]],
      professor: ['', [Validators.required]]
    });

    this.fromEditCours = this.fromBuilderEditCours.group({
      name: ['', [Validators.required]],
      degree: ['', [Validators.required]],
      color: ['', [Validators.required]],
      major: ['', [Validators.required]],
      professor: ['', [Validators.required]]
    })
  }

  onDegreesReceived(degrees, context: this) {
    for (let degree of degrees) {
      context.degrees.push(degree)
    }
  }

  addCours() {
    const modal = new Modal(document.getElementById('addCoursModal'), {
      keyboard: false
    }).show();
  }

  getProf(id: number) {
    return this.professors.filter(x => x.id == id)[0] ?? null;
  }

  getDegree(id: number) {
    return this.degrees.filter(x => x.id == id)[0] ?? null;
  }

  getMajor(id: number) {
    return this.majors.filter(x => x.id == id)[0] ?? null;
  }

  get f() {
    return this.fromEditCours.controls;
  }

  get g() {
    return this.formAddCours.controls;
  }

  editCours(cours) {
    const modal = new Modal(document.getElementById("editCoursModal"), {
      keyboard: false
    });

    this.fromEditCours.controls['name'].setValue(cours.name);
    this.fromEditCours.controls['degree'].setValue(cours);
    modal.show();
  }

  updateCours() {
    this.submittedEditCours = true;

    if (this.fromEditCours.invalid) {
      return;
    }

    /*this.coursService.updateCours(this..controls['degree'].value,
      this.fromEditDegree.controls['name'].value);*/

    const modal = new Modal(document.getElementById("editUserModal"), {
      keyboard: false
    });
    modal.close();
  }

  onSubmitAddCours() {
    this.submittedAddCours = true;

    if (this.formAddCours.invalid) {
      return;
    }

    this.loadingAddCours = true;

    this.coursService.addCours(this.formAddCours.controls, this.getProf(this.formAddCours.controls['professor'].value),
      this.getDegree(this.formAddCours.controls['degree'].value), this.getMajor(this.formAddCours.controls['major'].value));
  }
}
