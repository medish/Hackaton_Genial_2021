import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllConstraintsComponent } from './all-constraints.component';

describe('AllConstraintsComponent', () => {
  let component: AllConstraintsComponent;
  let fixture: ComponentFixture<AllConstraintsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AllConstraintsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AllConstraintsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
