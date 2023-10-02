import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TotalPriceCheckComponent } from './total-price-check.component';

describe('TotalPriceCheckComponent', () => {
  let component: TotalPriceCheckComponent;
  let fixture: ComponentFixture<TotalPriceCheckComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TotalPriceCheckComponent]
    });
    fixture = TestBed.createComponent(TotalPriceCheckComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
