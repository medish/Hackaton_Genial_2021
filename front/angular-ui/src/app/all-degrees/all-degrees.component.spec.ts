import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllDegreesComponent } from './all-degrees.component';

describe('AllDegreesComponent', () => {
  let component: AllDegreesComponent;
  let fixture: ComponentFixture<AllDegreesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AllDegreesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AllDegreesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
