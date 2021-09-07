import * as internal from "stream";
import { Constraint } from "./constraint";

export class ConstraintTimeRoom extends Constraint{
    room:string='';
    day:number=0;
    hourBegin:string='08:00';
    hourEnd:string='12:00';
}