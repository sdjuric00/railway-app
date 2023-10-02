import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TokenTransactionsPageComponent } from './token-transactions-page.component';

describe('TokenTransactionsPageComponent', () => {
  let component: TokenTransactionsPageComponent;
  let fixture: ComponentFixture<TokenTransactionsPageComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TokenTransactionsPageComponent]
    });
    fixture = TestBed.createComponent(TokenTransactionsPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
