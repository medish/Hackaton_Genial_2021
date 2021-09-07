import { TestBed } from '@angular/core/testing';

import { OutputCalendarService } from './output-calendar.service';

describe('OutputCalendarService', () => {
  let service: OutputCalendarService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OutputCalendarService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
