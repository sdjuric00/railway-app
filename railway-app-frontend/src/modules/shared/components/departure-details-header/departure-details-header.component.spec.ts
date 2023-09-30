import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DepartureDetailsHeaderComponent } from './departure-details-header.component';

describe('DepartureDetailsHeaderComponent', () => {
  let component: DepartureDetailsHeaderComponent;
  let fixture: ComponentFixture<DepartureDetailsHeaderComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DepartureDetailsHeaderComponent]
    });
    fixture = TestBed.createComponent(DepartureDetailsHeaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
