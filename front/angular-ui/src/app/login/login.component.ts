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

  ngOnInit(): void {
    console.log('logged in', this.authService.isLoggedIn())
    if (this.authService.isLoggedIn())
      this.router.navigateByUrl("/");
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
      this.tokenStorage.saveToken(data.accessToken);
      this.tokenStorage.saveUser(data);
      this.isLoginFailed = false;
      console.log(data)
      window.location.reload();
      console.log(data)
    }, err => {
      this.isLoginFailed = true;
      console.log("login failed")
    })
  }
}
