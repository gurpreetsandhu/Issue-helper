import { TestBed, async, inject } from '@angular/core/testing';

import { AccountGuardGuard } from './account-guard.guard';

describe('AccountGuardGuard', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AccountGuardGuard]
    });
  });

  it('should ...', inject([AccountGuardGuard], (guard: AccountGuardGuard) => {
    expect(guard).toBeTruthy();
  }));
});
