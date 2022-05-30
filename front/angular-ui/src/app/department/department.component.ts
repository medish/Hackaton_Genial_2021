import {Component, OnInit} from '@angular/core';
import {Department, DepartmentcontrollerApi} from "../model/swagger/api";
import {document} from "ngx-bootstrap/utils";
import {Modal} from 'bootstrap';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {HttpClient} from '@angular/common/http';
import {environment} from "../../environments/environment";


@Component({
  selector: 'app-department',
  templateUrl: './department.component.html',
  styleUrls: ['./department.component.scss']
})
export class DepartmentComponent implements OnInit {

  constructor(private departmentcontrollerApi: DepartmentcontrollerApi, private http: HttpClient) {
  }

  all_departments: Department[] = [];

  ngOnInit(): void {
    this.departmentcontrollerApi.getAllUsingGET4().then(data => {
      console.log(data);
      this.all_departments = data;
    })
  }

  formDepModal = new FormGroup({
    name_department: new FormControl(null, Validators.required)
  })


  addDepartment() {
    let modal = new Modal(document.getElementById("modal-add-dep"));
    this.editing = false
    modal.show();

  }


  handleAction() {
    if (this.editing) {
      this.handlEdit()
    } else {
      this.handleAddDepartement()
    }
  }


  handlEdit() {
    console.log(this.department_to_edit)
    let res: Department;
    res = new class implements Department {

    }

    this.department_to_edit.name = "heh";
    this.http.put(environment.baseUrl + '/departments', {
      department: res
    }).subscribe(data => {
      console.log(data);
    })
  }


  handleAddDepartement() {
    if (this.formDepModal.invalid) {
      return;
    }
    let res: Department;
    res = new class implements Department {
    }
    res.name = this.formDepModal.value.name_department;

    this.departmentcontrollerApi.insertUsingPOST3({
      department: res
    }).then(data => {
        console.log(data);
        location.reload();
      }
    )
  }


  department_to_edit: Department = null;
  editing = false;

  editDepartment(department: Department) {
    this.formDepModal.controls['name_department'].setValue(department.name);
    this.department_to_edit = department;
    this.editing = true;
    let modal = new Modal(document.getElementById("modal-add-dep"))
    modal.show();
  }


  deleteDepartment(department: Department) {
    this.http.delete(environment.baseUrl + '/departments/' + department.id).subscribe(
      data => {
        console.log(data)
        location.reload();
      }
    );
  }


}
