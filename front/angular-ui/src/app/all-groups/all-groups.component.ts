import {Component, OnInit} from '@angular/core';
import {get} from 'jquery';
import {CourseGroup, CoursegroupcontrollerApi} from "../model/swagger/api";

@Component({
  selector: 'app-all-groups',
  templateUrl: './all-groups.component.html',
  styleUrls: ['./all-groups.component.scss']
})
export class AllGroupsComponent implements OnInit {

  constructor(private coursegroupcontroller: CoursegroupcontrollerApi) {

  }

  all_groups: CourseGroup[];
  array_filter: any[] = [];

  async ngOnInit(): Promise<void> {
    this.all_groups = await this.coursegroupcontroller.getAllUsingGET1().then(data => {
      console.log(data);
      return data;
    })
    this.filterByGroup();
  }


  get(x: CourseGroup) {
    return [x.groupId, x.course.name, x.course.degree.name, x.duration,x.roomType]
  }

  filterByGroup() {
    this.array_filter = this.all_groups.map(this.get);
  }

}
