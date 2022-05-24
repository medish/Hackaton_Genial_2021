import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot} from '@angular/router';
import {AuthService} from "./auth.service";

@Injectable({
  providedIn: 'root'
})

export class ChangeRouteService implements CanActivate {

  constructor(private authService: AuthService) {
  }

  canActivate(next: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    this.authService.currentRoute = next.url.toString()
    return true
  }
}

