import { Component, OnInit } from '@angular/core';
import { Planning } from '../model/planning/planning';
import { DataInterfaceService } from '../services/data-interface.service';

@Component({
  selector: 'app-all-plannings',
  templateUrl: './all-plannings.component.html',
  styleUrls: ['./all-plannings.component.scss']
})
export class AllPlanningsComponent implements OnInit {

  plannings:Planning[]=[];
  constructor(private dataSource:DataInterfaceService) { }

  ngOnInit(): void {
    this.dataSource.fetchAllPlannings(this.onPlanningsReceived, this);
  }
  onPlanningsReceived(plannings:Planning[],context:any){
    context.plannings = plannings;
  }
}
