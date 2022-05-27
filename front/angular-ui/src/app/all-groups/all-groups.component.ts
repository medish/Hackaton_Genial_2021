import {Component, OnInit} from '@angular/core';
import {data, get} from 'jquery';
import {Modal} from 'bootstrap';
import {
  Course,
  CoursecontrollerApi,
  CourseGroup,
  CoursegroupcontrollerApi, CourseGroupRoomTypeEnum,
  RoomcontrollerApi,
} from "../model/swagger/api";
import {document} from "ngx-bootstrap/utils";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-all-groups',
  templateUrl: './all-groups.component.html',
  styleUrls: ['./all-groups.component.scss']
})
export class AllGroupsComponent implements OnInit {

  constructor(private coursegroupcontroller: CoursegroupcontrollerApi, private course: CoursecontrollerApi) {

  }


  all_groups: CourseGroup[];
  all_courses: Course[];
  all_rooms_type: CourseGroupRoomTypeEnum;
  array_filter: any[] = [];


  formGroupModal = new FormGroup({
    groupe_name: new FormControl(),
    niveau: new FormControl(),
    cours_name: new FormControl(),
    duration: new FormControl(),
    roomtype: new FormControl(),
    nb_students: new FormControl(),
  })


  async ngOnInit(): Promise<void> {
    this.all_groups = await this.coursegroupcontroller.getAllUsingGET1().then(data => {
      console.log(data);
      return data;
    })

    console.log(this.all_rooms_type);
    this.course.getAllUsingGET().then(data => {
      this.all_courses = data;
      console.log(data);
    })
    this.filterByGroup();
  }


  get(x: CourseGroup) {
    return [x.groupId, x.course.name, x.course.degree.name, x.duration, x.roomType]
  }

  filterByGroup() {
    this.array_filter = this.all_groups.map(this.get);
  }

  editGroup(group: CourseGroup) {

    const modal = new Modal(document.getElementById("modal-group"), {
      keyboard: false
    });
    document.getElementById('div_groupe_name').hidden = false;
    this.formGroupModal.controls['groupe_name'].setValue("groupe " + group.groupId);
    this.formGroupModal.controls['groupe_name'].disable();
    this.formGroupModal.controls['duration'].setValue(group.duration);
    this.formGroupModal.controls['roomtype'].setValue(group.roomType);
    this.formGroupModal.controls['cours_name'].setValue(group.course.id);
    this.formGroupModal.controls['nb_students'].setValue(group.size);
    modal.show();
  }


  deleteGroup(group: CourseGroup) {
    const modal = new Modal(document.getElementById("modal-delete-group"));
    modal.show();
  }


  addCourseGroup() {
    let modal = new Modal(document.getElementById("modal-group"));
    document.getElementById('div_groupe_name').hidden = true;
    this.formGroupModal.reset();
    modal.show();
  }


}
