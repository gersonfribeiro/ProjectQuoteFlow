import { TestBed } from '@angular/core/testing';
import { CanActivateFn } from '@angular/router';

import { registeredCompanyGuard } from './registered-company.guard';

describe('registeredCompanyGuard', () => {
  const executeGuard: CanActivateFn = (...guardParameters) => 
      TestBed.runInInjectionContext(() => registeredCompanyGuard(...guardParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeGuard).toBeTruthy();
  });
});
