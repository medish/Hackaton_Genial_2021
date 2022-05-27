import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {environment} from "../../environments/environment";

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'}),
}

@Injectable({
  providedIn: 'root'
})
export class ConstraintService {

  constructor(private http: HttpClient) { }

  public deletePrecedenceConstraint(precedence: string) {
    return this.http.delete(environment.baseUrl + '/constraints/precedence?id=' + precedence, httpOptions)
      .pipe().toPromise().then(data => window.location.reload()).catch(err => {
        console.log("error on delete precedence constraint");
      })
  }

  public  deleteTimeConstraint(time: string) {
    return this.http.delete(environment.baseUrl + '/constraints/time-and-room?id=' + time, httpOptions)
      .pipe().toPromise().then(data => window.location.reload()).catch(err => {
        console.log("error on delete time constraint");
      })
  }
}
