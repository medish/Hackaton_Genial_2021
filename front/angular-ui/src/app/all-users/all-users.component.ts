import { Component, OnInit } from '@angular/core';
import {Professor} from "../model/datastore/datamodel";
import {DataInterfaceService} from "../services/data-interface.service";

@Component({
  selector: 'app-all-users',
  templateUrl: './all-users.component.html',
  styleUrls: ['./all-users.component.scss']
})
export class AllUsersComponent implements OnInit {

  constructor(private dataService : DataInterfaceService) { }

  all_teachers : Professor[] = [];
  ngOnInit(): void {
      this.dataService.fetchAllTeachers(this.onTeachersReceived,this)
  }

  onTeachersReceived(teachers :[Professor],context:this){
    console.log("Teachers",teachers);
  }

}
