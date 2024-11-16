import { TestBed } from '@angular/core/testing';
import { CanActivateFn } from '@angular/router';

import { quotationGuard } from './quotation.guard';

describe('quotationGuard', () => {
  const executeGuard: CanActivateFn = (...guardParameters) => 
      TestBed.runInInjectionContext(() => quotationGuard(...guardParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeGuard).toBeTruthy();
  });
});
