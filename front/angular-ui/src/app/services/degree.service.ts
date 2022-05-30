import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {
  Degree,
} from "../model/swagger/api";

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'}),
}

@Injectable({
  providedIn: 'root'
})

export class DegreeService {

  constructor(private http: HttpClient) {
  }

  addDegree(name: string) {
    let degree: Degree = {};
    degree.name= name;

    return this.http.post(
      environment.baseUrl + '/degrees/', degree, httpOptions)
      .pipe().toPromise().then(data => window.location.reload())
      .catch(err => console.log(err))
  }

  updateDegree(degree: Degree, name: string) {
    degree.name = name;

    return this.http.put(
      environment.baseUrl + '/degrees/', degree, httpOptions)
      .pipe().toPromise().then(data => window.location.reload())
      .catch(err => console.log(err))
  }
}
