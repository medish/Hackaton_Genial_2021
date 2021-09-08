import { Selector } from "../selector/selector";
import { Time } from "../time/time";
import { ConstraintPrecedence } from "./constraint-precedence";

export class Constraint{
    selector:Selector={
        selectorUnits:[]
    };
    wants: boolean = true;
    day: Array<string> = [];
    time: Time = {
        time: []
    };
    room: string = "";
    priority:number=0; // This attribute should be between 0 and 100 
    precedence: ConstraintPrecedence = {
        precedence: "Before",
        strict: false,
        selectorTarget: {selectorUnits:[]}
    };
}