import {CanActivateFn, Router} from '@angular/router';
import {inject} from '@angular/core';

export const authGuard: CanActivateFn = (route, state) => {
  const router = inject(Router);

  const isAuthenticated = !!localStorage.getItem('authToken');

  if (isAuthenticated) {
    return true; // Permite o acesso
  } else {
    // Redireciona o usuário para a página de login se não estiver autenticado
    router.navigate(['/login']);
    return false; // Bloqueia o acesso à rota
  }
};