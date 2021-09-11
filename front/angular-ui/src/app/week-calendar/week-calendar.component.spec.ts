import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WeekCalendarComponent } from './week-calendar.component';

describe('WeekCalendarComponent', () => {
  let component: WeekCalendarComponent;
  let fixture: ComponentFixture<WeekCalendarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ WeekCalendarComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(WeekCalendarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
