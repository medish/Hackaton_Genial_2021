import { Selector } from "../selector/selector";
import {Time} from "../time/time";
import {DateSlot} from "../swagger/api";

export class ConstraintTimeRoom {
    id:number;
    selector:Selector={
        selectorUnits:[]
    };
    wants: boolean = true;
    day:number = 1;
    dateBegin:string = '08:00:00';
    dateEnd:string = '12:00:00';
    room:Selector = {selectorUnits:[]};
    priority:number = 0; // This attribute should be between 0 and 100
}

export class ConstraintTimeRoomExport {
  id:number;
  selector:string;
  wants: boolean = true;
  dateBegin:DateSlot;
  dateEnd:DateSlot;
  room:string;
  priority:number = 0; // This attribute should be between 0 and 100
}
