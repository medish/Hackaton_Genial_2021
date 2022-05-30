import {Component, OnInit} from '@angular/core';
import {Department, DepartmentcontrollerApi} from "../model/swagger/api";

@Component({
  selector: 'app-department',
  templateUrl: './department.component.html',
  styleUrls: ['./department.component.scss']
})
export class DepartmentComponent implements OnInit {

  constructor(private departmentcontrollerApi: DepartmentcontrollerApi) {
  }

  all_departments: Department[] = [];

  ngOnInit(): void {
    this.departmentcontrollerApi.getAllUsingGET4().then(data => {
      console.log(data);
      this.all_departments = data;
    })
  }

  editDepartment(department: Department) {

  }

  deleteDepartment(department: Department) {

  }
}
