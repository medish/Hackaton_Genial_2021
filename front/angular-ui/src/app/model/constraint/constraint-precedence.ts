import { Selector } from "../selector/selector";

export class ConstraintPrecedence{
    id:number;
    selector:Selector={
        selectorUnits:[]
    };
    veut: boolean = true;
    priority:number=0; // This attribute should be between 0 and 100 
    precedence:string='before'; //Possible values for this attribute : 'before'|'after'|'synchro'
    strict:boolean=false;
    selectorTarget:Selector={selectorUnits:[]};
}