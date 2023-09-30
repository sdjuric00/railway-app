import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllStationsTableComponent } from './all-stations-table.component';

describe('AllStationsTableComponent', () => {
  let component: AllStationsTableComponent;
  let fixture: ComponentFixture<AllStationsTableComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AllStationsTableComponent]
    });
    fixture = TestBed.createComponent(AllStationsTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
