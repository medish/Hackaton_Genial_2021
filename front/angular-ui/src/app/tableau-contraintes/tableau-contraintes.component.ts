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
          table:'a',
          attribute:'b',
          value:'c'
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
  constructor() { }
  ngOnInit(): void {
console.log(CONSTRAINTS)

  }
  

}
