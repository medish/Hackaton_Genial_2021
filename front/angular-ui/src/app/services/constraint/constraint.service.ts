import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ConstraintService {

  constructor() { }

  parseConstraints(constraints:string){
    let lines = constraints.split('\n');
  }
}
