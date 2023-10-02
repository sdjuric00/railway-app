import { TestBed } from '@angular/core/testing';

import { BalanceAccountService } from './balance-account.service';

describe('BalanceAccountService', () => {
  let service: BalanceAccountService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BalanceAccountService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
