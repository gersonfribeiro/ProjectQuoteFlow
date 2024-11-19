import { CanActivateFn, Router } from '@angular/router';
import { inject } from '@angular/core';
import { map } from 'rxjs';
import {ApiUserService} from "../services/api-user.service";
import { ToastrService } from 'ngx-toastr';

export const registeredCompanyGuard: CanActivateFn = (route, state) => {
  const router = inject(Router);
  const apiUserService = inject(ApiUserService);
  const toastr = inject(ToastrService);
  const userId = localStorage.getItem('userId');

  return apiUserService.getUserById(userId).pipe(
      map((user) => {
          if (user.id_empresa) {
              toastr.warning("Você já cadastrou sua empresa!")
              return false;
          }
          return true;
        })
    );
};
