import { TestBed } from '@angular/core/testing';

import { ChangeRouteService } from './change-route.service';

describe('ChangeRouteService', () => {
  let service: ChangeRouteService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ChangeRouteService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
