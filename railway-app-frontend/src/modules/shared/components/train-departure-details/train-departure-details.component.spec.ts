import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TrainDepartureDetailsComponent } from './train-departure-details.component';

describe('TrainDepartureDetailsComponent', () => {
  let component: TrainDepartureDetailsComponent;
  let fixture: ComponentFixture<TrainDepartureDetailsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TrainDepartureDetailsComponent]
    });
    fixture = TestBed.createComponent(TrainDepartureDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
