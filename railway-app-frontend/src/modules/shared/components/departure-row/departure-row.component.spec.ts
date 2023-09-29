import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DepartureRowComponent } from './departure-row.component';

describe('DepartureRowComponent', () => {
  let component: DepartureRowComponent;
  let fixture: ComponentFixture<DepartureRowComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DepartureRowComponent]
    });
    fixture = TestBed.createComponent(DepartureRowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
