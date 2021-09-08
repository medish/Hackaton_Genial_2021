import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../model/user';
import { TokenStorageService } from './token-storage.service';

const AUTH_API = 'http://localhost:3000/';
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService implements OnInit{

  constructor(private http: HttpClient, private tokenService :TokenStorageService) { 
    
    
  }
  ngOnInit(){
  }
  isLoggedIn():boolean{
    console.log('is logged in',this.tokenService.getUser())
    let currentUser = this.tokenService.getUser();
    return currentUser!=null && currentUser!=undefined;
  };
  login(username: string, password: string): Promise<any> {
    /**return this.http.post(AUTH_API + 'signin', {
      username,
      password
    }, httpOptions).toPromise().then(data=>{this.isLoggedIn=true});**/
    return this.http.get(AUTH_API + 'signin?username='+username+"&password="+password, httpOptions).toPromise();
  }
  logout(){
    this.tokenService.signOut();
  }

  getUser(){
    return this.tokenService.getUser();
  }
}
