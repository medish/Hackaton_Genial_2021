import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { faCalendarDays } from "@fortawesome/free-solid-svg-icons";
import {solid} from "@fortawesome/fontawesome-svg-core/import.macro";
import {AuthcontrollerApi, CourseGroup, CoursegroupcontrollerApi} from "../model/swagger/api";
import { param } from 'jquery';


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})


export class HeaderComponent implements OnInit {

  constructor(public authService: AuthService, public router: Router, public c: AuthcontrollerApi) {

  }

  currentActiveItem = null;
  isLoggedIn = false;
  name: string
  url: string;



  ngOnInit(): void {
    this.isLoggedIn = this.authService.isLoggedIn();
    this.c.checkAuthUsingGET({username:'',password:""}).then(data => {
      console.log(data)
      }
    )
    // insert group

  }



  acitiveItem() {

  }

  active_item = "actived";

  logout() {
    this.authService.logout();
  }

  home() {
    this.router.navigate(['/admin']);
  }

  allPlannings() {
    this.router.navigate(['/all-plannings'])
  }

  manual() {
    this.router.navigate(['/manuel'])
  }

  allRooms() {
    this.router.navigate(['les_salles'])
  }

  allDepartments() {
    this.router.navigate(['les-departements'])
  }

  allUsers() {
    this.router.navigate(['les-utilisateurs'])
  }

  allGroups() {
    this.router.navigate(['les-groupes'])
  }

  allConstraints() {
    this.router.navigate(['les-contraintes'])
  }

  auto() {
    this.router.navigate(['/auto'])
  }

}
