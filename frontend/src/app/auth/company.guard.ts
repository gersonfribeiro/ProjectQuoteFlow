import { CanActivateFn, Router } from '@angular/router';
import { inject } from '@angular/core';
import { Observable, of } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import {ApiUserService} from "../services/api-user.service";
import { ToastrService } from 'ngx-toastr';

export const companyGuard: CanActivateFn = (route, state) => {
  const router = inject(Router);
  const apiUserService = inject(ApiUserService);
  const toastr = inject(ToastrService);
  const userId = localStorage.getItem('userId');

  return apiUserService.getUserById(userId).pipe(
      map(user => {
          if (user.permissao === "EMPRESA") {
              return true;
            } else {
              toastr.warning("Cadastre sua empresa para acessar essa página!")
              router.navigate(['dashboard/register-companies'])
              return false;
            }
        }),
      catchError(() => of(false))
    );
};
