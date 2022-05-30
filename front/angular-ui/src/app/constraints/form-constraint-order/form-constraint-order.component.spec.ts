import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormConstraintOrderComponent } from './form-constraint-order.component';

describe('FormConstraintOrderComponent', () => {
  let component: FormConstraintOrderComponent;
  let fixture: ComponentFixture<FormConstraintOrderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FormConstraintOrderComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FormConstraintOrderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
