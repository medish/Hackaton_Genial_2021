import { Component, OnInit } from '@angular/core';
import { Constraint } from '../model/constraint/constraint';


const CONSTRAINTS: Constraint[] = [
  {
    selector:{
      selectorUnits:[]
    }, 
    veut: true,
    jour: ["Lundi"], 
    temps: {
      temps: []
    }, 
    salle: "232C", 
    priority: 80, 
    precedence: {
      precedence: "Before",
      strict: false,
      selectorTarget: {selectorUnits:[]}
    }
  }
];

@Component({
  selector: 'tableau-contraintes',
  templateUrl: './tableau-contraintes.component.html',
  styleUrls: ['./tableau-contraintes.component.scss']
})


export class TableauContraintesComponent implements OnInit {

  constraints = CONSTRAINTS;
  constructor() { }

  ngOnInit(): void {
  }
  

}
