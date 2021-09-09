import { splitAtColon } from '@angular/compiler/src/util';
import { Injectable } from '@angular/core';
import { TurnedIn } from '@material-ui/icons';
import { ConstraintPrecedence } from 'src/app/model/constraint/constraint-precedence';
import { ConstraintTimeRoom } from 'src/app/model/constraint/constraint-time-room';
import { Selector } from 'src/app/model/selector/selector';


let CSV_SEPARATOR = ';';
let SELECTOR_SEPARATOR = ',';
let SELECTOR_INTERNAL_SEPARATOR = ':';

@Injectable({
  providedIn: 'root'
})
export class ConstraintService {
  
  constructor() { }
  
  /**
  * 
  * @param constraints 
  * @returns null if errors, else the list of parsed constraints
  */
  parseConstraintsTimeAndRoom(constraints:string):ConstraintTimeRoom[]{
    let lines = constraints.split('\n');
    let result:ConstraintTimeRoom[]=[];
    let lineSplitted = []
    for(let line of lines){
      lineSplitted = line.split(CSV_SEPARATOR);
      if(!this.verifySplittedLineConstraintsTimeRoom(lineSplitted))return null;
      result.push({
        selector: this.parseSelector(lineSplitted[0]),
        veut:lineSplitted[1].toLowerCase()=='true',
        room:this.parseSelector(lineSplitted[0]),
        day:parseInt(lineSplitted[2]),
        hourBegin:lineSplitted[3],
        hourEnd:lineSplitted[4],
        priority:parseInt(lineSplitted[6])
      })
    }
    return result;
  }
  
  predicatesTimeAndRoomEntry: ((string)=>boolean)[] = [
    selector=>selector?.match(/^([a-zA-Z]+:[a-zA-Z]+:[a-zA-Z][a-zA-Z0-9]*,)*([a-zA-Z]+:[a-zA-Z]+:[a-zA-Z][a-zA-Z0-9]*)$/g),
    want=>want?.match(/^(true|false)$/g),
    dayOfWeek=>dayOfWeek?.match(/^[1-7]$/g),
    hour=>hour?.match(/^[0-2]\d:$[0-5]\d/g),
    hour=>hour?.match(/^[0-2]\d:$[0-5]\d/g),
    roomSelector=>roomSelector?.match(/^(room:[a-zA-Z]+:[a-zA-Z][a-zA-Z0-9]*,)*(room:[a-zA-Z]+:[a-zA-Z][a-zA-Z0-9]*)$/g),
    priority=>priority?.match(/^0*(?:[1-9][0-9]?|100)$/)
  ]
  
  verifySplittedLineConstraintsTimeRoom(splittedLine:string[]){
    if(!splittedLine)return false;
    if(splittedLine.length != 7)return false;
    for(let i=0; i<splittedLine.length;i++)if(!this.predicatesTimeAndRoomEntry[i](splittedLine[i]))return false;
    return true;
  }
  
  parseConstraintsPrecedence(constraints:string):ConstraintPrecedence[]{
    let lines = constraints.split('\n');
    let result:ConstraintPrecedence[]=[];
    let lineSplitted = []
    for(let line of lines){
      lineSplitted = line.split(CSV_SEPARATOR);
      if(!this.verifySplittedLinePrecedence(lineSplitted))return null;
      result.push({
        selector:this.parseSelector(lineSplitted[0]),
        veut:lineSplitted[1]=='true',
        priority:parseInt(lineSplitted[5]),
        precedence:lineSplitted[2],
        strict:lineSplitted[3]=='true',
        selectorTarget:this.parseSelector(lineSplitted[4])
      })
    }
    return result;
  }
  
  predicatesPrecedence: ((string)=>boolean)[] = [
    this.predicatesTimeAndRoomEntry[0],
    this.predicatesTimeAndRoomEntry[1],
    beforeOfAfterOrSynchro=>beforeOfAfterOrSynchro?.match(/^(before|after|synchro)$/),
    this.predicatesTimeAndRoomEntry[1],
    this.predicatesTimeAndRoomEntry[0],
    this.predicatesTimeAndRoomEntry[6]
  ]
  
  verifySplittedLinePrecedence(splittedLine:string[]):boolean{
    if(!splittedLine)return false;
    if(splittedLine.length != 6)return false;
    for(let i=0; i<splittedLine.length;i++)if(!this.predicatesPrecedence[i](splittedLine[i]))return false;
    return true;
  }
  
  parseSelector(selectorString:string):Selector{
    return {selectorUnits:selectorString.split(SELECTOR_SEPARATOR)
      .map(sU=>{let splitted=sU.split(SELECTOR_INTERNAL_SEPARATOR);
        return {table:splitted[0],attribute:splitted[1],value:splitted[2]}
      })
    }}
  }
  