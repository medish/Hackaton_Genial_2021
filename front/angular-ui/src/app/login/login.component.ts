import {Component, OnInit} from '@angular/core';
import {FormBuilder} from '@angular/forms';
import {Router} from '@angular/router';
import {AuthService} from '../services/auth.service';
import {TokenStorageService} from '../services/token-storage.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  constructor(public authService: AuthService, private tokenStorage: TokenStorageService, private router: Router) {
  }

  currentUser = null;

  ngOnInit(): void {
  }

  loginForm = {
    email: '',
    password: '',
  };
  isLoginFailed = false;
  errorMessage = '';


  onSubmitLogin(): void {
    const {email, password} = this.loginForm;
    this.authService.login(email, password).then(data => {
      if (data != null) {
        this.currentUser = data;
        this.tokenStorage.saveToken(data.accessToken);
        this.tokenStorage.saveUser(data);
        this.isLoginFailed = false;
        if (this.currentUser.role == "PROFESSOR") {
          this.router.navigateByUrl("/prof");
        } else if (this.currentUser.role == "ADMIN") {
          this.router.navigateByUrl("/admin")
        }
      }
    }, err => {
      this.isLoginFailed = true;
      console.log("login failed")
    })
  }

  register() {
    this.router.navigate(["/register"]);
  }
}
