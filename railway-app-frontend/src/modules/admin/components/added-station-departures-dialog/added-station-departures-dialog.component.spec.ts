import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddedStationDeparturesDialogComponent } from './added-station-departures-dialog.component';

describe('AddedStationDeparturesDialogComponent', () => {
  let component: AddedStationDeparturesDialogComponent;
  let fixture: ComponentFixture<AddedStationDeparturesDialogComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AddedStationDeparturesDialogComponent]
    });
    fixture = TestBed.createComponent(AddedStationDeparturesDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
