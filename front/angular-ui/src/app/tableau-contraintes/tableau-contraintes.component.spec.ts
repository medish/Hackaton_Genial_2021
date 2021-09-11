import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TableauContraintesComponent } from './tableau-contraintes.component';

describe('TableauContraintesComponent', () => {
  let component: TableauContraintesComponent;
  let fixture: ComponentFixture<TableauContraintesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TableauContraintesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TableauContraintesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
