import { Selector } from "../selector/selector";
import { Constraint } from "./constraint";

export class ConstraintPrecedence extends Constraint{
    /**Possible values for this attribute : 'before'|'after'|'synchro' */
    precedence:string='before';
    strict:boolean=false;
    selectorTarget:Selector={selectorUnits:[]};
}