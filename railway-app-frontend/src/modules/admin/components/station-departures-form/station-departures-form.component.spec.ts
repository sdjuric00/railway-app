import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StationDeparturesFormComponent } from './station-departures-form.component';

describe('StationDeparturesFormComponent', () => {
  let component: StationDeparturesFormComponent;
  let fixture: ComponentFixture<StationDeparturesFormComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [StationDeparturesFormComponent]
    });
    fixture = TestBed.createComponent(StationDeparturesFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
