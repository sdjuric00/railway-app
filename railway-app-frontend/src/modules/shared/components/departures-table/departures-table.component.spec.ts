import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeparturesTableComponent } from './departures-table.component';

describe('DeparturesTableComponent', () => {
  let component: DeparturesTableComponent;
  let fixture: ComponentFixture<DeparturesTableComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DeparturesTableComponent]
    });
    fixture = TestBed.createComponent(DeparturesTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
