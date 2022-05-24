import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {AuthService} from '../services/auth.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  constructor(public authService: AuthService, public router: Router) {
  }

  isLoggedIn = false;

  ngOnInit(): void {
    this.isLoggedIn = this.authService.isLoggedIn()
  }


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
