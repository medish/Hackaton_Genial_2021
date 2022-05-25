import { Selector } from "../selector/selector";
import {Time} from "../time/time";

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
  day:number = 1;
  dateBegin:DataSlot;
  dateEnd:DataSlot;
  room:string;
  priority:number = 0; // This attribute should be between 0 and 100
}

export class DataSlot {
  day: string;
  startTime: string;
}
