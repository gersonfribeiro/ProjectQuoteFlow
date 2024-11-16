import { CanActivateFn } from '@angular/router';

export const quotationGuard: CanActivateFn = (route, state) => {
  // Verifica se o estado de cotação está no localStorage
  const isQuotationStarted = localStorage.getItem('isQuotationStarted') === 'true';

  if (isQuotationStarted) {
    alert('Não é possível mudar de rota enquanto uma cotação está em andamento.');
    return false; // Bloqueia a navegação
  }

  return true; // Permite a navegação
};
