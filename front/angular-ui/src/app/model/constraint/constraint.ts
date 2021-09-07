import { Selector } from "../selector/selector";

export class Constraint{
    selector:Selector={
        selectorUnits:[]
    };

    should:boolean=true;
    /**This attribute should be between 0 and 100 */
    priority:number=0;
}