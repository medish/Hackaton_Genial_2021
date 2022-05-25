import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormConstraintTimeComponent } from './form-constraint-time.component';

describe('FormConstraintTimeComponent', () => {
  let component: FormConstraintTimeComponent;
  let fixture: ComponentFixture<FormConstraintTimeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FormConstraintTimeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FormConstraintTimeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
