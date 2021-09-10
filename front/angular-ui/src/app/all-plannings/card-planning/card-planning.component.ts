import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Planning } from 'src/app/model/planning/planning';

@Component({
  selector: 'app-card-planning',
  templateUrl: './card-planning.component.html',
  styleUrls: ['./card-planning.component.scss']
})
export class CardPlanningComponent implements OnInit {

  constructor(private router:Router) { }

  @Input('planning')planning:Planning;

  ngOnInit(): void {
  }

  removePlanning(){
    //TODO
  }
  editPlanning(){
    this.router.navigate(['/manuel',{'planning_id':this.planning.id}]);
  }

}
