import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute} from "@angular/router";
import {RegisterService} from "../services/register.service";
import {User} from "../model/user";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  form: FormGroup;
  submitted = false;
  loading = false;

  constructor(private formBuilder: FormBuilder,
              private route: ActivatedRoute,
              private registerService: RegisterService) {
  }

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      username: ['', [Validators.required, Validators.max(255), Validators.min(20)]],
      firstname: ['', [Validators.required, Validators.max(255)]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]]
    })
  }

  get f() {
    return this.form.controls;
  }

  onSubmitRegister(): void {
    this.submitted = true;

    if (this.form.invalid) {
      return;
    }

    this.loading = true;
    let user = new User();
    user.name = this.form.value.username;
    user.firstname = this.form.value.firstname;
    user.password = this.form.value.password;
    user.email = this.form.value.email;

    this.registerService.register(user);
  }
}
