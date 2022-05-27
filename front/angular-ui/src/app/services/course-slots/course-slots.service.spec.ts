import { TestBed } from '@angular/core/testing';

import { CourseSlotsService } from './course-slots.service';

describe('CourseSlotsService', () => {
  let service: CourseSlotsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CourseSlotsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
