import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CardPlanningComponent } from './card-planning.component';

describe('CardPlanningComponent', () => {
  let component: CardPlanningComponent;
  let fixture: ComponentFixture<CardPlanningComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CardPlanningComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CardPlanningComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
