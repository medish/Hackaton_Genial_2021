import { Component, OnInit } from '@angular/core';
import { Constraint } from '../model/constraint/constraint';


let CONSTRAINTS: Constraint[] = [
  {
    active: true,
    selector:{
      selectorUnits:[
        {
          enseignant:'a',
          enseignement:'b',
          cursus:'c',
        }
      ]
    }, 
    veut: true,
    jour: ["Lundi", "Mardi", "Mercredi"], 
    temps: {
      temps: [
        {debut: 8,
        fin: 17}
      ]
    }, 
    salle: "232C", 
    priority: 80, 
    precedence: {
      precedence: "Before",
      strict: false,
      selectorTarget: {selectorUnits:[
        { 
          enseignant:'d',
          enseignement:'e',
          cursus:'f'
        }
      ]}
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
  setActive(newValue) {
    const constraintIndex = parseInt(newValue.currentTarget.id);
    const constraintActive =  this.constraints[constraintIndex].active;
    this.constraints[constraintIndex].active = !constraintActive;
  }
  constructor() { }
  ngOnInit(): void {
  }
  

}
