import { TestBed } from '@angular/core/testing';
import { CanActivateFn } from '@angular/router';

import { companyGuard } from './company.guard';

describe('companyGuard', () => {
  const executeGuard: CanActivateFn = (...guardParameters) => 
      TestBed.runInInjectionContext(() => companyGuard(...guardParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeGuard).toBeTruthy();
  });
});
