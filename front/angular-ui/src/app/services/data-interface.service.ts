import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {identity, Observable, throwError } from 'rxjs';
import { ConstraintPrecedence } from '../model/constraint/constraint-precedence';
import { ConstraintTimeRoom } from '../model/constraint/constraint-time-room';
import { Room, Class, RoomType, Teacher, Department, Degree, Identity, Output } from '../model/datastore/datamodel'
import { catchError, retry } from 'rxjs/operators';
import { Planning } from '../model/planning/planning';

@Injectable({
  providedIn: 'root'
})
export class DataInterfaceService {

  url = "http://localhost:3000"

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

  sendTimeRoomConstraints(constraints: [ConstraintTimeRoom]) {
    return this.http.post(this.url + "/constraints/time-and-room", constraints)
    .pipe(catchError(this.handleError)).subscribe();
  }

  sendPrecedenceConstraints(constraints: [ConstraintPrecedence]) {
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

  fetchAllClasses(callback: (classes: [Class], context: any) => any, context: any) {
    return this.http.get<[Class]>(this.url + "/lessons")
    .subscribe(data => callback(data, context));
  }

  fetchAllRoomTypes(callback: (roomTypes: [RoomType]) => any) {
    return this.http.get<[RoomType]>(this.url + "/roomTypes")
    .subscribe(data => callback(data));
  }

  fetchAllTeachers(callback: (teachers: [Teacher], context: any) => any, context: any) {
    return this.http.get<[Teacher]>(this.url + "/professors")
    .subscribe(data => callback(data, context));
  }

  fetchAllDepartments(callback: (departments: [Department]) => any) {
    return this.http.get<[Department]>(this.url + "/departments")
    .subscribe(data => callback(data));
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

  verifyConstraints(constraints: [Output], callback: (violatedConstraints: number) => any) {
    return this.http.post<number>(this.url + "/constraints/verify", constraints)
    .pipe(catchError(this.handleError)).subscribe(data => callback(data));
  }

  fetchAllPlannings(callback: (plannings:[Planning],context:any)=>any, context:any){
    return this.http.get<[Planning]>(this.url+'/planning').subscribe(data=>callback(data,context))
  }

  handleError(error: HttpErrorResponse) {
    console.log("[HTTP ERROR]: " + error);
    return throwError("An error happened");
  }
}