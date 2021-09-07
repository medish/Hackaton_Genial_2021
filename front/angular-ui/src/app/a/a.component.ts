import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-a',
  templateUrl: './a.component.html',
  styleUrls: ['./a.component.scss']
})
export class AComponent implements OnInit {

  constructor() { }
  nom='Massyl'
  ngOnInit(): void {
  }
  fParent(event:string){
    console.log('from AAAA')
    console.log(event)
  }

}
