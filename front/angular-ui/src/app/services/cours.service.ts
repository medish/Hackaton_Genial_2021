import {Injectable} from '@angular/core';
import {Course, CoursecontrollerApi, Professor, ProfessorcontrollerApi} from "../model/swagger/api";

@Injectable({
  providedIn: 'root'
})
export class CoursService {

  constructor(private courscontrollerApi: CoursecontrollerApi, private professorApi: ProfessorcontrollerApi) {
  }

  addCours(controls, prof, degree, major) {
    let cours: Course = {};
    cours.color = controls['color'].value
    cours.degree = degree
    cours.name = controls['name'].value
    cours.professors = [prof];
    cours.majorCourses = [major]

    console.log(cours);

    this.courscontrollerApi.insertUsingPOST({course: cours}).then(data => window.location.reload());
  }
}
