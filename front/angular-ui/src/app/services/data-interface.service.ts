import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {Subject, throwError} from 'rxjs';
import { ConstraintPrecedence, ConstraintPrecedenceExport } from '../model/constraint/constraint-precedence';
import { ConstraintTimeRoom, ConstraintTimeRoomExport } from '../model/constraint/constraint-time-room';
import { catchError } from 'rxjs/operators';
import { Planning } from '../model/planning/planning';
import { environment } from 'src/environments/environment';
import {User} from "../model/user";
import {Degree, Department, Professor, Room, TimeConstraint} from '../model/swagger/api';

import { Lesson, RoomType } from '../model/datastore/datamodel';

@Injectable({
  providedIn: 'root'
})
export class DataInterfaceService {

  url = environment.baseUrl

  resultChange : Subject<boolean> = new Subject<boolean>();

  constructor(private http : HttpClient) {}

  handleError(error: HttpErrorResponse) {
    this.resultChange.next(false);
    return throwError(error);
  }

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

  sendTimeRoomConstraints(constraints: [any]) {
    return this.http.post(this.url + "/constraints/time-and-room/all", constraints)
    .pipe(catchError(this.handleError.bind(this))).subscribe((_ => this.resultChange.next(true)));
  }

  sendPrecedenceConstraints(constraints: [any]) {
    return this.http.post(this.url + "/constraints/precedence/all", constraints)
    .pipe(catchError(this.handleError.bind(this))).subscribe((_ => { this.resultChange.next(true)}));
  }

  deleteTimeConstraints(constraintId: number) {
    return this.http.post(this.url + "/constraints/delete-time-and-room", constraintId)
    .pipe(catchError(this.handleError.bind(this))
    ).subscribe()
  }

  deletePrecedenceConstraints(constraintId: number) {
    return this.http.post(this.url + "/constraints/delete-precedence", constraintId).pipe(
      catchError(this.handleError.bind(this))
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
    return this.http.post<number>(this.url, data).pipe(catchError(this.handleError.bind(this))).subscribe(
      data => callback(data)
    );
  }

  verifyConstraints(constraints: Planning, callback: (violatedConstraints: any) => any) {
    return this.http.post<number>(this.url + "/planning/verify", constraints)
    .pipe(catchError(this.handleError.bind(this))).subscribe(data => callback(data));
  }

  fetchAllPlannings(callback: (plannings:[Planning],context:any)=>any, context:any){
    return this.http.get<[Planning]>(this.url + '/planning').subscribe(data=>callback(data,context))
  }

  fetchAllUsers(callback: (users: [User], context: any) => any, context: any) {
    return this.http.get<[User]>(this.url + "/users")
      .subscribe(data => callback(data, context));
  }

  getTimeConstraints(callback: (constraints: [ConstraintTimeRoom], context: any) => any, context: any) {
    let auth_user_id = context.auth_user.id;

    return this.http.get<[ConstraintTimeRoom]>(this.url + "/constraints/time-and-room?auth="+auth_user_id)
      .subscribe(data => callback(data, context));
  }

  getPrecedenceConstraints(callback: (constraints: [ConstraintPrecedence], context: any) => any, context: any) {
    let auth_user_id = context.auth_user.id;

    return this.http.get<[ConstraintPrecedence]>(this.url + "/constraints/precedence?auth="+auth_user_id)
      .subscribe(data => callback(data, context));
  }

  getResult() {
    return this.resultChange.asObservable();
  }
}
