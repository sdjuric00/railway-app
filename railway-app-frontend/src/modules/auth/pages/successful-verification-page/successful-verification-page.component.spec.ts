import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SuccessfulVerificationPageComponent } from './successful-verification-page.component';

describe('SuccessfulVerificationPageComponent', () => {
  let component: SuccessfulVerificationPageComponent;
  let fixture: ComponentFixture<SuccessfulVerificationPageComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SuccessfulVerificationPageComponent]
    });
    fixture = TestBed.createComponent(SuccessfulVerificationPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
