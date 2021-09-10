import { Lesson, OurDate, Room } from "../datastore/datamodel";

export class Output{
    id:string;
    date:OurDate;
    room:Room;
    lesson:Lesson;
    day:number;
    endTime:string;
}