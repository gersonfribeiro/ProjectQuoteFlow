import {CanActivateFn, Router} from '@angular/router';
import {inject} from '@angular/core';
import {ToastrService} from 'ngx-toastr';

export const authGuard: CanActivateFn = (route, state) => {
  const router = inject(Router);
  const toastr = inject(ToastrService);

  const isAuthenticated = !!localStorage.getItem('authToken');

  if (isAuthenticated) {
    return true; // Permite o acesso
  } else {
    // Redireciona o usuário para a página de login se não estiver autenticado
    toastr.warning('Registre-se ou faça login para acessar o sistema.');
    router.navigate(['/login']);
    return false; // Bloqueia o acesso à rota
  }
};
