import { Selector } from "../selector/selector";

export class ConstraintTimeRoom {
    id:number;
    selector:Selector={
        selectorUnits:[]
    };
    veut: boolean = true;
    priority:number=0; // This attribute should be between 0 and 100 
    room:Selector={selectorUnits:[]};
    day:number=1;
    hourBegin:string='08:00';
    hourEnd:string='12:00';
}