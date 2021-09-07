import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InputConstraintComponent } from './input-constraint.component';

describe('InputConstraintComponent', () => {
  let component: InputConstraintComponent;
  let fixture: ComponentFixture<InputConstraintComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InputConstraintComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(InputConstraintComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
