import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeparturesTimetableComponent } from './departures-timetable.component';

describe('DeparturesTimetableComponent', () => {
  let component: DeparturesTimetableComponent;
  let fixture: ComponentFixture<DeparturesTimetableComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DeparturesTimetableComponent]
    });
    fixture = TestBed.createComponent(DeparturesTimetableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
