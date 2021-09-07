import { Selector } from "../selector/selector";
import { Temps } from "../temps/temps";
import { ConstraintPrecedence } from "./constraint-precedence";

export class Constraint{
    selector:Selector={
        selectorUnits:[]
    };
    veut: boolean = true;
    jour: Array<string> = ["Lundi"];
    temps: Temps = {
        temps: []
    };
    salle: string = "232C";
    priority:number=0; // This attribute should be between 0 and 100 
    precedence: ConstraintPrecedence = {
        precedence: "Before",
        strict: false,
        selectorTarget: {selectorUnits:[]}
    };
}