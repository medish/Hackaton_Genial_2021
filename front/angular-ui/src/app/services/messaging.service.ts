import { Injectable } from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MessagingService {
  message = new BehaviorSubject(false);
  currentMessageState = this.message.asObservable();

  constructor() { }

  updateMessage(message: boolean) {
    this.message.next(message);
  }
}
