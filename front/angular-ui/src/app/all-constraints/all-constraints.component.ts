import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {TokenStorageService} from "../services/token-storage.service";
import {DataInterfaceService} from "../services/data-interface.service";
import {ConstraintService} from "../services/constraint.service";

@Component({
  selector: 'app-all-constraints',
  templateUrl: './all-constraints.component.html',
  styleUrls: ['./all-constraints.component.scss']
})
export class AllConstraintsComponent implements OnInit {

  timeConstraints = [];
  precedenceConstraints = [];
  auth_user = null;

  constructor(private router: Router, private tokenStorageService: TokenStorageService,
              private dataInterfaceService: DataInterfaceService, private constraintService: ConstraintService) {
  }

  ngOnInit(): void {
    this.auth_user = this.tokenStorageService.getUser();
    this.dataInterfaceService.getTimeConstraints(this.onTimeConstraintReceived, this);
    this.dataInterfaceService.getPrecedenceConstraints(this.onPrecedenceConstraintReceived, this);
    console.log(this.precedenceConstraints)
    console.log(this.timeConstraints)
  }

  onPrecedenceConstraintReceived(constraints, context: any) {
    for (let precedenceConstraint of constraints) {
      context.precedenceConstraints.push(precedenceConstraint)
    }
  }

  onTimeConstraintReceived(constraints, context: any) {
    for (let timeConstraint of constraints) {
      context.timeConstraints.push(timeConstraint)
    }
  }

  deletePrecedenceConstraints(precedence: string) {
    this.constraintService.deletePrecedenceConstraint(precedence);
  }

  deleteTimeConstraints(time: string) {
    this.constraintService.deleteTimeConstraint(time)
  }
}
