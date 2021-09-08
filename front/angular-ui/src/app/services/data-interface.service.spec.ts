import { TestBed } from '@angular/core/testing';

import { DataInterfaceService } from './data-interface.service';

describe('DataInterfaceService', () => {
  let service: DataInterfaceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DataInterfaceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
