import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PaypalBuyingFormComponent } from './paypal-buying-form.component';

describe('PaypalBuyingFormComponent', () => {
  let component: PaypalBuyingFormComponent;
  let fixture: ComponentFixture<PaypalBuyingFormComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PaypalBuyingFormComponent]
    });
    fixture = TestBed.createComponent(PaypalBuyingFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
