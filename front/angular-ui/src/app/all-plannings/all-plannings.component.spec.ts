import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllPlanningsComponent } from './all-plannings.component';

describe('AllPlanningsComponent', () => {
  let component: AllPlanningsComponent;
  let fixture: ComponentFixture<AllPlanningsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AllPlanningsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AllPlanningsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
