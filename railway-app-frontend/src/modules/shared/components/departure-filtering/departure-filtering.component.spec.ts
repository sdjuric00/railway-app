import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DepartureFilteringComponent } from './departure-filtering.component';

describe('DepartureFilteringComponent', () => {
  let component: DepartureFilteringComponent;
  let fixture: ComponentFixture<DepartureFilteringComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DepartureFilteringComponent]
    });
    fixture = TestBed.createComponent(DepartureFilteringComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
