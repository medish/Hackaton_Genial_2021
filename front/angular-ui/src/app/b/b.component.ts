import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-b',
  templateUrl: './b.component.html',
  styleUrls: ['./b.component.scss']
})
export class BComponent implements OnInit {

  constructor() { }
  @Input('nom')monAmi='Hichem';

  @Output('evt')evt=new EventEmitter<string>();

  ngOnInit(): void {

  }

  fct(monAmi:string){
    console.log(monAmi)
    this.evt.emit(monAmi);
    return monAmi;
  }

}
