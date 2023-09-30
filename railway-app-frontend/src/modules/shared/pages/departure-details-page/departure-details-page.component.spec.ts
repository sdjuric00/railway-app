import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DepartureDetailsPageComponent } from './departure-details-page.component';

describe('DepartureDetailsPageComponent', () => {
  let component: DepartureDetailsPageComponent;
  let fixture: ComponentFixture<DepartureDetailsPageComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DepartureDetailsPageComponent]
    });
    fixture = TestBed.createComponent(DepartureDetailsPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
