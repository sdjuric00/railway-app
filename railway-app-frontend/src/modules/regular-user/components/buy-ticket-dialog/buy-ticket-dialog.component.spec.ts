import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BuyTicketDialogComponent } from './buy-ticket-dialog.component';

describe('BuyTicketDialogComponent', () => {
  let component: BuyTicketDialogComponent;
  let fixture: ComponentFixture<BuyTicketDialogComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [BuyTicketDialogComponent]
    });
    fixture = TestBed.createComponent(BuyTicketDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
