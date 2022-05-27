import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {User} from "../model/user";
import {environment} from "../../environments/environment";
import {Router} from "@angular/router";

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
}

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  constructor(private http: HttpClient, private router: Router) { }

  register(lastName: string, firstName: string, email: string, password: string) {
    let id = this.http.post(
      environment.baseUrl + '/register?lastName='+lastName+"&firstName="+firstName+"&email="+email+"&password="+password, httpOptions)
      .pipe()
      .toPromise()
      .then(data => this.router.navigate(["/login"]));
    if (id) {
      console.log(id);
    }
  }
}
