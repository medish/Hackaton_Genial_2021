import {Component, OnInit} from '@angular/core';
import {DataInterfaceService} from "../services/data-interface.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {document} from "ngx-bootstrap/utils";
import {Modal} from 'bootstrap';
import {UserService} from "../services/user.service";

@Component({
  selector: 'app-all-users',
  templateUrl: './all-users.component.html',
  styleUrls: ['./all-users.component.scss']
})
export class AllUsersComponent implements OnInit {

  constructor(private dataService: DataInterfaceService,
              private formBuilder: FormBuilder, private userService: UserService) {
  }

  formGroupModal: FormGroup;
  users = [];

  ngOnInit(): void {
    this.dataService.fetchAllUsers(this.onUsersReceived, this)
    this.formGroupModal = this.formBuilder.group({
      lastName: ['', [Validators.required, Validators.max(255), Validators.min(20)]],
      firstname: ['', [Validators.required, Validators.max(255), Validators.min(20)]],
      email: ['', [Validators.required, Validators.email]],
      id: [],
      role: ['', [Validators.required]]
    });
  }

  onUsersReceived(users, context: this) {
    for (let user of users) {
      context.users.push(user)
    }
  }

  editUser(user) {
    const modal = new Modal(document.getElementById("editUserModal"), {
      keyboard: false
    });

    console.log(user);
    this.formGroupModal.controls['lastName'].setValue(user.lastName);
    this.formGroupModal.controls['firstname'].setValue(user.firstName);
    this.formGroupModal.controls['email'].setValue(user.email);
    this.formGroupModal.controls['id'].setValue(user.id)
    //this.formGroupModal.controls['isAdmin'].setValue(user.isAdmin);
    modal.show();
  }

  deleteUser(user) {
    this.userService.deleteUser(user.id);
  }

  updateUser() {
    console.log(this.formGroupModal.controls['role'], this.formGroupModal.controls['lastName'])
    this.userService.updateUser(this.formGroupModal.controls['id'].value,
      this.formGroupModal.controls['lastName'].value, this.formGroupModal.controls['firstname'].value,
      this.formGroupModal.controls['email'].value, this.formGroupModal.controls['role'].value);
  }
}
