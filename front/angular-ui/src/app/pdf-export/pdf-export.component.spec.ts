import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PdfExportComponent } from './pdf-export.component';

describe('PdfExportComponent', () => {
  let component: PdfExportComponent;
  let fixture: ComponentFixture<PdfExportComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PdfExportComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PdfExportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
