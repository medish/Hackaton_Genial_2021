import {Component, OnInit} from '@angular/core';
import {DataInterfaceService} from "../services/data-interface.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {document} from "ngx-bootstrap/utils";
import {Modal} from 'bootstrap';
import {UserService} from "../services/user.service";
import {AuthService} from "../services/auth.service";

@Component({
  selector: 'app-all-users',
  templateUrl: './all-users.component.html',
  styleUrls: ['./all-users.component.scss']
})
export class AllUsersComponent implements OnInit {

  constructor(private authService: AuthService,
              private dataService: DataInterfaceService,
              private formBuilderAddUser: FormBuilder,
              private formBuilder: FormBuilder, private userService: UserService) {
  }

  formGroupModal: FormGroup;
  submitted: boolean = false;
  submitted_edit = false;
  users = [];
  loading: boolean = false;
  formAddUser: FormGroup

  ngOnInit(): void {
    this.dataService.fetchAllUsers(this.onUsersReceived, this)
    this.formGroupModal = this.formBuilder.group({
      lastName: ['', [Validators.required, Validators.max(255), Validators.min(20)]],
      firstname: ['', [Validators.required, Validators.max(255), Validators.min(20)]],
      email: ['', [Validators.required, Validators.email]],
      id: [],
      role: ['', [Validators.required]]
    });

    this.formAddUser = this.formBuilderAddUser.group({
      lastName: ['', [Validators.required, Validators.max(255), Validators.min(20)]],
      firstName: ['', [Validators.required, Validators.max(255), Validators.min(20)]],
      email: ['', [Validators.required, Validators.email]],
      id: [],
      role: ['', [Validators.required]],
      password: ['', [Validators.required, Validators.min(6)]]
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

    this.formGroupModal.controls['lastName'].setValue(user.lastName);
    this.formGroupModal.controls['firstname'].setValue(user.firstName);
    this.formGroupModal.controls['email'].setValue(user.email);
    this.formGroupModal.controls['id'].setValue(user.id)
    this.formGroupModal.controls['role'].setValue(user.role);
    modal.show();
  }

  deleteUser(user) {
    this.userService.deleteUser(user.id);
  }

  updateUser() {
    this.submitted_edit = true;

    if (this.formGroupModal.invalid) {
      return;
    }

    this.userService.updateUser(this.formGroupModal.controls['id'].value,
      this.formGroupModal.controls['lastName'].value, this.formGroupModal.controls['firstname'].value,
      this.formGroupModal.controls['email'].value, this.formGroupModal.controls['role'].value);
    const modal = new Modal(document.getElementById("editUserModal"), {
      keyboard: false
    });
    modal.close();
  }

  get f() {
    return this.formAddUser.controls;
  }

  get g() {
    return this.formGroupModal.controls;
  }

  addUser() {
    const modal = new Modal(document.getElementById('addUserModal'), {
      keyboard: false
    }).show();
  }

  onSubmitAddUser() {
    this.submitted = true;

    if (this.formAddUser.invalid) {
      return;
    }

    this.loading = true;

    this.userService.addUser(this.formAddUser.controls['lastName'].value,
      this.formAddUser.controls['firstName'].value, this.formAddUser.controls['email'].value,
      this.formAddUser.controls['role'].value, this.formAddUser.controls['password'].value)
  }
}
