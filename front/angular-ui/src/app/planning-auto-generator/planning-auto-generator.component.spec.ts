import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PlanningAutoGeneratorComponent } from './planning-auto-generator.component';

describe('PlanningAutoGeneratorComponent', () => {
  let component: PlanningAutoGeneratorComponent;
  let fixture: ComponentFixture<PlanningAutoGeneratorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PlanningAutoGeneratorComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PlanningAutoGeneratorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
