import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetFileConstraintsComponent } from './get-file-constraints.component';

describe('GetFileConstraintsComponent', () => {
  let component: GetFileConstraintsComponent;
  let fixture: ComponentFixture<GetFileConstraintsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GetFileConstraintsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GetFileConstraintsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
