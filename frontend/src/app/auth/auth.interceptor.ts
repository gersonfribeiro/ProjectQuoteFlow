import { HttpInterceptorFn, HttpErrorResponse } from '@angular/common/http';
import { inject } from '@angular/core';
import { Router } from '@angular/router';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { ToastrService } from 'ngx-toastr';

export const authInterceptor: HttpInterceptorFn = (req, next) => {

  const token = localStorage.getItem('authToken');
  const router = inject(Router);
  const toastr = inject(ToastrService);

  // Verificar se a URL não é /usuarios/registrar nem /usuarios/login
  if (token && !req.url.includes('/usuarios/registrar') && !req.url.includes('/usuarios/login')) {
    const clonedRequest = req.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`
      }
    });

    return next(clonedRequest).pipe(
      catchError((error: HttpErrorResponse) => {
        if (error.status === 403) {
          // Token expirado ou inválido
          localStorage.removeItem('authToken');
          toastr.warning("Sua sessão expirou-se. Por favor, faça o login novamente!")
          router.navigate(['/login']); // Redireciona para a página de login
        }
        return throwError(error);
      })
    );
  }

  return next(req).pipe(
    catchError((error: HttpErrorResponse) => {
      if (error.status === 403) {
        // Token expirado ou inválido
        localStorage.removeItem('authToken');
        toastr.warning("Sua sessão expirou-se. Por favor, faça o login novamente!")
        router.navigate(['/login']); // Redireciona para a página de login
      }
      return throwError(error);
    })
  );
};
