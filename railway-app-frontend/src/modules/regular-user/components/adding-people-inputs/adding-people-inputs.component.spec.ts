import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddingPeopleInputsComponent } from './adding-people-inputs.component';

describe('AddingPeopleInputsComponent', () => {
  let component: AddingPeopleInputsComponent;
  let fixture: ComponentFixture<AddingPeopleInputsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AddingPeopleInputsComponent]
    });
    fixture = TestBed.createComponent(AddingPeopleInputsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
