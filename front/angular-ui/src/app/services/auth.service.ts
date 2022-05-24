import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { User } from '../model/user';
import { TokenStorageService } from './token-storage.service';


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
    return true;
    let currentUser = this.tokenService.getUser();
    return currentUser!=null && currentUser!=undefined;
  };
  login(username: string, password: string): Promise<any> {
    /**return this.http.post(AUTH_API + 'signin', {
      username,
      password
    }, httpOptions).toPromise().then(data=>{this.isLoggedIn=true});**/
    return this.http.get(environment.baseUrl + '/signin?username='+username+"&password="+password, httpOptions).toPromise();
  }
  logout(){
    this.tokenService.signOut();
  }

  getUser(){
    return this.tokenService.getUser();
  }
}
