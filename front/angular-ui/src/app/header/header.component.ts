import { Route } from '@angular/compiler/src/core';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  constructor(public authService: AuthService, public router:Router) { }

  ngOnInit(): void {
  }

  logout(){
    this.authService.logout();
  }

  home(){
    this.router.navigate(['/admin']);
  }


}
