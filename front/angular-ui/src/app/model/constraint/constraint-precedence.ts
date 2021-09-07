import { Selector } from "../selector/selector";
import { Constraint } from "./constraint";

export class ConstraintPrecedence{
    precedence:string='before'; //Possible values for this attribute : 'before'|'after'|'synchro'
    strict:boolean=false;
    selectorTarget:Selector={selectorUnits:[]};
}