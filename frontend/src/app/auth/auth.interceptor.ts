import { HttpInterceptorFn } from '@angular/common/http';

export const authInterceptor: HttpInterceptorFn = (req, next) => {

  const token = localStorage.getItem('authToken');

  // Verificar se a URL não é /usuarios/registrar nem /usuarios/login
  if (token && !req.url.includes('/usuarios/registrar') && !req.url.includes('/usuarios/login')) {
    const clonedRequest = req.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`
      }
    });
    return next(clonedRequest);
  }

  return next(req);
};
