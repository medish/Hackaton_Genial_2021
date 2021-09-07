import { Injectable } from '@angular/core';
import { from, Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class OutputCalendarService {
  url = 'http://localhost:3000'

  constructor(private http: HttpClient) { }

  fetchAllRooms(): Observable<[Room]> {
    return this.http.get<[Room]>(this.url + "/rooms");
  }

  fetchAllClasses(): Observable<[Class]> {
    return this.http.get<[Class]>(this.url + "/class");
  }

  fetchAllRoomTypes(): Observable<[RoomType]> {
    return this.http.get<[RoomType]>(this.url + "/roomTypes");
  }

  fetchAllTeachers(): Observable<[Teacher]> {
    return this.http.get<[Teacher]>(this.url + "/teachers");
  }

  fetchAllDepartments(): Observable<[Department]> {
    return this.http.get<[Department]>(this.url + "/departments") 
  }

  fetchAllDegrees(): Observable<[Degree]> {
    return this.http.get<[Degree]>(this.url + "/degrees");
  }
}

export interface Room {
  number: number,
  departmentId: number,
  capacity: number,
  typeId: number
}

export interface Class {
  id: number,
  duration: Date,
  size: number
};

export interface RoomType {
  id: number,
  name: string
}

export interface Teacher {
  id: number,
  departmentId: number,
  firstName: string,
  lastName: string,
  email: string,
  is_admin: boolean
};

export interface Department {
  id: number,
  name: string
};

export interface Degree {
  id: number,
  name: string
};