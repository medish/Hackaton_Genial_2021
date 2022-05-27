import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { faCalendarDays } from '@fortawesome/free-solid-svg-icons';
import { solid } from '@fortawesome/fontawesome-svg-core/import.macro';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
})
export class HeaderComponent implements OnInit {
  constructor(public authService: AuthService, public router: Router) {
    console.log(router.url);
  }

  currentActiveItem = null;
  isLoggedIn = false;
  name: string;
  url: string;
  public active_item: string = '/admin';

  ngOnInit(): void {
    this.isLoggedIn = this.authService.isLoggedIn();
  }
  ngAfterViewChecked(): void {
    this.active_item = this.router.url;
  }

  logout() {
    this.authService.logout();
  }

  home() {
    this.router.navigate(['/admin']);
    this.active_item = '/admin';
  }

  allPlannings() {
    this.router.navigate(['/all-plannings']);
    this.active_item = '/all-plannings';
  }

  manual() {
    this.router.navigate(['/manuel']);
    this.active_item = '/manuel';
  }

  allRooms() {
    this.router.navigate(['les_salles']);
    this.active_item = '/les_salles';
  }

  allDepartments() {
    this.router.navigate(['les-departements']);
    this.active_item = '/les-departements';
  }

  allUsers() {
    this.router.navigate(['les-utilisateurs']);
    this.active_item = '/les-utilisateurs';
  }

  allGroups() {
    this.router.navigate(['les-groupes']);
    this.active_item = '/les-groupes';
  }

  allConstraints() {
    this.router.navigate(['les-contraintes']);
    this.active_item = '/les-contraintes';
  }

  auto() {
    this.router.navigate(['/auto']);
  }
}
