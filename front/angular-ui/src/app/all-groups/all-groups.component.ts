import {Component, OnInit} from '@angular/core';
import {CourseGroup, CoursegroupcontrollerApi} from "../model/swagger/api";

@Component({
  selector: 'app-all-groups',
  templateUrl: './all-groups.component.html',
  styleUrls: ['./all-groups.component.scss']
})
export class AllGroupsComponent implements OnInit {

  constructor(private coursegroupcontroller: CoursegroupcontrollerApi) {
  }

  all_groups : CourseGroup[];

  ngOnInit(): void {
    this.coursegroupcontroller.getAllUsingGET1().then(data => {
      this.all_groups = data;
    })
  }





}
