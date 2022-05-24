import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { faCalendarDays } from "@fortawesome/free-solid-svg-icons";
import {solid} from "@fortawesome/fontawesome-svg-core/import.macro";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  faCalendarDays = faCalendarDays;

  constructor(public authService: AuthService, public router:Router) { }

  ngOnInit(): void {
  }

  logout(){
    this.authService.logout();
  }

  home(){
    this.router.navigate(['/admin']);
  }

  allPlannings(){
    this.router.navigate(['/all-plannings'])
  }

  manual(){
    this.router.navigate(['/manuel'])
  }

  auto(){
    this.router.navigate(['/auto'])
  }

}
