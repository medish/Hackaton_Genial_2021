import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PlanningManuelGeneratorComponent } from './planning-manuel-generator.component';

describe('PlanningManuelGeneratorComponent', () => {
  let component: PlanningManuelGeneratorComponent;
  let fixture: ComponentFixture<PlanningManuelGeneratorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PlanningManuelGeneratorComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PlanningManuelGeneratorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
