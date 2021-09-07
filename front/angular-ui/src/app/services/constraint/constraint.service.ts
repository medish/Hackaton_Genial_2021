import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ConstraintService {

  constructor() { }

  parseConstraints(constraints:string){
    console.log(constraints);
  }
}
