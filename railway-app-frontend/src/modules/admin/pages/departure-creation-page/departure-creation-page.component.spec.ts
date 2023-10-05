import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DepartureCreationPageComponent } from './departure-creation-page.component';

describe('DepartureCreationPageComponent', () => {
  let component: DepartureCreationPageComponent;
  let fixture: ComponentFixture<DepartureCreationPageComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DepartureCreationPageComponent]
    });
    fixture = TestBed.createComponent(DepartureCreationPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
