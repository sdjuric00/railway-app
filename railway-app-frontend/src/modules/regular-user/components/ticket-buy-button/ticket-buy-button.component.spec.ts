import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TicketBuyButtonComponent } from './ticket-buy-button.component';

describe('TicketBuyButtonComponent', () => {
  let component: TicketBuyButtonComponent;
  let fixture: ComponentFixture<TicketBuyButtonComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TicketBuyButtonComponent]
    });
    fixture = TestBed.createComponent(TicketBuyButtonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
