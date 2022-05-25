import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {environment} from "../../environments/environment";

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'}),
  params: new HttpParams()
}

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) {
  }

  updateUser(id: string, name: string, firstname: string, email: string, isAdmin: boolean) {
    httpOptions.params = httpOptions.params.set("id", id).set("name", name).set("firstname", firstname).set("email", email).set("isAdmin", isAdmin)

    let user = this.http.post(
      environment.baseUrl + '/users', {}, httpOptions
    ).pipe().toPromise().then(data => window.location.reload()).catch(err => {
      console.log("err on update user")
    });

    if (user) {
      console.log(user);
    }
  }

  deleteUser(id: string) {
    return this.http.delete(environment.baseUrl+ '/users?id='+id, httpOptions)
      .pipe().toPromise().then(data => window.location.reload()).catch(err => {
        console.log("error on delete user");
      })
  }
}
