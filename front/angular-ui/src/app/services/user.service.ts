import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  isAdmin=true;
  isLoggedIn=true;
  constructor() {
    console.log(this.isLoggedIn)
   }
}
