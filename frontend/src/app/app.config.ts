// Importa os módulos principais do núcleo do Angular para configuração e detecção de mudanças de zona
import {ApplicationConfig, provideZoneChangeDetection} from '@angular/core';
// Importa a função necessária para fornecer e configurar o roteamento do Angular Router
import {provideRouter} from '@angular/router';

// Importa o serviço de cliente HTTP para fazer requisições HTTP na aplicação
import {provideHttpClient, withFetch, withInterceptors} from '@angular/common/http';
// Importa as rotas definidas no arquivo 'app.routes.ts', que mapeiam os caminhos da aplicação
import {routes} from './app.routes';
// Importa a função que otimiza o carregamento da página fornecendo hidratação no cliente,
// o que melhora a performance inicial da aplicação ao pré-carregar o conteúdo HTML
import {provideClientHydration} from '@angular/platform-browser';
// Importa a função para configurar o ngx-mask, uma biblioteca usada para aplicar máscaras
// de entrada (ex.: formatação de números de telefone, CPF, etc.) nos campos de formulários
import {provideEnvironmentNgxMask} from 'ngx-mask';
// Importa o suporte a animações no Angular, necessário para componentes que utilizam animações,
// como Material Design, entre outros
import {provideAnimations} from '@angular/platform-browser/animations';
// Importa o serviço Toastr, que permite exibir notificações (toasts) visuais na aplicação
import {provideToastr} from 'ngx-toastr';
// Importa a função para fornecer animações assíncronas, melhorando o desempenho em casos
// onde as animações são executadas em segundo plano
import {provideAnimationsAsync} from '@angular/platform-browser/animations/async';
// import { CeilPipe } from './pipes/ceil.pipe';
import {authInterceptor} from './auth/auth.interceptor'

// Define a configuração global da aplicação como um objeto do tipo ApplicationConfig,
// onde todos os serviços e provedores serão registrados
export const appConfig: ApplicationConfig = {
  // O array 'providers' contém uma lista de serviços que serão disponibilizados globalmente
  providers: [
    // Configura a detecção de mudanças no Angular para melhorar o desempenho,
    // agrupando eventos de mudança e reduzindo o número de verificações de alteração
    provideZoneChangeDetection({eventCoalescing: true}),
    // Configura o provedor de roteamento da aplicação com as rotas definidas no arquivo 'app.routes.ts'
    provideRouter(routes),
    // Fornece o serviço de hidratação do cliente para otimizar o carregamento inicial da aplicação,
    // permitindo a reutilização de conteúdo estático renderizado previamente
    provideClientHydration(),
    // Configura o ngx-mask para fornecer máscaras de entrada em formulários (ex.: CPF, telefone)
    provideEnvironmentNgxMask(),
    // Fornece suporte a animações no Angular, permitindo que componentes utilizem animações nativas
    provideAnimations(),
    // Registra o serviço Toastr para permitir a exibição de notificações não intrusivas na interface do usuário
    provideToastr(),
    // Fornece suporte para animações assíncronas, aumentando o desempenho em cenários que demandam animações complexas
    provideAnimationsAsync(),
    // Fornece o serviço de cliente HTTP para realizar requisições HTTP dentro da aplicação
    provideHttpClient(withInterceptors([authInterceptor])),
  ],
};
