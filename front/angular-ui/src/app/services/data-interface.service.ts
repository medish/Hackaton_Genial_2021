import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {identity, Observable, throwError } from 'rxjs';
import { ConstraintPrecedence, ConstraintPrecedenceExport } from '../model/constraint/constraint-precedence';
import { ConstraintTimeRoom, ConstraintTimeRoomExport } from '../model/constraint/constraint-time-room';
import { catchError, retry } from 'rxjs/operators';
import { Planning } from '../model/planning/planning';
import { environment } from 'src/environments/environment';
import {User} from "../model/user";

import {Degree, Department, Professor, Room} from '../model/swagger/api';
import { Lesson, RoomType } from '../model/datastore/datamodel';

@Injectable({
  providedIn: 'root'
})
export class DataInterfaceService {

  url = environment.baseUrl

  constructor(private http : HttpClient) { }

  signIn(username: string, password: string, callback: (wasCreated: boolean) => any) {
    const identity = {username: username, password: password};
    return this.http.post<boolean>(this.url + "/auth/signin", identity)
    .subscribe(data => callback(data));
  }

  fetchTimeConstraints(callback: (constraints: [ConstraintTimeRoom]) => any) {
    return this.http.get<[ConstraintTimeRoom]>(this.url + "/constraints/time-and-room")
    .subscribe(data => callback(data));
  }

  fetchPrecedenceConstraints(callback: (constraints: [ConstraintPrecedence]) => any) {
    return this.http.get<[ConstraintPrecedence]>(this.url + "/constraints/precedence")
    .subscribe(data => callback(data));
  }

  sendTimeRoomConstraints(constraints: [ConstraintTimeRoomExport]) {
    return this.http.post(this.url + "/constraints/time-and-room", constraints)
    .pipe(catchError(this.handleError)).subscribe();
  }

  sendPrecedenceConstraints(constraints: [ConstraintPrecedenceExport]) {
    return this.http.post(this.url + "/constraints/precedence", constraints)
    .pipe(catchError(this.handleError)).subscribe();
  }

  deleteTimeConstraints(constraintId: number) {
    return this.http.post(this.url + "/constraints/delete-time-and-room", constraintId)
    .pipe(catchError(this.handleError)
    ).subscribe()
  }

  deletePrecedenceConstraints(constraintId: number) {
    return this.http.post(this.url + "/constraints/delete-precedence", constraintId).pipe(
      catchError(this.handleError)
    ).subscribe()
  }

  fetchAllRooms(callback: (rooms: [Room], context: any) => any, context : any) {
    return this.http.get<[Room]>(this.url + "/rooms")
    .subscribe(data => callback(data, context));
  }

  fetchAllLessons(callback: (classes: [Lesson], context: any) => any, context: any) {
    return this.http.get<[Lesson]>(this.url + "/lessons")
    .subscribe(data => callback(data, context));
  }

  fetchAllRoomTypes(callback: (roomTypes: [RoomType]) => any) {
    return this.http.get<[RoomType]>(this.url + "/roomTypes")
    .subscribe(data => callback(data));
  }

  fetchAllTeachers(callback: (teachers: [Professor], context: any) => any, context: any) {
    return this.http.get<[Professor]>(this.url + "/professors")
    .subscribe(data => callback(data, context));
  }



  fetchAllDepartments(callback: (departments: [Department], context: any) => any, context: any) {
    return this.http.get<[Department]>(this.url + "/departments")
    .subscribe(data => callback(data,context));
  }


  fetchAllDegrees(callback: (degrees: [Degree], context: any) => any, context: any) {
    return this.http.get<[Degree]>(this.url + "/degrees")
    .subscribe(data => callback(data, context));
  }

  sendDataToCore<Type>(callback: (httpStatusCode: number) => any, data : Type) {
    return this.http.post<number>(this.url, data).pipe(catchError(this.handleError)).subscribe(
      data => callback(data)
    );
  }

  verifyConstraints(constraints: Planning, callback: (violatedConstraints: any) => any) {
    return this.http.post<number>(this.url + "/planning/verify", constraints)
    .pipe(catchError(this.handleError)).subscribe(data => callback(data));
  }


  fetchAllPlannings(callback: (plannings:[Planning],context:any)=>any, context:any){
    return this.http.get<[Planning]>(this.url+'/planning').subscribe(data=>callback(data,context))
  }

  handleError(error: HttpErrorResponse) {
    console.log("[HTTP ERROR]: " + error);
    window.alert("Une erreur est survenue");
    return throwError("An error happened");
  }

  fetchAllUsers(callback: (users: [User], context: any) => any, context: any) {
    return this.http.get<[User]>(this.url + "/users")
      .subscribe(data => callback(data, context));
  }
}
