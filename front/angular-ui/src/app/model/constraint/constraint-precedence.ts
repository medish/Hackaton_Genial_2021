import { Selector } from "../selector/selector";

export class ConstraintPrecedence{
    id:number;
    selector:Selector={
        selectorUnits:[]
    };
    wants: boolean = true;
    whenConstraint:string='before'; //Possible values for this attribute : 'before'|'after'|'synchro'
    strict:boolean=false;
    selectorTarget:Selector={selectorUnits:[]};
    priority:number=0; // This attribute should be between 0 and 100
}

export class ConstraintPrecedenceExport {
  id:number;
  selector:string
  wants: boolean = true;
  whenConstraint:string='before'; //Possible values for this attribute : 'before'|'after'|'synchro'
  strict:boolean=false;
  target:string;
  priority:number=0; // This attribute should be between 0 and 100
}
