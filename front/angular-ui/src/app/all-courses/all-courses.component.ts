import {Component, OnInit} from '@angular/core';
import {CoursecontrollerApi} from "../model/swagger/api";
import {Course} from "../model/swagger/api";

@Component({
  selector: 'app-all-courses',
  templateUrl: './all-courses.component.html',
  styleUrls: ['./all-courses.component.scss']
})
export class AllCoursesComponent implements OnInit {

  constructor(private coursesController: CoursecontrollerApi) {
  }

  all_courses: Course[] = []

  ngOnInit(): void {
    this.coursesController.getAllUsingGET().then(data => {
      this.all_courses = data;
      console.log(data);
    })
  }

}
