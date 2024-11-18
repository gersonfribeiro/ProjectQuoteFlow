import { CanDeactivateFn } from '@angular/router';

export const quotationGuard: CanDeactivateFn<unknown> = (component, currentRoute, currentState, nextState) => {
  // Obtenha o valor do localStorage
  const isQuotationStarted = localStorage.getItem('isQuotationStarted') === 'true';

  if (isQuotationStarted) {
    return window.confirm('Você tem alterações não salvas. Tem certeza de que deseja sair?');
  }

  return true;
};
